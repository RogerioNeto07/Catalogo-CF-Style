package rogerio.n.escolar.edu.br.Catalogo.CFStyle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Cor;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.CorRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.CorService;

public class CorServiceTest {
    
    @Mock
    private CorRepository corRepository;

     @InjectMocks
    private CorService corService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------------------------------------
    // TESTAR LISTAR
    // ---------------------------------------------
    @Test
    void testListar() {
        when(corRepository.findAll())
            .thenReturn(Arrays.asList(new Cor(), new Cor()));

        List<Cor> cores = corService.listar();

        assertEquals(2, cores.size());
        verify(corRepository, times(1)).findAll();
    }

    // ---------------------------------------------
    // TESTAR BUSCAR - SUCESSO
    // ---------------------------------------------
    @Test
    void testBuscar_Sucesso() {
        Cor cor = new Cor();
        cor.setId(1L);
        when(corRepository.findById(1L)).thenReturn(Optional.of(cor));
        Cor resultado = corService.buscar(1L);
        assertEquals(1L, resultado.getId());
    }

    // ---------------------------------------------
    // TESTAR BUSCAR - NÃO ENCONTRADO
    // ---------------------------------------------
    @Test
    void testBuscar_NaoEncontrado() {
        when(corRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
        corService.buscar(1L);});
}

    // ---------------------------------------------
    // TESTAR CRIAR
    // ---------------------------------------------
    @Test
    void testCriar() {
        Cor c = new Cor();
        c.setNome("azul");
        when(corRepository.save(c)).thenReturn(c);
        Cor resultado = corService.criar(c);
        assertEquals("azul", resultado.getNome());
    }

    // ---------------------------------------------
    // TESTAR ATUALIZAR - SUCESSO
    // ---------------------------------------------
    @Test
    void testAtualizar_Sucesso() {
        Cor existente = new Cor();
        existente.setId(1L);
        existente.setNome("azul");
        Cor atualizada = new Cor();
        atualizada.setNome("vermelho");
        when(corRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(corRepository.save(existente)).thenReturn(existente);
        Cor resultado = corService.atualizar(1L, atualizada);
        assertEquals("vermelho", resultado.getNome());
    }

    // ---------------------------------------------
    // TESTAR ATUALIZAR - NÃO ENCONTRADA
    // ---------------------------------------------
    @Test
    void testAtualizar_NaoEncontrada() {
        when(corRepository.findById(1L)).thenReturn(Optional.empty());
        Cor corAtualizada = new Cor();
        corAtualizada.setNome("vermelho");
        assertThrows(ResponseStatusException.class, () -> {
            corService.atualizar(1L, corAtualizada);
        });
    }

    // ---------------------------------------------
    // TESTAR DELETAR
    // ---------------------------------------------
    @Test
    void testDeletar() {
        Cor cor = new Cor();
        cor.setId(1L);
        when(corRepository.findById(1L)).thenReturn(Optional.of(cor));
        corService.deletar(1L);
        verify(corRepository, times(1)).delete(cor);
    }

    // ---------------------------------------------
    // TESTAR DELETAR - NÃO ENCONTRADA
    // ---------------------------------------------
    @Test
    void testDeletar_NaoEncontrada() {
        when(corRepository.findById(1L))
            .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            corService.deletar(1L);
        });
    }

}
