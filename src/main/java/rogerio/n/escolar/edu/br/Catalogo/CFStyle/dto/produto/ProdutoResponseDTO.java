package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private Boolean ativo;
    private String fotos;
    private String opcoes;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    private Long usuarioId;
    private String usuarioNome;

    private Long tipoId;
    private String tipoNome;

    private List<Long> coresIds;
    private List<String> coresNomes;

    private List<Long> tagsIds;
    private List<String> tagsNomes;
}
