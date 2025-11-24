package rogerio.n.escolar.edu.br.Catalogo.CFStyle.dto.produto;

import java.util.List;

import lombok.Data;

@Data
public class ProdutoUpdateDTO {
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private Boolean ativo;
    private List<Long> coresIds;
    private List<Long> tagsIds;
    private String fotos;
    private String opcoes;
}
