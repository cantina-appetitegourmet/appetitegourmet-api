package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Empresa;
import br.com.appetitegourmet.api.repositories.EmpresaRepository;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa buscarEmpresaPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Empresa não encontrada"));
    }

    public Empresa salvarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void excluirEmpresa(Long id) {
        empresaRepository.deleteById(id);
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
