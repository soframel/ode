package org.soframel.opendata.ode.scripts.frpar;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.parsers.frpar.scrutins.ScrutinsParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.VotesParser;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

public class ScrutinsProcessor {
	private final static Logger log = LoggerFactory.getLogger(ScrutinsProcessor.class);

	public static void main(String[] args) {
		ScrutinsProcessor proc = new ScrutinsProcessor(args);
		try {
			proc.insertScrutinsFull();
		}
		catch (IOException e) {
			log.error("IOException while inserting data: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	public ScrutinsProcessor(String[] args) {

	}

	private ScrutinsParser scrutinsParser;
	private VotesParser votesParser;

	private final String BIGFILES_DIR = "./src/test/bigresources";

	public void insertScrutinsFull() throws IOException {
		//TODO: scrutinRepository.deleteAll();
		//TODO: voteRepository.deleteAll();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource scrutinsResource = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		InputStream in = scrutinsResource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");

		long time = System.currentTimeMillis();
		scrutinsParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		//log.info(scrutinRepository.count() + " scrutins parsed and inserted in " + (time2 - time) + " ms");

		in.close();

		//in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		in = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml").getInputStream();
		votesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		//log.info(voteRepository.count() + " votes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();
	}
}
