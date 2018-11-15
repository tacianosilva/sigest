package br.ufrn.cerescaico.bsi.sigest.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrn.cerescaico.bsi.sigest.model.Curso;

public class CursoBOTest {
    
    private static final Logger LOGGER = Logger.getLogger(CursoBOTest.class.getName());

    CursoBO bo = new CursoBO();
    Curso curso;
    Curso curso2;

    @Before
    public void setUp() throws Exception {
        bo.listar();
        curso = new Curso();
        curso.setNome("Curso Teste");

        curso2 = new Curso();
        curso2.setNome("Curso Teste Excluir");
    }

    @After
    public void tearDown() {
        try {
            bo.excluir(curso.getCodigo());
            bo.excluir(curso2.getCodigo());
        } catch (NegocioException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
        curso = null;
        curso2 = null;
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

    @Test
    public void testInserirDuplicata() {
        try {
            curso = bo.inserir(curso);
            curso2 = bo.inserir(curso);
        } catch (NegocioException e) {
            assertEquals("1", "erro.curso.bo.inserir.PreexistingEntityException", e.getMessage());
            //e.printStackTrace();
        }
    }

    @Test
    public void testBuscar() {
        try {
            curso = bo.inserir(curso);
            assertNotNull("1", curso);
            assertNotNull("2", curso.getCodigo());
            assertEquals("3", "Curso Teste", curso.getNome());
            Curso buscado = bo.buscarCurso(curso.getCodigo());
            assertEquals("4", buscado.getCodigo(), curso.getCodigo());
        } catch (NegocioException e) {
            fail();
            //e.printStackTrace();
        }
    }


    @Test
    public void testExcluir() {
        try {
            curso2 = bo.inserir(curso2);
            assertNotNull("1", curso2);
            assertNotNull("2", curso2.getCodigo());
            assertEquals("3", "Curso Teste Excluir", curso2.getNome());
            Curso buscado = bo.buscarCurso(curso2.getCodigo());
            assertEquals("4", buscado.getCodigo(), curso2.getCodigo());
            bo.excluir(curso2.getCodigo());
            buscado = bo.buscarCurso(curso2.getCodigo());
            assertEquals("5", buscado, null);
        } catch (NegocioException e) {
            fail();
            //e.printStackTrace();
        }
    }

    //@Test
    public void testListar() {
        fail("Not yet implemented");
    }
}
