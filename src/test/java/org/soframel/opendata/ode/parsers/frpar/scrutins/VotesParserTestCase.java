package org.soframel.opendata.ode.parsers.frpar.scrutins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.soframel.opendata.ode.domain.frpar.CausePositionVote;
import org.soframel.opendata.ode.domain.frpar.PositionVote;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.parsers.frpar.scrutins.VotesParser;

public class VotesParserTestCase {

	private VotesParser parser;
	private List<Vote> savedVotes;

	@Before
	public void init() {
		savedVotes = new ArrayList<Vote>();

		/*repo = Mockito.mock(VoteRepository.class);
		when(repo.insert(Mockito.any(Vote.class))).thenAnswer(
				new Answer<Vote>() {
					@Override
					public Vote answer(InvocationOnMock invocation)
							throws Throwable {
						Vote vote = invocation.getArgumentAt(0, Vote.class);
						savedVotes.add(vote);
						return vote;
					}
				});*/

		parser = new VotesParser();
		//parser.setVoteRepository(repo);
	}

	@Test
	public void testParseSmallScrutinsFile() throws IOException {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("scrutins-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		assertEquals(3, savedVotes.size());
		for (Vote vote : savedVotes) {
			assertEquals("VTANR5L14V1", vote.getScrutinId());
			assertNotNull(vote.getVoteId());
			if (vote.getActeurRef().equals("PA328")) {
				assertEquals("PM645184", vote.getMandatRef());
				assertEquals(CausePositionVote.MG, vote.getCausePositionVote());
				assertEquals(PositionVote.NONVOTANT, vote.getVote());
			}
			else if (vote.getActeurRef().equals("PA356")) {
				assertEquals("PM645358", vote.getMandatRef());
				assertNull(vote.getCausePositionVote());
				assertEquals(PositionVote.POUR, vote.getVote());

			}
			else if (vote.getActeurRef().equals("PA394")) {
				assertEquals("PM645447", vote.getMandatRef());
				assertNull(vote.getCausePositionVote());
				assertEquals(PositionVote.POUR, vote.getVote());
			}
			else {
				fail("Vote with no corresponding acteurRef: " + vote);
			}
		}
	}
}
