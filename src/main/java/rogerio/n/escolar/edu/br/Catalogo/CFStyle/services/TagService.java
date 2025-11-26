package rogerio.n.escolar.edu.br.Catalogo.CFStyle.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TagRepository;

@Service
public class TagService {
    
    @Autowired
    private TagRepository tagRepository;

    
    // -----------------------------
    // LISTAR TODAS AS TAGS
    // -----------------------------
    public List<Tag> listar() {
        return tagRepository.findAll();
    }

    // -----------------------------
    // BUSCAR POR ID
    // -----------------------------
    public Tag buscar(Long id) {
        return tagRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tag não encontrada"));
    }

    // -----------------------------
    // CRIAR TAG
    // -----------------------------
    public Tag criar(Tag tag){
        return tagRepository.save(tag);
    }

    // -----------------------------
    // DELETAR TAG
    // -----------------------------
    public void deletar(Long id){
        tagRepository.deleteById(id);
    }

    // -----------------------------
    // ATUALIZAR TAG
    // -----------------------------
    public Tag atualizar(Long id, Tag tagAtualizada) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada"));
        tag.setNome(tagAtualizada.getNome());
        return tagRepository.save(tag);
    }
}
