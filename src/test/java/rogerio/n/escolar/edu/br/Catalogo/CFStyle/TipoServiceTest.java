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

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tipo;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TipoRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.TipoService;

public class TipoServiceTest {
    
    @Mock
    private TipoRepository tipoRepository;

     @InjectMocks
    private TipoService tipoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------------------------------------
    // TESTAR LISTAR
    // ---------------------------------------------
    @Test
    void testListar() {
        when(tipoRepository.findAll())
            .thenReturn(Arrays.asList(new Tipo(), new Tipo()));

        List<Tipo> tipoes = tipoService.listar();

        assertEquals(2, tipoes.size());
        verify(tipoRepository, times(1)).findAll();
    }

    // ---------------------------------------------
    // TESTAR BUSCAR - SUCESSO
    // ---------------------------------------------
    @Test
    void testBuscar_Sucesso() {
        Tipo tipo = new Tipo();
        tipo.setId(1L);
        when(tipoRepository.findById(1L)).thenReturn(Optional.of(tipo));
        Tipo resultado = tipoService.buscar(1L);
        assertEquals(1L, resultado.getId());
    }

    // ---------------------------------------------
    // TESTAR BUSCAR - NÃO ENCONTRADO
    // ---------------------------------------------
    @Test
    void testBuscar_NaoEncontrado() {
        when(tipoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
        tipoService.buscar(1L);});
}

    // ---------------------------------------------
    // TESTAR CRIAR
    // ---------------------------------------------
    @Test
    void testCriar() {
        Tipo c = new Tipo();
        c.setNome("acessorio");
        when(tipoRepository.save(c)).thenReturn(c);
        Tipo resultado = tipoService.criar(c);
        assertEquals("acessorio", resultado.getNome());
    }

    // ---------------------------------------------
    // TESTAR ATUALIZAR - SUCESSO
    // ---------------------------------------------
    @Test
    void testAtualizar_Sucesso() {
        Tipo existente = new Tipo();
        existente.setId(1L);
        existente.setNome("acessorio");
        Tipo atualizada = new Tipo();
        atualizada.setNome("oculos");
        when(tipoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(tipoRepository.save(existente)).thenReturn(existente);
        Tipo resultado = tipoService.atualizar(1L, atualizada);
        assertEquals("oculos", resultado.getNome());
    }

    // ---------------------------------------------
    // TESTAR ATUALIZAR - NÃO ENCONTRADA
    // ---------------------------------------------
    @Test
    void testAtualizar_NaoEncontrada() {
        when(tipoRepository.findById(1L)).thenReturn(Optional.empty());
        Tipo tipoAtualizada = new Tipo();
        tipoAtualizada.setNome("oculos");
        assertThrows(ResponseStatusException.class, () -> {
            tipoService.atualizar(1L, tipoAtualizada);
        });
    }

    // ---------------------------------------------
    // TESTAR DELETAR
    // ---------------------------------------------
    @Test
    void testDeletar() {
        Tipo tipo = new Tipo();
        tipo.setId(1L);
        when(tipoRepository.findById(1L)).thenReturn(Optional.of(tipo));
        tipoService.deletar(1L);
        verify(tipoRepository, times(1)).delete(tipo);
    }

    // ---------------------------------------------
    // TESTAR DELETAR - NÃO ENCONTRADA
    // ---------------------------------------------
    @Test
    void testDeletar_NaoEncontrada() {
        when(tipoRepository.findById(1L))
            .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            tipoService.deletar(1L);
        });
    }

}
