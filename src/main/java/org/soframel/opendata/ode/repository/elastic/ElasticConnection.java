package org.soframel.opendata.ode.repository.elastic;

import java.nio.charset.Charset;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.soframel.opendata.ode.repository.ODEHttpConnection;

public class ElasticConnection implements ODEHttpConnection {
	private String url = "http://localhost:9200";

	@Override
	public CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	@Override
	public HttpGet getHttpGet(String query) {
		HttpGet get = new HttpGet(url + "/" + query);
		get.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		return get;
	}

	@Override
	public HttpPost getHttpPost(String query, String body) {
		HttpPost post = new HttpPost(url + "/" + query);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
		return post;
	}

	@Override
	public HttpDelete getHttpDelete(String query) {
		HttpDelete delete = new HttpDelete(url + "/" + query);
		return delete;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public HttpPut getHttpPut(String query, String body) {
		HttpPut put = new HttpPut(url + "/" + query);
		put.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		put.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
		return put;
	}

}
