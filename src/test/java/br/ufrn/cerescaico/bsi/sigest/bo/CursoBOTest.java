package br.ufrn.cerescaico.bsi.sigest.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrn.cerescaico.bsi.sigest.model.Curso;

public class CursoBOTest {

    CursoBO bo = new CursoBO();
    Curso curso;

    @Before
    public void setUp() throws Exception {
        curso = new Curso();
        curso.setNome("Curso Teste");
    }

    @After
    public void tearDown() throws Exception {
        bo.excluir(curso.getCodigo());
        curso = null;
    }

    @Test
    public void testInserir() {
        try {
            curso = bo.inserir(curso);
            assertNotNull("1", curso);
            assertNotNull("2", curso.getCodigo());
            assertEquals("3", "Curso Teste", curso.getNome());
        } catch (NegocioException e) {
            fail();
            e.printStackTrace();
        }
    }

    //@Test
    public void testListar() {
        fail("Not yet implemented");
    }
}
