package org.soframel.opendata.ode.parsers.frpar.scrutins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.CausePositionVote;
import org.soframel.opendata.ode.domain.frpar.PositionVote;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.mock.MockODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ODEConfig.class, VotesParserTestCase.class })
@Configuration
public class VotesParserTestCase {

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
	private VotesParser parser;

	@Autowired
	private ODERepository<Vote> voteRepository;

	@Test
	public void testParseSmallScrutinsFile() throws Exception {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("scrutins-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		Vote vote = voteRepository.get("VTANR5L14V1-PA328-PM645184");
		assertNotNull(vote);
		assertEquals("VTANR5L14V1", vote.getScrutinId());
		assertEquals("PM645184", vote.getMandatRef());
		assertEquals(CausePositionVote.MG, vote.getCausePositionVote());
		assertEquals(PositionVote.NONVOTANT, vote.getVote());

		vote = voteRepository.get("VTANR5L14V1-PA356-PM645358");
		assertNotNull(vote);
		assertEquals("VTANR5L14V1", vote.getScrutinId());
		assertEquals("PM645358", vote.getMandatRef());
		assertNull(vote.getCausePositionVote());
		assertEquals(PositionVote.POUR, vote.getVote());

		vote = voteRepository.get("VTANR5L14V1-PA394-PM645447");
		assertNotNull(vote);
		assertEquals("VTANR5L14V1", vote.getScrutinId());
		assertEquals("PM645447", vote.getMandatRef());
		assertNull(vote.getCausePositionVote());
		assertEquals(PositionVote.POUR, vote.getVote());

	}
}
