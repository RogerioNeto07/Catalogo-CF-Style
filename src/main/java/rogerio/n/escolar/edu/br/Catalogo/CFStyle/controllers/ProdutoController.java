package rogerio.n.escolar.edu.br.Catalogo.CFStyle.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    // -----------------------------
    // LISTAR TODOS
    // -----------------------------
    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return produtoService.listar();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    // -----------------------------
    // CRIAR PRODUTO
    // -----------------------------
    @PostMapping
    public ProdutoResponseDTO criar(@RequestBody ProdutoCreateDTO dto) {
        return produtoService.criar(dto);
    }

    // -----------------------------
    // ATUALIZAR PRODUTO
    // -----------------------------
    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id,
                                        @RequestBody ProdutoCreateDTO dto) {
        return produtoService.atualizar(id, dto);
    }

    // -----------------------------
    // DELETAR PRODUTO
    // -----------------------------
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }


    // -----------------------------
    // BUSCAR POR TIPO
    // -----------------------------
    @GetMapping("/tipo/{tipoId}")
    public List<ProdutoResponseDTO> listarPorTipo(@PathVariable Long tipoId) {
        return produtoService.listarPorTipo(tipoId);
    }

    // -----------------------------
    // BUSCAR POR TAG
    // -----------------------------
    @GetMapping("/tag/{tagId}")
    public List<ProdutoResponseDTO> listarPorTag(@PathVariable Long tagId) {
        return produtoService.listarPorTag(tagId);
    }
}

