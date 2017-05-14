package org.soframel.opendata.ode.parsers.frpar.scrutins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.soframel.opendata.ode.domain.frpar.ModePublicationVotes;
import org.soframel.opendata.ode.domain.frpar.PositionVote;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.TypeVote;
import org.soframel.opendata.ode.domain.frpar.VotesGroupe;
import org.soframel.opendata.ode.parsers.frpar.scrutins.ScrutinsParser;

public class ScrutinParserTestCase {

	private ScrutinsParser parser;

	private List<Scrutin> savedScrutins;

	@Before
	public void init() {
		savedScrutins = new ArrayList<Scrutin>();

		/*repo = Mockito.mock(ScrutinRepository.class);
		when(repo.insert(Mockito.any(Scrutin.class))).thenAnswer(
				new Answer<Scrutin>() {
					@Override
					public Scrutin answer(InvocationOnMock invocation)
							throws Throwable {
						Scrutin scrutin = invocation.getArgumentAt(0,
								Scrutin.class);
						savedScrutins.add(scrutin);
						return scrutin;
					}
				});*/

		parser = new ScrutinsParser();
		//parser.setScrutinRepository(repo);
	}

	@Test
	public void testParseSmallScrutinsFile() throws IOException {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("scrutins-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		assertEquals(1, savedScrutins.size());
		Scrutin scrutin = savedScrutins.get(0);
		assertEquals("VTANR5L14V1", scrutin.getUid());
		assertEquals("PO644420", scrutin.getOrganeRef());
		assertEquals(LocalDate.of(2012, 7, 3), scrutin.getDateScrutin());
		assertEquals(TypeVote.SAT, scrutin.getTypeVote());
		assertEquals(Boolean.TRUE, scrutin.isAdopte());
		assertEquals("la déclaration de politique générale du gouvernement de " + "Jean-Marc Ayrault (application de l'article 49, alinéa premier de la " + "Constitution).", scrutin.getTitre());
		assertEquals("Conférence des présidents", scrutin.getDemandeur());
		assertEquals("la déclaration de politique générale du gouvernement de " + "Jean-Marc Ayrault (application de l'article 49, alinéa premier de la " + "Constitution).", scrutin.getObjet());
		assertEquals(ModePublicationVotes.DecompteNominatif, scrutin.getModePublicationVotes());
		assertEquals(544, scrutin.getNbVotants());
		assertEquals(527, scrutin.getSuffragesExprimes());
		assertEquals(264, scrutin.getSuffragesRequis());
		assertEquals(302, scrutin.getVotesPour());
		assertEquals(225, scrutin.getVotesContre());
		assertEquals(17, scrutin.getAbstention());
		assertEquals(26, scrutin.getNonVotants());

		assertEquals(1, scrutin.getVotesGroupes().size());
		VotesGroupe groupe = scrutin.getVotesGroupes().get(0);
		assertEquals("PO656002", groupe.getOrganeRef());
		assertEquals(294, groupe.getNbMembresGroupe());
		assertEquals(PositionVote.POUR, groupe.getPositionMajoritaire());
		assertEquals(267, groupe.getVotesPour());
		assertEquals(0, groupe.getVotesContre());
		assertEquals(0, groupe.getNbAbstentions());
		assertEquals(24, groupe.getNbNonVotants());
	}
}
