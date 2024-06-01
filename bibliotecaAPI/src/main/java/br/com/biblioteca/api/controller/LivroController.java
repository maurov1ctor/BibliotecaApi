package br.com.biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.biblioteca.api.model.Livro;
import br.com.biblioteca.api.service.LivroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

   @GetMapping("/{id}")
    public ResponseEntity<Livro> procurarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroService.procurarPorId(id);
        return livro.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {
        return livroService.salvar(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
    return ResponseEntity.ok(livroService.atualizar(id, livroAtualizado));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        livroService.delete(id);
    }
}
