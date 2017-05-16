package org.soframel.opendata.ode.repository.mock;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.soframel.opendata.ode.repository.ODERepository;

public abstract class MockODERepository<T> implements ODERepository<T> {

	private static final Logger logger = Logger.getLogger(MockODERepository.class);

	private Map<String, T> map = new HashMap<String, T>();

	@Override
	public void save(T o) throws Exception {
		String id = getId(o);
		if (map.containsKey(id)) {
			logger.warn(">>>>>> entry already saved. Previous=" + map.get(id) + ", new=" + o);
		}

		map.put(id, o);
	}

	@Override
	public T get(String id) throws Exception {
		return map.get(id);
	}

	public abstract String getId(T t);

	public int countEntries() {
		return map.size();
	}
}