package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.dto.frpar.VoteDTO;
import org.soframel.opendata.ode.mapping.ODEMapper;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CompositeVoteRepository implements ODERepository<Vote> {

	@Autowired
	private ODEMapper<Vote, VoteDTO> voteMapper;
	@Autowired
	private ODERepository<VoteDTO> voteRepository;

	@Override
	public void save(Vote o) throws Exception {
		VoteDTO dto = voteMapper.map(o);
		voteRepository.save(dto);
	}

	@Override
	public Vote get(String id) throws Exception {
		VoteDTO dto = voteRepository.get(id);
		Vote vo = voteMapper.mapInverted(dto);
		return vo;
	}

	@Override
	public void deleteAll() throws Exception {
		voteRepository.deleteAll();
	}

	@Override
	public void createIndexMapping() throws Exception {
		voteRepository.createIndexMapping();
	}

	@Override
	public Vote getCached(String id) {
		throw new IllegalStateException("Not implemented");
	}

	@Override
	public void cache(Vote o) {
		throw new IllegalStateException("Not implemented");
	}

}
