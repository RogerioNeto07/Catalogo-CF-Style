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

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tipo;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.TipoService;

@RestController
@RequestMapping("/tipos")
public class TipoController {
    
    @Autowired
    private TipoService tipoService;

    // -----------------------------
    // LISTAR TODOS
    // -----------------------------
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tipo> listar() {
        return tipoService.listar();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tipo buscar(@PathVariable Long id) {
        return tipoService.buscar(id);
    }


    // -----------------------------
    // CRIAR TIPO
    // -----------------------------
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tipo criar(@RequestBody Tipo tipo) {
        return tipoService.criar(tipo);
    }

    // -----------------------------
    // ATUALIZAR TIPO
    // -----------------------------
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tipo atualizar(@PathVariable Long id,
                                        @RequestBody Tipo tipo) {
        return tipoService.atualizar(id, tipo);
    }

    // -----------------------------
    // DELETAR TIPO
    // -----------------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tipoService.deletar(id);
    }

}
