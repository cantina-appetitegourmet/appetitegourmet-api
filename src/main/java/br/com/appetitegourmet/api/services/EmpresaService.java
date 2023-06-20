package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Empresa;
import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.repositories.EmpresaRepository;
import br.com.appetitegourmet.api.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EnderecoRepository enderecoRepository;

    public EmpresaService(EmpresaRepository empresaRepository, 
    					  EnderecoRepository enderecoRepository) {
        this.empresaRepository = empresaRepository;
		this.enderecoRepository = enderecoRepository;
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada"));
    }
    
    @Transactional
    public Empresa salvarEmpresa(Empresa empresa) {
    	if(empresa.getEndereco() != null) {
    		Endereco retorno;
    		retorno = enderecoRepository.save(empresa.getEndereco());
    		if(retorno == null) {
    			new Exception("Falha ao inserir o endereço");
    		}
    		empresa.setEndereco(retorno);
    	}
        return empresaRepository.save(empresa);
    }
    
    @Transactional
    public void excluirEmpresa(Long id) {
    	Empresa retorno = empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada"));
        empresaRepository.deleteById(id);
        if(retorno.getEndereco() != null) {
    		enderecoRepository.deleteById(retorno.getEndereco().getId());
    	}
    }

    public Empresa editarEmpresa(Long id, Empresa empresa) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa novaEmpresa = optionalEmpresa.get();
            return empresaRepository.save(novaEmpresa);
        } else {
            return null;
        }
    }
}
