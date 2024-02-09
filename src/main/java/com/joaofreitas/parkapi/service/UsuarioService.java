package com.joaofreitas.parkapi.service;

import com.joaofreitas.parkapi.entity.Usuario;
import com.joaofreitas.parkapi.exception.EntityNotFoundException;
import com.joaofreitas.parkapi.exception.PasswordInvalidException;
import com.joaofreitas.parkapi.exception.UsernameUniqueViolationException;
import com.joaofreitas.parkapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username %s já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new
                        EntityNotFoundException(String.format("Username com o id %s não encontrado", id)));
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("Senhas não conferem.");
        }
        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)) {
            throw new PasswordInvalidException("Senha atual inválida.");
        }
        user.setPassword(novaSenha);
        return user;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();

    }
}
