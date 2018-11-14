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
import br.ufrn.cerescaico.bsi.sigest.model.Professor;

public class ProfessorBOTest {
    
    private static final Logger LOGGER = Logger.getLogger(ProfessorBOTest.class.getName());

    ProfessorBO bo = new ProfessorBO();
    CursoBO cursobo = new CursoBO();
    Professor prof;
    Professor prof2;
    Curso curso;

    @Before
    public void setUp() throws Exception {
        curso = new Curso();
        curso.setNome("Curso Teste");
        curso = cursobo.inserir(curso);

        prof = new Professor();
        prof.setNome("Professor Teste");
        prof.setSiape(1);
        prof.setCurso(curso);

        prof2 = new Professor();
        prof2.setNome("Professor Teste 2");
        prof2.setSiape(2);
        prof2.setCurso(curso);
    }

    @After
    public void tearDown() throws Exception {
        try {
            bo.excluir(prof.getCodigo());
            bo.excluir(prof2.getCodigo());
            cursobo.excluir(curso.getCodigo());
        } catch (NegocioException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
        prof = null;
        prof2 = null;
        curso = null;
    }

    @Test
    public void testInserir() {
        try {
            prof = bo.inserir(prof);
            assertNotNull("1", prof);
            assertNotNull("2", prof.getCodigo());
            assertEquals("3", "Professor Teste", prof.getNome());
        } catch (NegocioException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void testInserirDuplicata() {
        try {
            prof = bo.inserir(prof);
            prof2 = bo.inserir(prof);
        } catch (NegocioException e) {
            assertEquals("1", "erro.professor.bo.inserir.exception", e.getMessage());
            //Deveria ser uma preexistingException
            //TODO assertEquals("1", "erro.professor.bo.inserir.PreexistingEntityException", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testBuscar() {
        try {
            prof = bo.inserir(prof);
            assertNotNull("1", prof);
            assertNotNull("2", prof.getCodigo());
            assertEquals("3", "Professor Teste", prof.getNome());
            Professor buscado = bo.buscarProfessor(prof.getCodigo());
            assertEquals("4", buscado.getCodigo(), prof.getCodigo());
        } catch (NegocioException e) {
            fail();
            e.printStackTrace();
        }
    }

}
