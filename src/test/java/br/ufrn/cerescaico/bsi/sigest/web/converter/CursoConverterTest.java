package br.ufrn.cerescaico.bsi.sigest.web.converter;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufrn.cerescaico.bsi.sigest.bo.CursoBO;
import br.ufrn.cerescaico.bsi.sigest.bo.NegocioException;
import br.ufrn.cerescaico.bsi.sigest.model.Curso;

public class CursoConverterTest {
    
    private static final Logger LOGGER = Logger.getLogger(CursoConverterTest.class.getName());

    CursoBO bo = new CursoBO();
    Curso curso;
    Curso inserido;
    String nomeCurso = "Curso de Teste - CursoConverterTest";

    @Before
    public void setUp() throws Exception {
        curso = new Curso();
        curso.setNome(nomeCurso);
        inserido = bo.inserir(curso);
    }

    @After
    public void tearDown() throws Exception {
        try {
            bo.excluir(inserido.getCodigo());
        } catch (NegocioException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }
        curso = null;
        inserido = null;
    }

    @Test
    public final void testGetAsObject() {
        CursoConverter converter = new CursoConverter();
        Object convertido = converter.getAsObject(null, null, nomeCurso);
        assertNotNull("1- Curso Existe!", convertido);
        if (convertido != null && convertido instanceof Curso) {
            Curso temp = (Curso) convertido;
            assertEquals("2", inserido.getCodigo(), temp.getCodigo());
            assertEquals("3", inserido.getNome(), temp.getNome());
        } else {
            fail("Retorno foi nulo ou outro problema! convertido = " + convertido);
        }

    }

    @Test
    public final void testGetAsString() {
        CursoConverter converter = new CursoConverter();
        String nomeConvertido = converter.getAsString(null, null, inserido);
        assertNotNull("1- Curso Existe!", nomeConvertido);
        if (nomeConvertido != null) {
            assertEquals("2", nomeCurso, nomeConvertido);
        }
    }
}