package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Endereco não encontrado"));
    }

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void excluirEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco editarEndereco(Long id, Endereco endereco) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        if (optionalEndereco.isPresent()) {
            Endereco novoEndereco = optionalEndereco.get();
            if(endereco.getBairro() != null) {
            	novoEndereco.setBairro(endereco.getBairro());
            }
            if(endereco.getCep() != null) {
            	novoEndereco.setCep(endereco.getCep());
            }
            if(endereco.getCidade() != null) {
            	novoEndereco.setCidade(endereco.getCidade());
            }
            if(endereco.getComplemento() != null) {
            	novoEndereco.setComplemento(endereco.getComplemento());
            }
            if(endereco.getLogradouro() != null) {
            	novoEndereco.setLogradouro(endereco.getLogradouro());
            }
            if(endereco.getNumero() != null) {
            	novoEndereco.setNumero(endereco.getNumero());
            }
            if(endereco.getUf() != null) {
            	novoEndereco.setUf(endereco.getUf());
            }
            return enderecoRepository.save(novoEndereco);
        } else {
            return null;
        }
    }
}
