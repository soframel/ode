package org.soframel.opendata.ode.parsers.frpar.acteurs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.mock.MockODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ODEConfig.class, OrganesParserTestCase.class })
@Configuration
public class OrganesParserTestCase {

	@Bean(name = "organeRepository")
	public ODERepository<Organe> createOrganeRepository() {
		ODERepository<Organe> mock = new MockODERepository<Organe>() {
			@Override
			public String getId(Organe t) {
				return t.getUid();
			}
		};
		return mock;
	}

	@Autowired
	private OrganesParser parser;

	@Autowired
	private ODERepository<Organe> organeRepository;

	@Test
	public void testParseSmallOrganesFile() throws Exception {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("organes-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		Organe organe = organeRepository.get("PO711218");
		assertEquals("PO711218", organe.getUid());
		assertEquals("Formation des enseignants", organe.getLibelle());
		assertEquals("de la mission d'information sur la formation des enseignants", organe.getLibelleEdition());
		assertEquals("Formation des enseignants", organe.getLibelleAbrege());
		assertEquals("FORMENS", organe.getLibelleAbrev());
		assertEquals(LocalDate.of(2015, 12, 16), organe.getDateDebut());
		assertNull(organe.getDateAgrement());
		assertEquals(LocalDate.of(2016, 10, 5), organe.getDateFin());
		assertEquals("5ème République", organe.getRegime());
		assertEquals(14, organe.getLegislature());

		Organe organe2 = organeRepository.get("PO76034");
		assertEquals("PO76034", organe2.getUid());
		assertEquals("Conseil constitutionnel", organe2.getLibelle());
		assertEquals("du Conseil constitutionnel", organe2.getLibelleEdition());
		assertEquals("Conseil constitutionnel", organe2.getLibelleAbrege());
		assertEquals("ASSEX", organe2.getLibelleAbrev());
		assertEquals(LocalDate.of(1959, 2, 1), organe2.getDateDebut());
		assertNull(organe2.getDateAgrement());
		assertNull(organe2.getDateFin());
		assertNull(organe2.getRegime());
		assertEquals(0, organe2.getLegislature());

	}
}
