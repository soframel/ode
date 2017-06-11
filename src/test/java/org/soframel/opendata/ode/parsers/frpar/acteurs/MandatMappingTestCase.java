package org.soframel.opendata.ode.parsers.frpar.acteurs;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collections;

import org.dozer.Mapper;
import org.junit.Test;
import org.soframel.opendata.ode.ODEConfig;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.domain.frpar.TypeMandat;
import org.soframel.opendata.ode.dto.frpar.MandatDTO;

public class MandatMappingTestCase {

	@Test
	public void transformToDTO() {
		Mandat m = new Mandat();
		m.setActeurId("A1");
		m.setDateDebut(LocalDate.of(2015, 1, 1));
		m.setDateFin(LocalDate.of(2020, 1, 1));
		m.setDatePublication(LocalDate.of(2015, 2, 1));
		m.setLegislature(1);
		m.setOrganesRef(Collections.singletonList("O1"));
		m.setQualite("qualite");
		m.setType(TypeMandat.MandatMission_Type);
		m.setTypeOrgane("type organe");
		m.setUid("42");

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

		Organe o = new Organe();
		o.setUid("O1");
		o.setCodeType("PARL");
		o.setLibelle("My Organe");

		ODEConfig config = new ODEConfig();
		Mapper mapper = config.createDozerMapper();
		MandatDTO dto = mapper.map(a, MandatDTO.class);

		mapper.map(o, dto);
		mapper.map(m, dto);

		assertEquals(m.getUid(), dto.getUid());
		assertEquals(a.getNom(), dto.getActeur_nom());
		assertEquals(o.getCodeType(), dto.getOrgane_codeType());
		assertEquals(o.getLibelle(), dto.getOrgane_libelle());
		assertEquals(o.getUid(), dto.getOrgane_uid());
	}
}
