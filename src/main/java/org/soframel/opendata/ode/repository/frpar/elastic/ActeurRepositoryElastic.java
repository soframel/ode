package org.soframel.opendata.ode.repository.frpar.elastic;

import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.repository.elastic.AbstractODERepository;

public class ActeurRepositoryElastic extends AbstractODERepository<Acteur> {

	@Override
	public String getElasticType() {
		return "acteur";
	}

	@Override
	public String getId(Acteur t) {
		return t.getUid();
	}

	@Override
	public Class<Acteur> getTypeClass() {
		return Acteur.class;
	}

}
