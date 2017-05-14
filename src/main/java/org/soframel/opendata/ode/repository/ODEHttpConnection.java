package org.soframel.opendata.ode.repository;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public interface ODEHttpConnection {
	public CloseableHttpClient getHttpClient();

	public HttpGet getHttpGet(String query);

	public HttpPost getHttpPost(String query, String body);
}
