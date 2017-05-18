package org.soframel.opendata.ode.repository.elastic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.soframel.opendata.ode.repository.ODEHttpConnection;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.frpar.elastic.ActeurRepositoryElastic;
import org.soframel.opendata.ode.utils.JacksonHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractODERepository<T> implements ODERepository<T> {

	private static Logger LOGGER = Logger.getLogger(ActeurRepositoryElastic.class);

	@Autowired
	private ODEHttpConnection connection;

	@Autowired
	private JacksonHelper jacksonHelper;

	private final static String frparIndex = "frpar";

	@Override
	public void save(T o) throws ClientProtocolException, IOException {
		//JSON mapping
		ObjectMapper mapper = jacksonHelper.getObjectMapper();
		String serialized = mapper.writeValueAsString(o);

		LOGGER.debug("Sending acteur: " + serialized);

		//HTTP request
		CloseableHttpClient httpclient = connection.getHttpClient();
		try {
			HttpPost post = connection.getHttpPost(frparIndex + "/" + getElasticType() + "/" + getId(o), serialized);
			String responseBody = httpclient.execute(post, new StringResponseHandler());
			LOGGER.debug(">>>>> response: " + responseBody);
		}
		finally {
			httpclient.close();
		}
	}

	@Override
	public T get(String id) throws Exception {
		//HTTP request
		String responseBody = null;
		CloseableHttpClient httpclient = connection.getHttpClient();
		try {
			HttpGet get = connection.getHttpGet(frparIndex + "/" + getElasticType() + "/" + id);
			responseBody = httpclient.execute(get, new StringResponseHandler());
			LOGGER.debug(">>>>> response: " + responseBody);
		}
		finally {
			httpclient.close();
		}

		//binding
		ObjectMapper mapper = jacksonHelper.getObjectMapper();
		T t = mapper.readValue(responseBody, getTypeClass());
		return t;
	}

	@Override
	public void deleteAll() throws Exception {
		//HTTP request
		String responseBody = null;
		CloseableHttpClient httpclient = connection.getHttpClient();
		try {
			HttpDelete del = connection.getHttpDelete(frparIndex);
			responseBody = httpclient.execute(del, new StringResponseHandler());
			LOGGER.debug(">>>>> response: " + responseBody);
		}
		finally {
			httpclient.close();
		}
	}

	@Override
	public void createIndexMapping() throws Exception {

		//load mapping file
		File mappingFile = new File("frpar-index.json");
		String mapping = FileUtils.readFileToString(mappingFile);

		//HTTP request
		CloseableHttpClient httpclient = connection.getHttpClient();
		try {
			HttpPut put = connection.getHttpPut(frparIndex, mapping);
			String responseBody = httpclient.execute(put, new StringResponseHandler());
			LOGGER.debug(">>>>> response: " + responseBody);
		}
		finally {
			httpclient.close();
		}
	}

	public abstract String getElasticType();

	public abstract String getId(T t);

	public abstract Class<T> getTypeClass();

}
