package org.soframel.opendata.ode.mapping;

public interface ODEMapper<T, U> {
	public U map(T t);

	public T mapInverted(U u);
}
