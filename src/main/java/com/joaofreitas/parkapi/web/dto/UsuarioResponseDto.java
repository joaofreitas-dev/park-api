package com.joaofreitas.parkapi.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioResponseDto {
    private Long id;
    private String username;
    private String role;
}
