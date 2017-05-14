package org.soframel.opendata.ode.repository;

public interface ODERepository<T> {

	public void save(T o) throws Exception;

	public T get(String id) throws Exception;
}
