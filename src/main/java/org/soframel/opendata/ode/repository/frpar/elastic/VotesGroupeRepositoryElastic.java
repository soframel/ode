package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.VotesGroupe;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class VotesGroupeRepositoryElastic extends AbstractODERepository<VotesGroupe> {

	@Override
	public String getElasticType() {
		return "votesgroupe";
	}

	@Override
	public String getId(VotesGroupe t) {
		return t.getId();
	}

	@Override
	public Class<VotesGroupe> getTypeClass() {
		return VotesGroupe.class;
	}

}
