package com.joaofreitas.parkapi.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioCreateDto {
    private String username;
    private String password;
}
