package com.joaofreitas.parkapi.web.controller;

import com.joaofreitas.parkapi.entity.Usuario;
import com.joaofreitas.parkapi.service.UsuarioService;
import com.joaofreitas.parkapi.web.dto.UsuarioCreateDto;
import com.joaofreitas.parkapi.web.dto.UsuarioResponseDto;
import com.joaofreitas.parkapi.web.dto.UsuarioSenhaDto;
import com.joaofreitas.parkapi.web.dto.mapper.UsuarioMapper;
import com.joaofreitas.parkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Operation(summary = "Atualizar senha", description = "Atualizar senha", responses = {@ApiResponse(responseCode = "201", description = "Senha atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema())), @ApiResponse(responseCode = "400", description = "Recurso não processados por dados de entrada inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))), @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class)))})

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário", responses = {@ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))), @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))), @ApiResponse(responseCode = "422", description = "Recurso não processados por dados de entrada inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Recuperar usuário pelo id", description = "Recuperar usuário pelo id", responses = {@ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))), @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Recuperar lista de usuário", description = "Recuperar lista de usuário", responses = {@ApiResponse(responseCode = "200", description = "Lista de usuários recuperada com sucesso", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))))})
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }


}