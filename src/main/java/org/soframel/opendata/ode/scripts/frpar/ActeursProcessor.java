package org.soframel.opendata.ode.scripts.frpar;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.parsers.frpar.acteurs.ActeursParser;
import org.soframel.opendata.ode.parsers.frpar.acteurs.OrganesParser;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

/**
 * insert big files to test on a real complete example
 * 
 * @author sophie
 *
 */
public class ActeursProcessor {
	private final static Logger log = LoggerFactory.getLogger(ActeursProcessor.class);

	public static void main(String[] args) {
		ActeursProcessor proc = new ActeursProcessor(args);
		try {
			proc.insertActeursFull();
			proc.insertOrganesFull();
		}
		catch (IOException e) {
			log.error("IOException while inserting data: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	public ActeursProcessor(String[] args) {

	}

	private ActeursParser acteursParser;
	private OrganesParser organesParser;

	private final String BIGFILES_DIR = "./src/test/bigresources";

	public void insertActeursFull() throws IOException {
		//TODO: acteurRepository.deleteAll();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(BIGFILES_DIR + "/acteurs-201702-full.xml");
		InputStream in = resource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream("/acteurs-201702-full.xml");

		long time = System.currentTimeMillis();
		acteursParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		//log.info(acteurRepository.count() + " acteurs parsed and inserted in " + (time2 - time) + " ms");

		in.close();
	}

	public void insertOrganesFull() throws IOException {
		//TODO: organeRepository.deleteAll();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource resource = loader.getResource(BIGFILES_DIR + "/acteurs-201702-full.xml");
		InputStream in = resource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream("/acteurs-201702-full.xml");
		long time2 = System.currentTimeMillis();
		organesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		//log.info(organeRepository.count() + " organes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();
	}

}
