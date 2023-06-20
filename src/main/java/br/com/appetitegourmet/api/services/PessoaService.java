package br.com.appetitegourmet.api.services;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.repositories.PessoaRepository;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
}
