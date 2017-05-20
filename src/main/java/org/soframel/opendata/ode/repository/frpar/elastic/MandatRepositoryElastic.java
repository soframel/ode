package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class MandatRepositoryElastic extends AbstractODERepository<Mandat> {

	@Override
	public String getElasticType() {
		return "mandat";
	}

	@Override
	public String getId(Mandat t) {
		return t.getUid();
	}

	@Override
	public Class<Mandat> getTypeClass() {
		return Mandat.class;
	}

	@Override
	public String getIndexName() {
		return "frpar-mandat";
	}

}
