package dat102_oblig3NY;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oppgave_c.JavaSetToMengde;
import mengdeADT.MengdeADT;

class JavaSetToMengdeTest {

	private JavaSetToMengde<Integer> mengde1;
	private JavaSetToMengde<Integer> mengde2;
	

	
	@BeforeEach
	void setUp() {
		mengde1 = new JavaSetToMengde<>();
		mengde2 = new JavaSetToMengde<>();
	}

	@Test
	void testErTom() {
		assertTrue(mengde1.erTom(), "Mengden skal være tom");
		mengde1.leggTil(5);
		assertFalse(mengde1.erTom(), "Mengden skal ikke være tom ");

	}

	@Test
	void testleggTilogInneholder() {
		mengde1.leggTil(8);
		mengde1.leggTil(9);
		mengde1.leggTil(10);
		assertTrue(mengde1.inneholder(8));
		assertTrue(mengde1.inneholder(9));
		assertTrue(mengde1.inneholder(10));
		assertFalse(mengde1.inneholder(1));
	}

	@Test
	void testFjern() {
		mengde1.leggTil(11);
		mengde1.leggTil(20);
		assertEquals(20, mengde1.fjern(20), "fjerne 20");
		assertEquals(11, mengde1.fjern(11), "fjerne 11");
		assertNull(mengde1.fjern(4), "element som ikke finnnes skal returner null");

	}

	@Test
	void testSnitt() {
		mengde1.leggTil(1);
		mengde1.leggTil(2);
		mengde1.leggTil(3);

		mengde2.leggTil(2);
		mengde2.leggTil(3);
		mengde2.leggTil(4);

		MengdeADT<Integer> snittmengde = mengde1.snitt(mengde2);
		assertTrue(snittmengde.inneholder(2));
		assertTrue(snittmengde.inneholder(3));
		assertFalse(snittmengde.inneholder(4));
		assertFalse(snittmengde.inneholder(1));

	}

	@Test
	void testUnion() {
		mengde1.leggTil(1);
		mengde1.leggTil(2);

		mengde2.leggTil(2);
		mengde2.leggTil(3);

		MengdeADT<Integer> unionmengde = mengde1.union(mengde2);
		assertTrue(unionmengde.inneholder(1));
		assertTrue(unionmengde.inneholder(2));
		assertTrue(unionmengde.inneholder(3));
	}

	@Test
	void testMinus() {
		mengde1.leggTil(1);
		mengde1.leggTil(2);
		mengde1.leggTil(3);

		mengde2.leggTil(2);

		MengdeADT<Integer> diffmengde = mengde1.minus(mengde2);
		assertTrue(diffmengde.inneholder(1));
		assertTrue(diffmengde.inneholder(3));
		assertFalse(diffmengde.inneholder(2));

	}

	@Test
	void testerDelMengdeav() {
		mengde1.leggTil(1);
		mengde1.leggTil(2);

		mengde2.leggTil(1);
		mengde2.leggTil(2);
		mengde2.leggTil(3);

		assertTrue(mengde1.erDelmengdeAv(mengde2));
		assertFalse(mengde2.erDelmengdeAv(mengde1));
	}

	@Test
	void testerLik() {
		mengde1.leggTil(1);
		mengde1.leggTil(2);
		mengde2.leggTil(2);
		mengde2.leggTil(1);

		assertTrue(mengde1.erLik(mengde2));

		mengde2.leggTil(3);
		assertFalse(mengde1.erLik(mengde2));

	}

	@Test
	void testerDisjunkt() {
		mengde1.leggTil(1);
		mengde1.leggTil(2);

		mengde2.leggTil(3);
		mengde2.leggTil(4);

		assertTrue(mengde1.erDisjunkt(mengde2));

		mengde2.leggTil(2);
		assertFalse(mengde1.erDisjunkt(mengde2));

	}

	@Test
	void testtilTabell() {
	    mengde1.leggTil(10);
	    mengde1.leggTil(20);
	    mengde1.leggTil(30);

	    Integer[] tabell = mengde1.tilTabell();

	    assertEquals(3, tabell.length);

	    List<Integer> faktisk = Arrays.asList(tabell);
	    List<Integer> forventet = Arrays.asList(10, 20, 30);

	    assertTrue(faktisk.containsAll(forventet) && forventet.containsAll(faktisk));
	}
}
