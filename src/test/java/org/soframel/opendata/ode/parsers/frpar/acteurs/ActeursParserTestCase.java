package org.soframel.opendata.ode.parsers.frpar.acteurs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.TypeMandat;
import org.soframel.opendata.ode.parsers.frpar.acteurs.ActeursParser;

public class ActeursParserTestCase {

	private ActeursParser parser;
	private List<Acteur> savedActeurs;

	@Before
	public void init() {
		savedActeurs = new ArrayList<Acteur>();

		/*repo = Mockito.mock(ActeurRepository.class);
		when(repo.insert(Mockito.any(Acteur.class))).thenAnswer(new Answer<Acteur>() {
			@Override
			public Acteur answer(InvocationOnMock invocation) throws Throwable {
				Acteur Acteur = invocation.getArgumentAt(0, Acteur.class);
				savedActeurs.add(Acteur);
				return Acteur;
			}
		});*/

		parser = new ActeursParser();
		//parser.setActeurRepository(repo);
	}

	@Test
	public void testParseSmallActeursFile() throws IOException {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("acteurs-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		assertEquals(1, savedActeurs.size());
		Acteur acteur = savedActeurs.get(0);

		assertEquals("PA286166", acteur.getUid());
		assertEquals("M.", acteur.getCivilite());
		assertEquals("Christian", acteur.getPrenom());
		assertEquals("Blanc", acteur.getNom());
		assertEquals("Blanc Chr", acteur.getAlpha());
		assertEquals(LocalDate.of(1942, 5, 17), acteur.getDateNaissance());
		assertEquals("Talence", acteur.getVilleNaissance());
		assertEquals("Gironde", acteur.getDepNaissance());
		assertNull(acteur.getPaysNaissance());
		assertNull(acteur.getDateDeces());
		assertEquals("Ancien président de sociétés publiques et privées, ancien préfet", acteur.getProfession());
		assertEquals("Cadres de la fonction publique, professions intellectuelles et artistiques", acteur.getCatSocPro());
		assertEquals("Cadres et professions intellectuelles supérieures", acteur.getFamSocPro());

		assertEquals(14, acteur.getMandats().size());

		for (Mandat mandat : acteur.getMandats()) {
			if ("PM386050".equals(mandat.getUid())) {
				assertEquals(TypeMandat.MandatParlementaire_type, mandat.getType());
				assertEquals("PM386050", mandat.getUid());
				assertEquals(13, mandat.getLegislature());
				assertEquals("ASSEMBLEE", mandat.getTypeOrgane());
				assertEquals(LocalDate.of(2007, 6, 20), mandat.getDateDebut());
				assertNull(mandat.getDatePublication());
				assertEquals(LocalDate.of(2008, 4, 19), mandat.getDateFin());
				assertEquals("membre", mandat.getQualite());
				assertEquals(1, mandat.getOrganesRef().size());
				assertEquals("PO384266", mandat.getOrganesRef().get(0));
			}
		}

	}
}
