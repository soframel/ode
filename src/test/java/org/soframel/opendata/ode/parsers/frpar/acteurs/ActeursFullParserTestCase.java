package org.soframel.opendata.ode.parsers.frpar.acteurs;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.parsers.frpar.acteurs.ActeursParser;
import org.soframel.opendata.ode.parsers.frpar.acteurs.OrganesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * insert big files to test on a real complete example
 * 
 * @author sophie
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ActeursFullParserTestCase {
	private final static Logger log = LoggerFactory.getLogger(ActeursFullParserTestCase.class);

	@Autowired
	private ActeursParser acteursParser;

	@Autowired
	private OrganesParser organesParser;

	private final String BIGFILES_DIR = "./src/test/bigresources";

	@Test
	public void testInsertActeursFull() throws IOException {
		//TODO: acteurRepository.deleteAll();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(BIGFILES_DIR + "/acteurs-201702-full.xml");
		InputStream in = resource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream("/acteurs-201702-full.xml");

		long time = System.currentTimeMillis();
		acteursParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		log.info("acteurs parsed and inserted in " + (time2 - time) + " ms");

		//TODO: assertEquals(943, acteurRepository.count());
		in.close();
	}

	@Test
	public void testInsertOrganesFull() throws IOException {
		//TODO: organeRepository.deleteAll();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(BIGFILES_DIR + "/acteurs-201702-full.xml");
		InputStream in = resource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream("/acteurs-201702-full.xml");
		long time2 = System.currentTimeMillis();
		organesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		log.info("organes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();

		//TODO: assertEquals(1499, organeRepository.count());
	}
}
