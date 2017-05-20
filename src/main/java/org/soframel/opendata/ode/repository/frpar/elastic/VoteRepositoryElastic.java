package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class VoteRepositoryElastic extends AbstractODERepository<Vote> {

	@Override
	public String getElasticType() {
		return "vote";
	}

	@Override
	public String getId(Vote t) {
		return t.getVoteId();
	}

	@Override
	public Class<Vote> getTypeClass() {
		return Vote.class;
	}

	@Override
	public String getIndexName() {
		return "frpar-vote";
	}

}
