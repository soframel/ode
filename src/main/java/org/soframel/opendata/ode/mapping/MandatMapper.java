package org.soframel.opendata.ode.mapping;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.dto.frpar.MandatDTO;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MandatMapper implements ODEMapper<Mandat, MandatDTO> {

	private static Logger LOGGER = Logger.getLogger(MandatMapper.class);

	@Autowired
	private ODERepository<Acteur> acteurRepository;
	@Autowired
	private ODERepository<Organe> organeRepository;

	@Autowired
	private Mapper dozerMapper;

	@Override
	public MandatDTO map(Mandat t) {
		MandatDTO dto = new MandatDTO();

		LOGGER.debug("mapping acteur with mandat");

		Acteur a = acteurRepository.getCached(t.getActeurId());
		if (a != null) {
			dozerMapper.map(a, dto);
		}
		else {
			LOGGER.error("Referenced acteur " + t.getActeurId() + " not found !");
		}

		LOGGER.debug("mapping organe with mandat");

		if (t.getOrganesRef().size() > 0) {
			Organe o = organeRepository.getCached(t.getOrganesRef().get(0));
			if (o != null) {
				dozerMapper.map(o, dto);
			}
			else {
				LOGGER.error("Referenced organe " + t.getOrganesRef().get(0) + " not found !");
			}
		}

		//mandat mapping last to override fieds of same name
		LOGGER.debug("mapping mandat with mandat");
		dozerMapper.map(t, MandatDTO.class);

		return dto;
	}

	@Override
	public Mandat mapInverted(MandatDTO t) {
		Mandat vo = dozerMapper.map(t, Mandat.class);

		return vo;
	}

}
