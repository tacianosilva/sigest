package br.ufrn.cerescaico.bsi.sigest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SigestTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetInstance() {
		Sigest sigest = Sigest.getInstance();
		assertNotNull("1 - Testando Not Null: ", sigest);
		Sigest copia = Sigest.getInstance();
		assertEquals("2 - Verificando Equals: ", sigest, copia);
	}

}
