package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Serie;
import br.com.appetitegourmet.api.repositories.SerieRepository;

@Service
public class SerieService {
    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public List<Serie> listarSeries() {
        return serieRepository.findAll();
    }

    public Serie buscarSeriePorId(Long id) {
        return serieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Serie não encontrada"));
    }

    public Serie salvarSerie(Serie serie) {
        return serieRepository.save(serie);
    }

    public void excluirSerie(Long id) {
        serieRepository.deleteById(id);
    }

    public Serie editarSerie(Long id, Serie serie) {
        Optional<Serie> optionalSerie = serieRepository.findById(id);
        if (optionalSerie.isPresent()) {
            Serie novaSerie = optionalSerie.get();
            novaSerie.setNome(serie.getNome());
            return serieRepository.save(novaSerie);
        } else {
            return null;
        }
    }
}

