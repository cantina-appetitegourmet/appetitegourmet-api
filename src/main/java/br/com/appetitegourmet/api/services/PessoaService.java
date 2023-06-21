package br.com.appetitegourmet.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.dto.PessoaRequest;
import br.com.appetitegourmet.api.exception.PessoaNaoEncontradaException;
import br.com.appetitegourmet.api.mapper.PessoaMapper;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.repositories.PessoaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaService {
    private final PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada com o ID: " + id));
    }

    public Pessoa editarPessoa(Long id, PessoaRequest pessoaRequest) throws PessoaNaoEncontradaException {
        Pessoa pessoaExistente = buscarPessoaPorId(id);
        pessoaMapper.atualizarPessoaFromRequest(pessoaRequest, pessoaExistente);
        salvarPessoa(pessoaExistente);
        return pessoaRepository.save(pessoaExistente);
    }

}
