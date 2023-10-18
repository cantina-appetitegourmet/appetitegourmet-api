package br.com.appetitegourmet.api.models;

import br.com.appetitegourmet.api.spring.login.models.Role;
import br.com.appetitegourmet.api.spring.login.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="associacao_usuario", 
		uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "role_id"})
    })
public class AssociacaoUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	private User usuario;
	
	@ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
	private Role role;
	
	@Column(name="associado_id", nullable = false, unique = true)
	private Integer associado_id;
}
