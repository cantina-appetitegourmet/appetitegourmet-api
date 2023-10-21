package br.com.appetitegourmet.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.AssociacaoUsuario;

public interface AssociacaoUsuarioRepository extends JpaRepository<AssociacaoUsuario, Long> {

	Optional<AssociacaoUsuario> findByUsuarioIdAndRoleId(Long usuarioId, Integer roleId);
}
