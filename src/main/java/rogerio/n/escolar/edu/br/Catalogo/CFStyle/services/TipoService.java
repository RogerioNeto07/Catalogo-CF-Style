package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tipo;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TipoRepository;

@Service
public class TipoService {
    
    @Autowired
    private TipoRepository tipoRepository;

    
    // -----------------------------
    // LISTAR TODAS OS TIPOS
    // -----------------------------
    public List<Tipo> listar() {
        return tipoRepository.findAll();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    public Tipo buscar(Long id) {
        return tipoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tipo de produto não encontrado"
            ));
    }

    // -----------------------------
    // CRIAR TIPO
    // -----------------------------
    public Tipo criar(Tipo tipo){
        return tipoRepository.save(tipo);
    }

    // -----------------------------
    // DELETAR TIPO
    // -----------------------------
   public void deletar(Long id) {
        Tipo tipo = tipoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tipo de produto não encontrado"
            ));
        
        tipoRepository.delete(tipo);
    }

    // -----------------------------
    // ATUALIZAR TIPO
    // -----------------------------
    public Tipo atualizar(Long id, Tipo tipoAtualizada) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Tipo de produto não encontrado"
            ));
        return tipoRepository.save(tipo);
    }
}
