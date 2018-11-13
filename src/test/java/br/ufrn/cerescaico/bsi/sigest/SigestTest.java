package br.ufrn.cerescaico.bsi.sigest;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;

public class SigestTest {
    
    private static final Logger LOGGER = Logger.getLogger(SigestTest.class.getName());

    Sigest sigest;

    Curso curso;
    Curso curso2;

    @Before
    public void setUp() throws Exception {
        sigest = Sigest.getInstance();
        curso = new Curso();
        curso.setNome("Curso 1");

        curso2 = new Curso();
        curso2.setNome("Curso 2");
    }

    @After
    public void tearDown() throws Exception {
        try {
            sigest.excluirCurso(curso.getCodigo());
            sigest.excluirCurso(curso2.getCodigo());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
        curso = null;
        curso2 = null;

    }

    @Test
    public final void testGetInstance() {
        Sigest sigest = Sigest.getInstance();
        assertNotNull("1 - Testando Not Null: ", sigest);
        Sigest copia = Sigest.getInstance();
        assertEquals("2 - Verificando Equals: ", sigest, copia);
    }

    @Test
    public void testCurso() {
        try {
            Curso add = sigest.inserirCurso(curso);
            assertNotNull("0", curso.getCodigo());
            assertNotNull("1", add.getCodigo());
            assertEquals("2", curso.getNome(), add.getNome());
            Curso buscado = sigest.buscarCurso(add.getCodigo());
            assertEquals("3", buscado.getCodigo(), add.getCodigo());
            assertEquals("4", buscado.getNome(), add.getNome());
            sigest.excluirCurso(add.getCodigo());
            buscado = sigest.buscarCurso(add.getCodigo());
            assertEquals("5", buscado, null);
        } catch (NegocioException e) {
            fail();
            // e.printStackTrace();
        }
    }
}
