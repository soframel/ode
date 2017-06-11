package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.dto.frpar.MandatDTO;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class MandatDTORepositoryElastic extends AbstractODERepository<MandatDTO> {

	@Override
	public String getElasticType() {
		return "mandat";
	}

	@Override
	public String getId(MandatDTO t) {
		return t.getUid();
	}

	@Override
	public Class<MandatDTO> getTypeClass() {
		return MandatDTO.class;
	}

	@Override
	public String getIndexName() {
		return "frpar-mandat";
	}

}
