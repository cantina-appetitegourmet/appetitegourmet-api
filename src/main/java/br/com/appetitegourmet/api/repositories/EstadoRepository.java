package br.com.appetitegourmet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>  {

}
