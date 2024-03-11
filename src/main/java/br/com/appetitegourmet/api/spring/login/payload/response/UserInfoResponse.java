package br.com.appetitegourmet.api.spring.login.payload.response;

import br.com.appetitegourmet.api.models.Pessoa;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
@Data
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
}
