package br.com.appetitegourmet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
