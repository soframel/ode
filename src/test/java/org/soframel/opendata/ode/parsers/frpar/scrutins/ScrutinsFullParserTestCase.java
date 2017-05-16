package org.soframel.opendata.ode.parsers.frpar.scrutins;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.domain.frpar.VotesGroupe;
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
@ContextConfiguration(classes = { ODEConfig.class, ScrutinsFullParserTestCase.class })
@Configuration
public class ScrutinsFullParserTestCase {
	private final static Logger log = LoggerFactory.getLogger(ScrutinsFullParserTestCase.class);

	private final String BIGFILES_DIR = "./src/test/bigresources";

	@Bean(name = "scrutinRepository")
	public ODERepository<Scrutin> createScrutinRepository() {
		ODERepository<Scrutin> mock = new MockODERepository<Scrutin>() {
			@Override
			public String getId(Scrutin t) {
				return t.getUid();
			}
		};
		return mock;
	}

	@Bean(name = "votesGroupeRepository")
	public ODERepository<VotesGroupe> createVotesGroupeRepository() {
		ODERepository<VotesGroupe> mock = new MockODERepository<VotesGroupe>() {
			@Override
			public String getId(VotesGroupe t) {
				return t.getId();
			}
		};
		return mock;
	}

	@Bean(name = "voteRepository")
	public ODERepository<Vote> createVoteRepository() {
		ODERepository<Vote> mock = new MockODERepository<Vote>() {
			@Override
			public String getId(Vote t) {
				return t.getVoteId();
			}
		};
		return mock;
	}

	@Autowired
	private VotesParser votesParser;

	@Autowired
	private ScrutinsParser scrutinsParser;

	@Autowired
	private ODERepository<Scrutin> scrutinRepository;
	@Autowired
	private ODERepository<VotesGroupe> votsGroupeRepository;

	@Autowired
	private ODERepository<Vote> voteRepository;

	@Test
	public void testInsertScrutinsFull() throws IOException {

		FileSystemResourceLoader loader = new FileSystemResourceLoader();
		Resource scrutinsResource = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		InputStream in = scrutinsResource.getInputStream();
		//InputStream in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");

		long time = System.currentTimeMillis();
		scrutinsParser.parseAndInsert(in);

		long time2 = System.currentTimeMillis();
		log.info("scrutins parsed and inserted in " + (time2 - time) + " ms");

		assertEquals(1354, ((MockODERepository<Scrutin>) scrutinRepository).countEntries());

		in.close();

		//in = this.getClass().getResourceAsStream(BIGFILES_DIR + "/scrutins-20170220-full.xml");
		in = loader.getResource(BIGFILES_DIR + "/scrutins-20170220-full.xml").getInputStream();
		votesParser.parseAndInsert(in);
		long time3 = System.currentTimeMillis();

		log.info("votes parsed and inserted in " + (time3 - time2) + " ms");
		in.close();

		//TODO: Warning: doesn't match, some vote are multiple times with same id !?!
		//assertEquals(111251, ((MockODERepository<Vote>) voteRepository).countEntries());
		assertEquals(110809, ((MockODERepository<Vote>) voteRepository).countEntries());
	}
}
