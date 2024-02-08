package com.joaofreitas.parkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioSenhaDto {
    @NotBlank
    @Size(min = 6, max = 8)
    private String senhaAtual;
    @NotBlank
    @Size(min = 6, max = 8)
    private String novaSenha;
    @NotBlank
    @Size(min = 6, max = 8)
    private String confirmaSenha;
}
