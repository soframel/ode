package org.soframel.opendata.ode.scripts.frpar;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.domain.frpar.VotesGroupe;
import org.soframel.opendata.ode.parsers.frpar.acteurs.ActeursParser;
import org.soframel.opendata.ode.parsers.frpar.acteurs.OrganesParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.ScrutinsParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.VotesParser;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

/**
 * insert big files to system
 * 
 * @author sophie
 *
 */
public class FrparProcessor {
	private final static Logger log = LoggerFactory.getLogger(FrparProcessor.class);

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ODEConfig.class);

		FrparProcessor proc = context.getBean(FrparProcessor.class);
		try {
			proc.insertOrganesFull();
			proc.insertActeursFull();
			proc.insertScrutinsFull();
		}
		catch (Exception e) {
			log.error("Exception while inserting data: " + e.getMessage(), e);
			e.printStackTrace();
		}

	}

	@Autowired
	private ODERepository<Acteur> acteurRepository;
	@Autowired
	private ODERepository<Organe> organeRepository;
	@Autowired
	private ODERepository<Mandat> mandatRepository;
	@Autowired
	private ODERepository<Scrutin> scrutinRepository;
	@Autowired
	private ODERepository<VotesGroupe> votesGroupeRepository;
	@Autowired
	private ODERepository<Vote> voteRepository;

	@Autowired
	private ActeursParser acteursParser;

	@Autowired
	private OrganesParser organesParser;
	@Autowired
	private ScrutinsParser scrutinsParser;
	@Autowired
	private VotesParser votesParser;

	private final String BIGFILES_DIR = "./src/test/bigresources";

	public void insertActeursFull() throws Exception {
		log.info("inserting acteurs");
		try {
			acteurRepository.deleteAll();
			mandatRepository.deleteAll();
		}
		catch (Exception e) {
			//not an issue, index was perhaps already deleted
			log.warn("Exception while deleting index: " + e.getMessage());
		}
		acteurRepository.createIndexMapping();
		mandatRepository.createIndexMapping();

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

	public void insertOrganesFull() throws Exception {
		log.info("inserting organes");
		try {
			organeRepository.deleteAll();
		}
		catch (Exception e) {
			//not an issue, index was perhaps already deleted
			log.warn("Exception while deleting index: " + e.getMessage());
		}
		organeRepository.createIndexMapping();

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

	public void insertScrutinsFull() throws Exception {
		log.info("inserting scrutins");
		try {
			scrutinRepository.deleteAll();
			voteRepository.deleteAll();
			votesGroupeRepository.deleteAll();
		}
		catch (Exception e) {
			//not an issue, index was perhaps already deleted
			log.warn("Exception while deleting index: " + e.getMessage());
		}
		scrutinRepository.createIndexMapping();
		voteRepository.createIndexMapping();
		votesGroupeRepository.createIndexMapping();

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource scrutinsResource = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		InputStream in = scrutinsResource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");

		long time = System.currentTimeMillis();
		scrutinsParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		//log.info(scrutinRepository.count() + " scrutins parsed and inserted in " + (time2 - time) + " ms");

		in.close();

		log.info("inserting votes");

		in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		in = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml").getInputStream();
		votesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		//log.info(voteRepository.count() + " votes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();
	}

}
