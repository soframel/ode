package org.soframel.opendata.ode.repository;

public interface ODERepository<T> {

	public void save(T o) throws Exception;

	public T get(String id) throws Exception;

	public void deleteAll() throws Exception;

	public void createIndexMapping() throws Exception;

	public T getCached(String id);

	public void cache(T o);
}
