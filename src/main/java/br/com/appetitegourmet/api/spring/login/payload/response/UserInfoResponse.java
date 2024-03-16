package br.com.appetitegourmet.api.spring.login.payload.response;

import br.com.appetitegourmet.api.models.Pessoa;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
@Getter
@Setter
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private Pessoa pessoa;
}
