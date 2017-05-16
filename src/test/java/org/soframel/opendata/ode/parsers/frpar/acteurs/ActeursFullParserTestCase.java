package org.soframel.opendata.ode.parsers.frpar.acteurs;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.mock.MockODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * insert big files to test on a real complete example
 * 
 * @author sophie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ODEConfig.class, ActeursFullParserTestCase.class })
@Configuration
public class ActeursFullParserTestCase {
	private final static Logger log = LoggerFactory.getLogger(ActeursFullParserTestCase.class);

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
	private ActeursParser acteursParser;

	@Autowired
	private OrganesParser organesParser;

	@Autowired
	private ODERepository<Acteur> acteurRepository;

	@Autowired
	private ODERepository<Mandat> mandatRepository;

	@Autowired
	private ODERepository<Organe> organeRepository;

	private final String BIGFILES_DIR = "./src/test/bigresources";

	@Test
	public void testInsertActeursFull() throws IOException {

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(BIGFILES_DIR + "/acteurs-201702-full.xml");
		InputStream in = resource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream("/acteurs-201702-full.xml");

		long time = System.currentTimeMillis();
		acteursParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		log.info("acteurs parsed and inserted in " + (time2 - time) + " ms");

		assertEquals(943, ((MockODERepository<Acteur>) acteurRepository).countEntries());

		in.close();
	}

	@Test
	public void testInsertOrganesFull() throws IOException {

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(BIGFILES_DIR + "/acteurs-201702-full.xml");
		InputStream in = resource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream("/acteurs-201702-full.xml");
		long time2 = System.currentTimeMillis();
		organesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		log.info("organes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();

		assertEquals(1499, ((MockODERepository<Organe>) organeRepository).countEntries());
	}
}
