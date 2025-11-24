package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoCreateDTO {
    @NotNull
    private long usuarioId;

    private long tipoId;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private Double preco;

    @NotNull
    private int quantidade;

    private List<Long> coresId;
    private List<Long> tagsId;
    private String fotos;
    private String opcoes;
}
