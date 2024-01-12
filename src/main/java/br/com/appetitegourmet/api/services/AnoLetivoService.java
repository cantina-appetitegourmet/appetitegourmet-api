package br.com.appetitegourmet.api.services;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoLetivo;
import br.com.appetitegourmet.api.models.Cidade;
import br.com.appetitegourmet.api.models.CidadeAnoLetivo;
import br.com.appetitegourmet.api.repositories.AnoLetivoRepository;
import br.com.appetitegourmet.api.repositories.CidadeAnoLetivoRepository;

@Service
public class AnoLetivoService {
    private final AnoLetivoRepository anoLetivoRepository;
    private final CidadeAnoLetivoRepository cidadeAnoLetivoRepository;
    
    public AnoLetivoService(AnoLetivoRepository anoLetivoRepository, 
    						CidadeAnoLetivoRepository cidadeAnoLetivoRepository) {
        this.anoLetivoRepository = anoLetivoRepository;
        this.cidadeAnoLetivoRepository = cidadeAnoLetivoRepository;
    }
    
    public List<AnoLetivo> listarAnoLetivos() {
        return anoLetivoRepository.findAll();
    }
    
    public AnoLetivo buscarAnoLetivoPorId(Long id) {
        return anoLetivoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ano Letivo não encontrado"));
    }
    
    public List<AnoLetivo> buscarAnosLetivosAtivos() {
        return anoLetivoRepository.queryByAtivoIsTrue();
    }
    
    public AnoLetivo salvarAnoLetivo(AnoLetivo anoLetivo) {
        return anoLetivoRepository.save(anoLetivo);
    }
    
    public void excluirAnoLetivo(Long id) {
        anoLetivoRepository.deleteById(id);
    }
    
    public void criaAnoLetivoCidade(AnoLetivo anoLetivo, 
    								Cidade cidade,
    								Date inicioAnoLetivo,
    								Date fimPrimeiroSemestre,
    								Date inicioSegundSemestre,
    								Date fimAnoLetivo) {
    	
    	CidadeAnoLetivo cidadeAnoLetivo = new CidadeAnoLetivo();
    	cidadeAnoLetivo.setAnoLetivo(anoLetivo);
    	cidadeAnoLetivo.setCidade(cidade);
    	cidadeAnoLetivo.setFimAnoLetivo(fimAnoLetivo);
    	cidadeAnoLetivo.setFimPrimeiroSemestre(fimPrimeiroSemestre);
    	cidadeAnoLetivo.setInicioAnoLetivo(inicioAnoLetivo);
    	cidadeAnoLetivo.setInicioSegundSemestre(inicioSegundSemestre);
    	cidadeAnoLetivoRepository.save(cidadeAnoLetivo);
    }
}