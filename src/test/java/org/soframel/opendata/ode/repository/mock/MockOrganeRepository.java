package org.soframel.opendata.ode.repository.mock;

import java.util.HashMap;
import java.util.Map;

import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.repository.ODERepository;

public class MockOrganeRepository implements ODERepository<Organe> {

	private Map<String, Organe> map = new HashMap<String, Organe>();

	@Override
	public void save(Organe o) throws Exception {
		map.put(o.getUid(), o);
	}

	@Override
	public Organe get(String id) throws Exception {
		return map.get(id);
	}

}
