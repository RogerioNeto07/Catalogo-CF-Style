package rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {
    
}