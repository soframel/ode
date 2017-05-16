package org.soframel.opendata.ode.parsers.frpar.acteurs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.time.LocalDate;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.TypeMandat;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.mock.MockODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ODEConfig.class, ActeursParserTestCase.class })
@Configuration
public class ActeursParserTestCase {

	@Bean(name = "acteurRepository")
	public ODERepository<Acteur> createActeurRepository() {
		ODERepository<Acteur> mock = new MockODERepository<Acteur>() {
			@Override
			public String getId(Acteur t) {
				return t.getUid();
			}
		};
		return mock;
	}

	@Bean(name = "mandatRepository")
	public ODERepository<Mandat> creatMandatRepository() {
		ODERepository<Mandat> mock = new MockODERepository<Mandat>() {
			@Override
			public String getId(Mandat t) {
				return t.getUid();
			}
		};
		return mock;
	}

	@Autowired
	private ActeursParser parser;

	@Autowired
	@Resource(name = "acteurRepository")
	private ODERepository<Acteur> acteurRepository;

	@Autowired
	@Resource(name = "mandatRepository")
	private ODERepository<Mandat> mandatRepository;

	@Test
	public void testParseSmallActeursFile() throws Exception {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("acteurs-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		Acteur acteur = acteurRepository.get("PA286166");

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

		Mandat mandat = mandatRepository.get("PM386050");

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
