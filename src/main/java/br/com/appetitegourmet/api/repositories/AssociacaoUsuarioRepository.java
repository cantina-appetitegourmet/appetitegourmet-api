package br.com.appetitegourmet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.AssociacaoUsuario;

public interface AssociacaoUsuarioRepository extends JpaRepository<AssociacaoUsuario, Long> {

	AssociacaoUsuario findByUsuarioIdAndRoleId(Long usuarioId, Long roleId);
}
