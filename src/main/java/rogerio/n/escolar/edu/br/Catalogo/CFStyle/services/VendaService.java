package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Venda;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.VendaRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda salvar(Venda venda) {
        
        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }
}
