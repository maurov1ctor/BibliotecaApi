package br.com.biblioteca.api.controller;

import br.com.biblioteca.api.dto.request.UsuarioRequestDTO;
import br.com.biblioteca.api.dto.response.UsuarioResponseDTO;
import br.com.biblioteca.api.model.Usuario;
import br.com.biblioteca.api.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDTO save(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.save(usuarioRequestDTO);
    }

    @GetMapping
    public List<UsuarioResponseDTO> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Usuario> procurarPorId(@PathVariable Long userId) {
        Optional<Usuario> usuario = usuarioService.procurarPorId(userId);
        return usuario.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId){
        usuarioService.delete(userId);
    }

}
