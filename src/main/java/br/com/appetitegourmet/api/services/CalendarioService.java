package br.com.appetitegourmet.api.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.time.DateUtils;


import org.springframework.stereotype.Service;
import br.com.appetitegourmet.api.models.AnoLetivo;
import br.com.appetitegourmet.api.models.Calendario;
import br.com.appetitegourmet.api.models.CalendarioExcecao;
import br.com.appetitegourmet.api.models.CidadeAnoLetivo;
import br.com.appetitegourmet.api.models.Feriado;
import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import br.com.appetitegourmet.api.models.Turma;
import br.com.appetitegourmet.api.repositories.AnoLetivoRepository;
import br.com.appetitegourmet.api.repositories.CalendarioExcecaoRepository;
import br.com.appetitegourmet.api.repositories.CidadeAnoLetivoRepository;
import br.com.appetitegourmet.api.repositories.FeriadoRepository;
import br.com.appetitegourmet.api.repositories.PlanoAlimentarPrecoRepository;
import utils.ValidacaoConstantes;

@Service
public class CalendarioService {
    private final CalendarioExcecaoRepository calendarioExcecaoRepository;
    private final CidadeAnoLetivoRepository cidadeAnoLetivoRepository;
    private final FeriadoRepository feriadoRepository;
    private final PlanoAlimentarPrecoRepository planoAlimentarPrecoRepository;
    
    public CalendarioService(AnoLetivoRepository anoLetivoRepository, 
    						 FeriadoRepository feriadoRepository, 
    						 CidadeAnoLetivoRepository cidadeAnoLetivoRepository, 
    						 CalendarioExcecaoRepository calendarioExcecaoRepository, 
    						 PlanoAlimentarPrecoRepository planoAlimentarPrecoRepository) {
		this.calendarioExcecaoRepository = calendarioExcecaoRepository;
		this.cidadeAnoLetivoRepository = cidadeAnoLetivoRepository;
		this.feriadoRepository = feriadoRepository;
		this.planoAlimentarPrecoRepository = planoAlimentarPrecoRepository;
    }
        
    private List<Calendario> pegaCalendarioGeral(Long cidadeId, 
			    								AnoLetivo anoLetivo, 
			    								Date dataInicio, 
			    								Date dataFim) {
    	GregorianCalendar cal = new GregorianCalendar();
    	boolean util;
    	String observacao;
    	List <Feriado> todosFeriados;
    	List <Feriado> feriados;
    	Calendario calendario = null;
    	CidadeAnoLetivo cidadeAnoLetivo;
    	Optional <CidadeAnoLetivo> opt;
    	int tipoFeriado = 0;
    	Date data = null;
    	List<Calendario> retorno = new ArrayList<Calendario>();
    	
    	System.out.println("ANO_LETIVO = " + anoLetivo.toString());
    	
    	opt = cidadeAnoLetivoRepository.findByAnoLetivoIdAndCidadeId(anoLetivo.getId(), cidadeId);
    	 
    	if(opt.isPresent()) {
    		cidadeAnoLetivo = opt.get();
    	} else {
    		return null;
    	}
    	
    	cal.setTime(dataInicio);
    	
    	todosFeriados = feriadoRepository.findByDataBetween(dataInicio, dataFim);
    	
    	for(data = dataInicio; data.compareTo(dataFim) <= 0; ) {
    		
    		feriados = pegaFeriados(data, todosFeriados);
    		
			calendario = new Calendario();
        	calendario.setAnoLetivo(anoLetivo);
        	calendario.setData(data);
		
    		calendario.setCidade(cidadeAnoLetivo.getCidade());
    		if(calendario.getData().compareTo(cidadeAnoLetivo.getInicioAnoLetivo()) < 0) {
    			util = false;
        		observacao = "Férias";
    		} else if(calendario.getData().compareTo(cidadeAnoLetivo.getFimPrimeiroSemestre()) <= 0) {
    			util = true;
        		observacao = "Dia letivo";
    		} else if(calendario.getData().compareTo(cidadeAnoLetivo.getInicioSegundSemestre()) < 0) {
    			util = false;
        		observacao = "Férias";
    		} else if(calendario.getData().compareTo(cidadeAnoLetivo.getFimAnoLetivo()) <= 0) {
    			util = true;
        		observacao = "Dia letivo";
    		} else {
    			util = false;
        		observacao = "Férias";
    		}
    		
    		if(util) {
        		if(!feriados.isEmpty()) {
        			for(Feriado feriado : feriados) {
        				tipoFeriado = ValidacaoConstantes.getTipoFeriado(feriado);
        				if(tipoFeriado == ValidacaoConstantes.FERIADO_NACIONAL) {
		        			util = false;
		        			observacao = feriados.get(0).getObservacao();
		        			break;
        				} else if( (tipoFeriado == ValidacaoConstantes.FERIADO_ESTADUAL) &&
        						   (cidadeAnoLetivo.getCidade().getEstado().getEstado().compareTo(feriado.getEstado().getEstado()) == 0) ) {
        					util = false;
		        			observacao = feriados.get(0).getObservacao();
		        			break;
        				} else if( (tipoFeriado == ValidacaoConstantes.FERIADO_MUNICIPAL) &&
        						   (cidadeAnoLetivo.getCidade().getCidade().compareTo(feriado.getCidade().getCidade()) == 0) ) {
        					util = false;
		        			observacao = feriados.get(0).getObservacao();
		        			break;
        				}
        			}
        		} 
        		if(util) {
        			if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) {
        				util = false;
		        		observacao = "Domingo";
        			} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
        				util = false;
		        		observacao = "Sábado";
	        		}
        		}
    		}
    		calendario.setUtil(util);
    		calendario.setObservacao(observacao);
    		retorno.add(calendario);
    		cal.add(GregorianCalendar.DATE, 1);
    		data = new java.sql.Date(cal.getTimeInMillis());
    	}
    	return retorno;
    }
    
    private List<Feriado> pegaFeriados(Date data, List<Feriado> todosFeriados) {
    	List<Feriado> retorno = new ArrayList<Feriado>();
    	
    	for(Feriado feriado : todosFeriados) {
    		if(DateUtils.isSameDay(feriado.getData(), data)) {
    			retorno.add(feriado);
    		}
    	}
		return retorno;
	}

	private CalendarioExcecao pegaExcecao(List<CalendarioExcecao> listaCalendarioExcecao, 
										 Long turma_id, 
										 Long serie_id, 
									 	 Long plano_alimentar_id) {
    	CalendarioExcecao retorno = null;
    	int matches;
    	int matchesSelection;
    	
    	for(CalendarioExcecao calendarioExcecao : listaCalendarioExcecao) {
    		matches = 0;
        	matchesSelection = 0;
    		if(calendarioExcecao.getTurma() != null) {
    			if(calendarioExcecao.getTurma().getId() == turma_id) {
    				++matches;
    			} else {
    				continue;
    			}
    		}
    		if(calendarioExcecao.getSerie() != null) {
    			if(calendarioExcecao.getSerie().getId() == serie_id) {
    				++matches;
    			} else {
    				continue;
    			}
    		}
    		if(calendarioExcecao.getPlanoAlimentar() != null) {
    			if(calendarioExcecao.getPlanoAlimentar().getId() == plano_alimentar_id) {
    				++matches;
    			} else {
    				continue;
    			}
    		}
    		if(retorno == null) {
    			retorno = calendarioExcecao;
    		} else {
    			if(retorno.getTurma() != null) {
    				++matchesSelection;
    			}
    			if(retorno.getSerie() != null) {
    				++matchesSelection;
    			}
    			if(retorno.getPlanoAlimentar() != null) {
    				++matchesSelection;
    			}
    			if(matches > matchesSelection) {
    				retorno = calendarioExcecao;
    			}
    		}
    	}
    	
    	return retorno;
    }
        
    public Date pegaProximoDiaUtil(Date data, 
								   CidadeAnoLetivo cidadeAnoLetivo,
								   Long turma_id,
								   Long unidade_id,
								   Long serie_id,
								   Long plano_alimentar_id) {
    	Date retorno = null;
    	Date utilGeral = null;
    	Date utilExcecao = null;
    	List<CalendarioExcecao> todosCalendarioExcecao;
    	Date diaFinal;
    	GregorianCalendar cal = new GregorianCalendar();
    	List<CalendarioExcecao> listaCalendarioExcecao;
    	
    	if(data.compareTo(cidadeAnoLetivo.getFimAnoLetivo()) > 0) {
    		utilGeral = null;
    	} else if(data.compareTo(cidadeAnoLetivo.getInicioSegundSemestre()) >= 0) {
    		utilGeral = data;
    	} else if(data.compareTo(cidadeAnoLetivo.getFimPrimeiroSemestre()) > 0) {
    		utilGeral = cidadeAnoLetivo.getInicioSegundSemestre();
    	} else if(data.compareTo(cidadeAnoLetivo.getInicioAnoLetivo()) >= 0) {
    		utilGeral = data;
    	} else {
    		utilGeral = cidadeAnoLetivo.getInicioAnoLetivo();
    	}
    	
    	cal.setTime(data);
    	cal.set( GregorianCalendar.DATE, 31 );
    	cal.set( GregorianCalendar.MONTH, 11 );
    	diaFinal = new java.sql.Date(cal.getTimeInMillis());
    	todosCalendarioExcecao = calendarioExcecaoRepository.findByAnoLetivoIdAndUnidadeIdAndDataBetween(cidadeAnoLetivo.getAnoLetivo().getId(), 
    																									 unidade_id, 
    																									 data, 
    																									 diaFinal);
    	for(CalendarioExcecao excecao: todosCalendarioExcecao) {
    		if(excecao.isUtil()) {
    			listaCalendarioExcecao = new ArrayList<CalendarioExcecao>();
    			listaCalendarioExcecao.add(excecao);
    			if(pegaExcecao(listaCalendarioExcecao, 
						turma_id, 
						serie_id, 
						plano_alimentar_id) != null) {
    				utilExcecao = excecao.getData();
    				break;
    			}
    		}
    	}
    	
    	if(utilGeral != null && utilExcecao != null) {
    		if(utilGeral.compareTo(utilExcecao) > 0) {
    			retorno = utilExcecao;
    		} else {
    			retorno = utilGeral;
    		}
    	} else if((utilGeral != null) || (utilGeral != null)) {
    		if(utilExcecao != null) {
    			retorno = utilExcecao;
    		} else {
    			retorno = utilGeral;
    		}
    	}
    	
    	return retorno;
    }
    
    public int pegaAno(String data) {
    	int retorno = 0;
    	
    	retorno = Integer.parseInt(data.substring(0, 4));
    	
    	return retorno;
    }
    
    public int pegaMes(String data) {
    	int retorno = 0;
    	
    	retorno = Integer.parseInt(data.substring(5, 7));
    	
    	return retorno;
    }
    
    public int pegaDia(String data) {
    	int retorno = 0;
    	
    	retorno = Integer.parseInt(data.substring(8, 10));
    	
    	return retorno;
    }
    
    public BigDecimal calculaTotalMes2(String dataInicial,
    								   String dataFinal,
									   boolean seg,
									   boolean ter,
									   boolean qua,
									   boolean qui,
									   boolean sex,
									   boolean sab,
									   boolean dom,
									   Long plano_alimentar_preco_id) {
    	BigDecimal valor;
    	List<Calendario> listaCalendario = null;
    	List<CalendarioExcecao> listaCalendarioExcecao = null;
    	CalendarioExcecao calendarioExcecao;
    	GregorianCalendar cal = new GregorianCalendar();
    	int ano;
    	int ultimoDia;
    	int numeroDiasUteis = 0;
    	AnoLetivo anoLetivo;
    	List<CalendarioExcecao> todosCalendarioExcecao;
    	Optional<PlanoAlimentarPreco> optPAP = planoAlimentarPrecoRepository.findById(plano_alimentar_preco_id);
    	PlanoAlimentarPreco pap;
    	Long cidadeId;
    	BigDecimal valorDia;
    	Turma turma;
    	Long turma_id;
    	Long unidade_id;
    	Long serie_id;
    	Date data;
    	CidadeAnoLetivo cidadeAnoLetivo = null;
    	Optional <CidadeAnoLetivo> opt;
    	Date diaInicial;
		Date diaFinal;
		Date dia;
    	
    	if(!optPAP.isPresent()) {
    		return null;
    	}    	
    	pap = optPAP.get();
    	anoLetivo = pap.getTurmaAnoLetivo().getAnoLetivo();
    	ano = Integer.parseInt(anoLetivo.getAno());
    	valorDia = pap.getPrecoDia();
    	turma = pap.getTurmaAnoLetivo().getTurma();
    	turma_id = turma.getId();
    	unidade_id = turma.getAnoSerieUnidadeTurno().getAnoSerieUnidade().getUnidade().getId();
    	cidadeId = turma.getAnoSerieUnidadeTurno().getAnoSerieUnidade().getUnidade().getCidade().getId();
    	serie_id = turma.getAnoSerieUnidadeTurno().getAnoSerieUnidade().getAnoSerie().getSerie().getId();
    	
    	System.out.println("Valor dia = " + valorDia.doubleValue());
    	
    	System.out.println("dataInicial(" + dataInicial + ")");
    	System.out.println("dataFinal(" + dataFinal + ")");
    	
    	if(dataInicial.contains("N")) {
    		if(cal.get(GregorianCalendar.YEAR) != ano) {
    			// Se o ano que estamos é diferente do ano em questão para o calculo, 
    			//  teremos que pegar a partir do primeiro dia do ano e ver 
    			//  qual o dia util mais proximo
    			cal.set( GregorianCalendar.YEAR, ano );
    	    	cal.set( GregorianCalendar.HOUR, 0 );
    	    	cal.set( GregorianCalendar.MINUTE, 0 );
    	    	cal.set( GregorianCalendar.SECOND, 0 );
    	    	cal.set( GregorianCalendar.DATE, 1 );
    	    	cal.set( GregorianCalendar.MONTH, 0 );
    			data = new java.sql.Date(cal.getTimeInMillis());
    		} else { 
    			// Se estamos no mesmo ano do calculo, pegamos o dia de amanha e 
    			//  vemos o dia util mais proximo;
    			cal.add(GregorianCalendar.DATE, 1);
    			data = new java.sql.Date(cal.getTimeInMillis());
    		}
    		
    		opt = cidadeAnoLetivoRepository.findByAnoLetivoIdAndCidadeId(anoLetivo.getId(), cidadeId);
       	 
        	if(!opt.isPresent()) {
        		return null;
        	}
        	cidadeAnoLetivo = opt.get();
        	
        	diaInicial = pegaProximoDiaUtil(data, 
        			 						cidadeAnoLetivo, 
        			 						turma_id, 
        			 						unidade_id, 
        			 						serie_id, 
        			 						pap.getPlanoAlimentar().getId());
        	
        	if(diaInicial == null) {
        		return BigDecimal.ZERO;
        	}
        	
        	cal.setTime(diaInicial);
        	ultimoDia = cal.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );
        	cal.set( GregorianCalendar.DATE, ultimoDia );
        	diaFinal = new java.sql.Date(cal.getTimeInMillis());
    	} else {
    		System.out.println("PEGANDO AS DATAS");
    		cal.set( GregorianCalendar.YEAR, pegaAno(dataInicial) );
	    	cal.set( GregorianCalendar.HOUR, 0 );
	    	cal.set( GregorianCalendar.MINUTE, 0 );
	    	cal.set( GregorianCalendar.SECOND, 0 );
	    	cal.set( GregorianCalendar.DATE, pegaDia(dataInicial) );
	    	cal.set( GregorianCalendar.MONTH, pegaMes(dataInicial) - 1 );
			diaInicial = new java.sql.Date(cal.getTimeInMillis());
			
			cal.set( GregorianCalendar.YEAR, pegaAno(dataFinal) );
	    	cal.set( GregorianCalendar.HOUR, 0 );
	    	cal.set( GregorianCalendar.MINUTE, 0 );
	    	cal.set( GregorianCalendar.SECOND, 0 );
	    	cal.set( GregorianCalendar.DATE, pegaDia(dataFinal) );
	    	cal.set( GregorianCalendar.MONTH, pegaMes(dataFinal) - 1 );
			diaFinal = new java.sql.Date(cal.getTimeInMillis());
    	}

    	System.out.println("Ciade Id = " + cidadeId);
    	System.out.println("Ano Letivo Id = " + anoLetivo.getId());
    	System.out.println("Data Inicial = " + diaInicial.toString());
    	System.out.println("Data Final = " + diaFinal.toString());

    	//listaCalendario = pegaCalendarioGeral(cidadeId, anoLetivo, diaInicial, diaFinal);

    	todosCalendarioExcecao = calendarioExcecaoRepository.findByAnoLetivoIdAndUnidadeIdAndDataBetween(anoLetivo.getId(), unidade_id, diaInicial, diaFinal);

    	//System.out.println("Numero de Dias = " + listaCalendario.size());
    	for(dia = diaInicial; dia.compareTo(diaFinal) <= 0;) {
    		System.out.println("Data para calculo = " + dia.toString());
    		Date diaAtual = dia;
    		cal.setTime(diaAtual);
    		cal.add(GregorianCalendar.DATE, 1);
    		dia = new java.sql.Date(cal.getTimeInMillis());
    	//for(Calendario calendario : listaCalendario) {
    		listaCalendarioExcecao = pegaListaCalendarioExcecao(diaAtual, todosCalendarioExcecao);

    		calendarioExcecao = pegaExcecao(listaCalendarioExcecao, 
    				turma_id, 
    				serie_id, 
    				pap.getPlanoAlimentar().getId());

    		if (calendarioExcecao == null) {
    			listaCalendario = pegaCalendarioGeral(cidadeId, anoLetivo, diaAtual, diaAtual);
    			System.out.println("Calendario = " + listaCalendario.toString());
    			if(listaCalendario.size() == 0) {
    				continue;
    			}
    			Calendario calendario = listaCalendario.get(0);
    			if(calendario.isUtil()) {
    				cal.setTime(calendario.getData());
    				System.out.println("Data = " + calendario.getData().toString());
    				if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.MONDAY) {
    					if(seg) {
    						System.out.println("Segunda");
    						++numeroDiasUteis;
    					}
    				} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.TUESDAY) {
    					if(ter) {
    						System.out.println("Terça");
    						++numeroDiasUteis;
    					}
    				} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.WEDNESDAY) {
    					if(qua) {
    						System.out.println("Quarta");
    						++numeroDiasUteis;
    					}
    				} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.THURSDAY) {
    					if(qui) {
    						System.out.println("Quinta");
    						++numeroDiasUteis;
    					}
    				} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.FRIDAY) {
    					if(sex) {
    						System.out.println("Sexta");
    						++numeroDiasUteis;
    					}
    				} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY) {
    					if(sab) {
    						System.out.println("Sabado");
    						++numeroDiasUteis;
    					}
    				} else if(cal.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY) {
    					if(dom) {
    						System.out.println("Domingo");
    						++numeroDiasUteis;
    					}
    				}
    			}
    		} else {

    			if(calendarioExcecao.isUtil()) {
    				System.out.println("Excecao dia letivo - " + calendarioExcecao.toString());
    				++numeroDiasUteis;
    			} else {
    				System.out.println("Excecao dia nao letivo - " + calendarioExcecao.toString());
    			}
    		}
    	}

    	System.out.println("Numero de Dias Uteis = " + numeroDiasUteis);

    	valor = valorDia.multiply(BigDecimal.valueOf(numeroDiasUteis));

    	System.out.println("Valor Total = " + valor.doubleValue());

    	return valor;
	}
    
	private List<CalendarioExcecao> pegaListaCalendarioExcecao(Date data,
			List<CalendarioExcecao> todosCalendarioExcecao) {
		List<CalendarioExcecao> retorno = new ArrayList<CalendarioExcecao>();
		
		for(CalendarioExcecao calendario : todosCalendarioExcecao) {
			if(DateUtils.isSameDay(calendario.getData(), data)) {
				retorno.add(calendario);
			}
		}
		
		
		return retorno;
	}
}