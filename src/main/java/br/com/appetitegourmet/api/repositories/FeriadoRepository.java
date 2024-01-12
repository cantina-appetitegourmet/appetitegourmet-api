package br.com.appetitegourmet.api.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Feriado;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, Long>  {

	List<Feriado> findByData(Date data);
}
