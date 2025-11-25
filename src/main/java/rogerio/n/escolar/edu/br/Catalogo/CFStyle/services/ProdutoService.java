package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoCreateDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto.ProdutoResponseDTO;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Produto;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.CorRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.ProdutoRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TagRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TipoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CorRepository corRepository;

    // -----------------------------
    // LISTAR TODOS
    // -----------------------------
    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    public ProdutoResponseDTO buscar(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return toResponse(produto);
    }

    // -----------------------------
    // CRIAR PRODUTO
    // -----------------------------
    public ProdutoResponseDTO criar(ProdutoCreateDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setAtivo(true);

        produto.setTipo(tipoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado")));

        List<Cor> cores = corRepository.findAllById(dto.getCoresIds());
        produto.setCores(cores);

        List<Tag> tags = tagRepository.findAllById(dto.getTagsIds());
        produto.setTags(tags);

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    // -----------------------------
    // ATUALIZAR PRODUTO
    // -----------------------------
    public ProdutoResponseDTO atualizar(Long id, ProdutoCreateDTO dto) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        produto.setTipo(tipoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo não encontrado")));

        produto.setCores(corRepository.findAllById(dto.getCoresIds()));

        produto.setTags(tagRepository.findAllById(dto.getTagsIds()));

        produtoRepository.save(produto);

        return toResponse(produto);
    }

    // -----------------------------
    // DELETAR / DESATIVAR PRODUTO
    // -----------------------------
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    // -----------------------------
    // LISTAR POR TIPO
    // -----------------------------
    public List<ProdutoResponseDTO> listarPorTipo(Long tipoId) {
        return produtoRepository.findByTipoId(tipoId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // -----------------------------
    // LISTAR POR TAG
    // -----------------------------
    public List<ProdutoResponseDTO> listarPorTag(Long tagId) {
        return produtoRepository.findByTagId(tagId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // -----------------------------
    // CONVERTER PARA DTO DE RESPOSTA
    // -----------------------------
    private ProdutoResponseDTO toResponse(Produto p) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();

        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        dto.setPreco(p.getPreco());
        dto.setQuantidade(p.getQuantidade());
        dto.setAtivo(p.isAtivo());
        dto.setFotos(p.getFotos());
        dto.setOpcoes(p.getOpcoes());
        dto.setCriadoEm(p.getCriadoEm());
        dto.setAtualizadoEm(p.getAtualizadoEm());

        dto.setTipoId(p.getTipo().getId());
        dto.setTipoNome(p.getTipo().getNome());

        // cores
        dto.setCoresIds(p.getCores().stream().map(c -> c.getId()).toList());
        dto.setCoresNomes(p.getCores().stream().map(c -> c.getNome()).toList());

        // tags
        dto.setTagsIds(p.getTags().stream().map(t -> t.getId()).toList());
        dto.setTagsNomes(p.getTags().stream().map(t -> t.getNome()).toList());

        return dto;
    }
}
