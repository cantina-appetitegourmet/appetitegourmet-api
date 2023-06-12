package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.TipoContato;
import br.com.appetitegourmet.api.repositories.TipoContatoRepository;

@Service
public class TipoContatoService {
    private final TipoContatoRepository tipoContatoRepository;

    public TipoContatoService(TipoContatoRepository tipoContatoRepository) {
        this.tipoContatoRepository = tipoContatoRepository;
    }

    public List<TipoContato> listarTipoContatos() {
        return tipoContatoRepository.findAll();
    }

    public TipoContato buscarTipoContatoPorId(Long id) {
        return tipoContatoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tipo Contato não encontrado"));
    }

    public TipoContato salvarTipoContato(TipoContato tipoContato) {
        return tipoContatoRepository.save(tipoContato);
    }

    public void excluirTipoContato(Long id) {
        tipoContatoRepository.deleteById(id);
    }

    public TipoContato editarTipoContato(Long id, TipoContato tipoContato) {
        Optional<TipoContato> optionalTipoContato = tipoContatoRepository.findById(id);
        if (optionalTipoContato.isPresent()) {
            TipoContato novaTipoContato = optionalTipoContato.get();
            return tipoContatoRepository.save(novaTipoContato);
        } else {
            return null;
        }
    }
}