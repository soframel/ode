package org.soframel.opendata.ode.parsers.frpar.scrutins;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.parsers.frpar.scrutins.ScrutinsParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.VotesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * insert big files to test on a real complete example
 * 
 * @author sophie
 *
 */
@RunWith(SpringRunner.class)
public class ScrutinsFullParserTestCase {
	private final static Logger log = LoggerFactory.getLogger(ScrutinsFullParserTestCase.class);

	@Autowired
	private ScrutinsParser scrutinsParser;

	@Autowired
	private VotesParser votesParser;

	private final String BIGFILES_DIR = "./src/test/bigresources";

	@Test
	public void testInsertScrutinsFull() throws IOException {
		//TODO: scrutinRepository.deleteAll();
		//TODO: voteRepository.deleteAll();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource scrutinsResource = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		InputStream in = scrutinsResource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");

		long time = System.currentTimeMillis();
		scrutinsParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		log.info("scrutins parsed and inserted in " + (time2 - time) + " ms");

		//TODO: assertEquals(1354, scrutinRepository.count());
		in.close();

		//in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		in = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml").getInputStream();
		votesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		log.info("votes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();

		//TODO: assertEquals(111251, voteRepository.count());
	}
}
