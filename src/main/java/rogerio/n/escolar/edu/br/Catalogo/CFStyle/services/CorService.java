package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.CorRepository;

@Service
public class CorService {
    
    @Autowired
    private CorRepository corRepository; 

    // -----------------------------
    // LISTAR TODAS AS CORES
    // -----------------------------
    public List<Cor> listar() {
        return corRepository.findAll();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    public Cor buscar(Long id) {
        return corRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cor não encontrada"
            ));
    }

    // -----------------------------
    // CRIAR COR
    // -----------------------------
    public Cor criar(Cor cor){
        return corRepository.save(cor);
    }

    // -----------------------------
    // DELETAR COR
    // -----------------------------
    public void deletar(Long id) {
        Cor cor = corRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cor não encontrada"
            ));
        
        corRepository.delete(cor);
    }

    // -----------------------------
    // ATUALIZAR COR
    // -----------------------------
    public Cor atualizar(Long id, Cor corAtualizada) {
    Cor cor = corRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cor não encontrada"
            ));
    cor.setNome(corAtualizada.getNome());
    return corRepository.save(cor);
}
}


