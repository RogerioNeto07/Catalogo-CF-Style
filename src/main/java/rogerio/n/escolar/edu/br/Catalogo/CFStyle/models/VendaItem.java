package rogerio.n.escolar.edu.br.Catalogo.CFStyle.models;

import java.beans.Transient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "venda_item")
public class VendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    private Double preco_unitario;
    private int quantidade;

    @Transient
    public Double getSubtotal() {
        if (preco_unitario == null) return 0.0;
        return preco_unitario * quantidade;
    }
}
