package org.soframel.opendata.ode.parsers.frpar.scrutins;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.ModePublicationVotes;
import org.soframel.opendata.ode.domain.frpar.PositionVote;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.TypeVote;
import org.soframel.opendata.ode.domain.frpar.VotesGroupe;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.mock.MockODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ODEConfig.class, ScrutinParserTestCase.class })
@Configuration
public class ScrutinParserTestCase {

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

	@Autowired
	private ScrutinsParser parser;

	@Autowired
	private ODERepository<Scrutin> srutinRepository;
	@Autowired
	private ODERepository<VotesGroupe> votsGroupeRepository;

	@Test
	public void testParseSmallScrutinsFile() throws Exception {

		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("scrutins-small.xml");) {
			assertNotNull("in is null", in);
			parser.parseAndInsert(in);
		}

		Scrutin scrutin = srutinRepository.get("VTANR5L14V1");
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

		VotesGroupe groupe = votsGroupeRepository.get("VTANR5L14V1-PO656002");
		assertNotNull(groupe);
		assertEquals("PO656002", groupe.getOrganeRef());
		assertEquals(294, groupe.getNbMembresGroupe());
		assertEquals(PositionVote.POUR, groupe.getPositionMajoritaire());
		assertEquals(267, groupe.getVotesPour());
		assertEquals(0, groupe.getVotesContre());
		assertEquals(0, groupe.getNbAbstentions());
		assertEquals(24, groupe.getNbNonVotants());
	}
}
