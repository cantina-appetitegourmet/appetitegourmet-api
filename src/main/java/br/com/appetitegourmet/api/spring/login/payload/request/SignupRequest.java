package br.com.appetitegourmet.api.spring.login.payload.request;

import java.util.HashSet;
import java.util.Set;

import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.spring.login.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 300)
    private String username;
 
    @NotBlank
    @Size(max = 300)
    @Email
    private String email;

    @NotBlank
    private Set<Role> roles = new HashSet<>();
    
    @NotBlank
    @Size(min = 6, max = 40)
    @JsonIgnore
    private String password;
    @NotBlank
    private Pessoa pessoa;
}
