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
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.CorService;

@RestController
@RequestMapping("/cores")
public class CorController {
    
    @Autowired
    private CorService corService;

    // -----------------------------
    // LISTAR TODAS
    // -----------------------------
    @GetMapping
    @Operation(summary = "Lista todas as cores")
    @ResponseStatus(HttpStatus.OK)
    public List<Cor> listar() {
        return corService.listar();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Busca cor por ID")
    @ResponseStatus(HttpStatus.OK)
    public Cor buscar(@PathVariable Long id) {
        return corService.buscar(id);
    }


    // -----------------------------
    // CRIAR COR
    // -----------------------------
    @PostMapping
    @Operation(summary = "Cria uma cor")
    @ResponseStatus(HttpStatus.CREATED)
    public Cor criar(@RequestBody Cor cor) {
        return corService.criar(cor);
    }

    // -----------------------------
    // ATUALIZAR COR
    // -----------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma cor")
    @ResponseStatus(HttpStatus.OK)
    public Cor atualizar(@PathVariable Long id,
                                        @RequestBody Cor cor) {
        return corService.atualizar(id, cor);
    }

    // -----------------------------
    // DELETAR COR
    // -----------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma cor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        corService.deletar(id);
    }

}
