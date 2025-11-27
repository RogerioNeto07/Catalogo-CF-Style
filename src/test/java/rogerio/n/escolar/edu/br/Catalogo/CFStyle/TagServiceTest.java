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

import rogerio.n.escolar.edu.br.Catalogo.CFStyle.models.Tag;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.repositories.TagRepository;
import rogerio.n.escolar.edu.br.Catalogo.CFStyle.services.TagService;

public class TagServiceTest {
    
    @Mock
    private TagRepository tagRepository;

     @InjectMocks
    private TagService tagService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------------------------------------
    // TESTAR LISTAR
    // ---------------------------------------------
    @Test
    void testListar() {
        when(tagRepository.findAll())
            .thenReturn(Arrays.asList(new Tag(), new Tag()));

        List<Tag> tages = tagService.listar();

        assertEquals(2, tages.size());
        verify(tagRepository, times(1)).findAll();
    }

    // ---------------------------------------------
    // TESTAR BUSCAR - SUCESSO
    // ---------------------------------------------
    @Test
    void testBuscar_Sucesso() {
        Tag tag = new Tag();
        tag.setId(1L);
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));
        Tag resultado = tagService.buscar(1L);
        assertEquals(1L, resultado.getId());
    }

    // ---------------------------------------------
    // TESTAR BUSCAR - NÃO ENCONTRADO
    // ---------------------------------------------
    @Test
    void testBuscar_NaoEncontrado() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
        tagService.buscar(1L);});
}

    // ---------------------------------------------
    // TESTAR CRIAR
    // ---------------------------------------------
    @Test
    void testCriar() {
        Tag c = new Tag();
        c.setNome("plus-size");
        when(tagRepository.save(c)).thenReturn(c);
        Tag resultado = tagService.criar(c);
        assertEquals("plus-size", resultado.getNome());
    }

    // ---------------------------------------------
    // TESTAR ATUALIZAR - SUCESSO
    // ---------------------------------------------
    @Test
    void testAtualizar_Sucesso() {
        Tag existente = new Tag();
        existente.setId(1L);
        existente.setNome("plus-size");
        Tag atualizada = new Tag();
        atualizada.setNome("plussize");
        when(tagRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(tagRepository.save(existente)).thenReturn(existente);
        Tag resultado = tagService.atualizar(1L, atualizada);
        assertEquals("plussize", resultado.getNome());
    }

    // ---------------------------------------------
    // TESTAR ATUALIZAR - NÃO ENCONTRADA
    // ---------------------------------------------
    @Test
    void testAtualizar_NaoEncontrada() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());
        Tag tagAtualizada = new Tag();
        tagAtualizada.setNome("plussize");
        assertThrows(ResponseStatusException.class, () -> {
            tagService.atualizar(1L, tagAtualizada);
        });
    }

    // ---------------------------------------------
    // TESTAR DELETAR
    // ---------------------------------------------
    @Test
    void testDeletar() {
        Tag tag = new Tag();
        tag.setId(1L);
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));
        tagService.deletar(1L);
        verify(tagRepository, times(1)).delete(tag);
    }

    // ---------------------------------------------
    // TESTAR DELETAR - NÃO ENCONTRADA
    // ---------------------------------------------
    @Test
    void testDeletar_NaoEncontrada() {
        when(tagRepository.findById(1L))
            .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            tagService.deletar(1L);
        });
    }

}
