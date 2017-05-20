package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class OrganeRepositoryElastic extends AbstractODERepository<Organe> {

	@Override
	public String getElasticType() {
		return "organe";
	}

	@Override
	public String getId(Organe t) {
		return t.getUid();
	}

	@Override
	public Class<Organe> getTypeClass() {
		return Organe.class;
	}

	@Override
	public String getIndexName() {
		return "frpar-organe";
	}

}
