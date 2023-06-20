package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Colegio;
import br.com.appetitegourmet.api.models.Empresa;
import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.repositories.ColegioRepository;
import br.com.appetitegourmet.api.repositories.EmpresaRepository;
import br.com.appetitegourmet.api.repositories.EnderecoRepository;
import br.com.appetitegourmet.api.repositories.UnidadeRepository;

@Service
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    private final ColegioRepository colegioRepository;
    private final EmpresaRepository empresaRepository;
    private final EnderecoRepository enderecoRepository;
    
    public UnidadeService(UnidadeRepository unidadeRepository, 
    					  ColegioRepository colegioRepository, 
    					  EmpresaRepository empresaRepository, 
    					  EnderecoRepository enderecoRepository) {
        this.unidadeRepository = unidadeRepository;
		this.colegioRepository = colegioRepository;
		this.empresaRepository = empresaRepository;
		this.enderecoRepository = enderecoRepository;
    }
    
    public List<Unidade> listarUnidades() {
        return unidadeRepository.findAll();
    }
    
    public List<Unidade> listarUnidadesPorColegio(Long colegioId) {
        return unidadeRepository.findByColegioId(colegioId);
    }
    
    public List<Unidade> listarUnidadesPorEmpresa(Long empresaId) {
        return unidadeRepository.findByEmpresaId(empresaId);
    }
    
    public List<Unidade> listarUnidadesPorEmpresaEPorColegio(Long empresaId, Long colegioId) {
        return unidadeRepository.findByEmpresaIdAndColegioId(empresaId, colegioId);
    }
    
    public Unidade buscarUnidadePorId(Long id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Unidade não encontrada"));
    }
    
    public Unidade salvarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }
    
    public void excluirUnidade(Long id) {
        unidadeRepository.deleteById(id);
    }

    public Unidade editarUnidade(Long id, Unidade unidade) {
    	Optional<Unidade> optionalUnidade = unidadeRepository.findById(id);
    	if (optionalUnidade.isPresent()) {
    		Unidade novaUnidade = optionalUnidade.get();

    		if(!unidade.getNome_unidade_escolar().isBlank() && 
    				!unidade.getNome_unidade_escolar().isEmpty())
    		{
    			novaUnidade.setNome_unidade_escolar(unidade.getNome_unidade_escolar());
    		}
    		if(!unidade.getNome_unidade_negocio().isBlank() && 
    				!unidade.getNome_unidade_negocio().isEmpty())
    		{
    			novaUnidade.setNome_unidade_negocio(unidade.getNome_unidade_negocio());
    		}
    		if(unidade.getAtivo() != null)
    		{
    			novaUnidade.setAtivo(unidade.getAtivo());
    		}
    		if(unidade.getColegio() != null)
    		{
    			Optional<Colegio> optionalColegio = 
    					colegioRepository.findById(unidade.getColegio().getId());
    			if (optionalColegio.isPresent()) {
    				Colegio novoColegio = optionalColegio.get();
    				novaUnidade.setColegio(novoColegio);
    			} else {
    				throw new NoSuchElementException("Colégio não encontrado");
    			}
    		}
    		if(unidade.getEmpresa() != null)
    		{
    			Optional<Empresa> optionalEmpresa = 
    					empresaRepository.findById(unidade.getEmpresa().getId());
    			if (optionalEmpresa.isPresent()) {
    				Empresa novaEmpresa = optionalEmpresa.get();
    				novaUnidade.setEmpresa(novaEmpresa);
    			} else {
    				throw new NoSuchElementException("Empresa não encontrada");
    			}
    		}
    		if(unidade.getEndereco() != null)
    		{
    			if(unidade.getEndereco().getId() != null) {
	    			Optional<Endereco> optionalEndereco = 
	    					enderecoRepository.findById(unidade.getEndereco().getId());
	    			if (optionalEndereco.isPresent()) {
	    				Endereco novoEndereco = optionalEndereco.get();
	    				novaUnidade.setEndereco(novoEndereco);
	    			} else {
	    				throw new NoSuchElementException("Endereço não encontrado");
	    			}
    			} else {
    				novaUnidade.setEndereco(unidade.getEndereco());
    			}
    		}
    		return unidadeRepository.save(novaUnidade);
    	} else {
    		return null;
    	}
    }
}
