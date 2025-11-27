package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {
    
    @Autowired
    private TagService tagService;

    // -----------------------------
    // LISTAR TODAS
    // -----------------------------
    @GetMapping
    @Operation(summary = "Lista todas as tags")
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> listar() {
        return tagService.listar();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Busca tag por ID")
    @ResponseStatus(HttpStatus.OK)
    public Tag buscar(@PathVariable Long id) {
        return tagService.buscar(id);
    }


    // -----------------------------
    // CRIAR TAG
    // -----------------------------
    @PostMapping
    @Operation(summary = "Cria uma tag")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag criar(@RequestBody Tag tag) {
        return tagService.criar(tag);
    }

    // -----------------------------
    // ATUALIZAR TAG
    // -----------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma tag")
    @ResponseStatus(HttpStatus.OK)
    public Tag atualizar(@PathVariable Long id,
                                        @RequestBody Tag tag) {
        return tagService.atualizar(id, tag);
    }

    // -----------------------------
    // DELETAR TAG
    // -----------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma tag")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tagService.deletar(id);
    }

}
