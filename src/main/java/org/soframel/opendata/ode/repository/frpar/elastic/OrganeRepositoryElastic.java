package org.soframel.opendata.ode.repository.frpar.elastic;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.elastic.ElasticConnection;
import org.soframel.opendata.ode.repository.elastic.StringResponseHandler;
import org.soframel.opendata.ode.utils.JacksonHelper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrganeRepositoryElastic implements ODERepository<Organe> {

	private static Logger LOGGER = Logger.getLogger(OrganeRepositoryElastic.class);

	@Autowired
	private ElasticConnection elastic;

	@Autowired
	private JacksonHelper jacksonHelper;

	private final static String frparIndex = "frpar";
	private final static String organeType = "organe";

	@Override
	public void save(Organe o) throws ClientProtocolException, IOException {
		//JSON mapping
		ObjectMapper mapper = jacksonHelper.getObjectMapper();
		String serialized = mapper.writeValueAsString(o);

		LOGGER.debug("Sending organe: " + serialized);

		//HTTP request
		CloseableHttpClient httpclient = elastic.getHttpClient();
		try {
			HttpPost post = elastic.getHttpPost(frparIndex + "/" + organeType + "/" + o.getUid(), serialized);
			String responseBody = httpclient.execute(post, new StringResponseHandler());
			LOGGER.debug(">>>>> response: " + responseBody);
		}
		finally {
			httpclient.close();
		}
	}

	@Override
	public Organe get(String id) throws Exception {
		//HTTP request
		String responseBody = null;
		CloseableHttpClient httpclient = elastic.getHttpClient();
		try {
			HttpGet get = elastic.getHttpGet(frparIndex + "/" + organeType + "/" + id);
			responseBody = httpclient.execute(get, new StringResponseHandler());
			LOGGER.debug(">>>>> response: " + responseBody);
		}
		finally {
			httpclient.close();
		}

		//binding
		ObjectMapper mapper = jacksonHelper.getObjectMapper();
		Organe organe = mapper.readValue(responseBody, Organe.class);
		return organe;
	}

}
