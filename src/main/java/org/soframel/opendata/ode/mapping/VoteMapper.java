package org.soframel.opendata.ode.mapping;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.dto.frpar.VoteDTO;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VoteMapper implements ODEMapper<Vote, VoteDTO> {

	private static Logger LOGGER = Logger.getLogger(VoteMapper.class);

	@Autowired
	private ODERepository<Acteur> acteurRepository;
	@Autowired
	private ODERepository<Scrutin> scrutinRepository;

	@Autowired
	private Mapper dozerMapper;

	@Override
	public VoteDTO map(Vote t) {
		VoteDTO dto = new VoteDTO();

		LOGGER.debug("mapping acteur with vote");

		Acteur a = acteurRepository.getCached(t.getActeurRef());
		if (a != null) {
			dozerMapper.map(a, dto);
		}
		else {
			LOGGER.error("Referenced acteur " + t.getActeurRef() + " not found !");
		}

		LOGGER.debug("mapping scrutin with vote");
		Scrutin s = scrutinRepository.getCached(t.getScrutinId());
		if (s != null) {
			dozerMapper.map(s, dto);
		}
		else {
			LOGGER.error("Referenced srcutin " + t.getScrutinId() + " not found !");
		}

		//Vote mapper last to override fields with same name
		LOGGER.debug("mapping vote with vote");
		dozerMapper.map(t, dto);
		return dto;
	}

	@Override
	public Vote mapInverted(VoteDTO t) {
		Vote vo = dozerMapper.map(t, Vote.class);

		return vo;
	}

}
