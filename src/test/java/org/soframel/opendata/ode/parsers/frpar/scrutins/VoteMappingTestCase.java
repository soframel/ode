package org.soframel.opendata.ode.parsers.frpar.scrutins;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.dozer.Mapper;
import org.junit.Test;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.CausePositionVote;
import org.soframel.opendata.ode.domain.frpar.PositionVote;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.dto.frpar.VoteDTO;

public class VoteMappingTestCase {

	@Test
	public void transformToDTO() {
		Vote v = new Vote();
		v.setActeurRef("A1");
		v.setCausePositionVote(CausePositionVote.MG);
		v.setMandatRef("M1");
		v.setScrutinId("S1");
		v.setVote(PositionVote.POUR);
		v.setVoteGroupeId("VG1");
		v.generateVoteId();

		Acteur a = new Acteur();
		a.setUid("A1");
		a.setAlpha("Short");
		a.setCatSocPro("cat");
		a.setCivilite("Mme");
		a.setDateDeces(LocalDate.now());
		a.setDateNaissance(LocalDate.now());
		a.setDepNaissance("42");
		a.setFamSocPro("fam");
		a.setNom("Dupont");
		a.setPaysNaissance("France");
		a.setPrenom("Michelle");
		a.setProfession("actrice");
		a.setVilleNaissance("Saint Etienne");

		Scrutin s = new Scrutin();
		s.setDateScrutin(LocalDate.of(2017, 06, 10));
		s.setTitre("Un scrutin");
		s.setObjet("Faire un test");

		ODEConfig config = new ODEConfig();
		Mapper mapper = config.createDozerMapper();
		VoteDTO dto = mapper.map(v, VoteDTO.class);

		mapper.map(a, dto);
		mapper.map(s, dto);

		assertEquals(v.getVoteId(), dto.getVoteId());
		assertEquals(v.getMandatRef(), dto.getMandatRef());
		assertEquals(a.getNom(), dto.getActeur_nom());
		assertEquals(s.getDateScrutin(), dto.getScrutin_dateScrutin());
		assertEquals(s.getTitre(), dto.getScrutin_titre());
		assertEquals(s.getObjet(), dto.getScrutin_objet());
	}
}
