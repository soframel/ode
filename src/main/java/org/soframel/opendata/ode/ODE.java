package org.soframel.opendata.ode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class ODE {
	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(ODEConfig.class);

	}
}
