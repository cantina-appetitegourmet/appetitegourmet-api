package br.com.appetitegourmet.api.spring.login.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LostPasswordRequest {
    @NotBlank
    private String username;
}
