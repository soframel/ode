package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class ScrutinRepositoryElastic extends AbstractODERepository<Scrutin> {

	@Override
	public String getElasticType() {
		return "scrutin";
	}

	@Override
	public String getId(Scrutin t) {
		return t.getUid();
	}

	@Override
	public Class<Scrutin> getTypeClass() {
		return Scrutin.class;
	}

}
