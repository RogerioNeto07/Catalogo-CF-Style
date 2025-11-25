package rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
