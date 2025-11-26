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

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {
    
    @Autowired
    private TagService tagService;

    // -----------------------------
    // LISTAR TODOS
    // -----------------------------
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> listar() {
        return tagService.listar();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag buscar(@PathVariable Long id) {
        return tagService.buscar(id);
    }


    // -----------------------------
    // CRIAR TAG
    // -----------------------------
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag criar(@RequestBody Tag tag) {
        return tagService.criar(tag);
    }

    // -----------------------------
    // ATUALIZAR TAG
    // -----------------------------
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tag atualizar(@PathVariable Long id,
                                        @RequestBody Tag tag) {
        return tagService.atualizar(id, tag);
    }

    // -----------------------------
    // DELETAR TAG
    // -----------------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tagService.deletar(id);
    }

}
