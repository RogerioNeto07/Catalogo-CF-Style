package rogerio.n.escolar.edu.br.Catalogo.CFStyle.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "venda")

public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String cliente_nome;
    private String cliente_contato;
    private Double total;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<VendaItem> itens;

    @Column(name = "data_venda")
    private LocalDateTime data_venda;

    @PrePersist
    public void onCreate() {
        data_venda = LocalDateTime.now();
    }



}