package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.dto.frpar.VoteDTO;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class VoteDTORepositoryElastic extends AbstractODERepository<VoteDTO> {

	@Override
	public String getElasticType() {
		return "vote";
	}

	@Override
	public String getId(VoteDTO t) {
		return t.getVoteId();
	}

	@Override
	public Class<VoteDTO> getTypeClass() {
		return VoteDTO.class;
	}

	@Override
	public String getIndexName() {
		return "frpar-vote";
	}

}
