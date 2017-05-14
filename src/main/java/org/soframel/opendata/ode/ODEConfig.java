package org.soframel.opendata.ode;

import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.parsers.frpar.acteurs.ActeursParser;
import org.soframel.opendata.ode.parsers.frpar.acteurs.OrganesParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.ScrutinsParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.VotesParser;
import org.soframel.opendata.ode.repository.ODEHttpConnection;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.elastic.ElasticConnection;
import org.soframel.opendata.ode.repository.frpar.elastic.OrganeRepositoryElastic;
import org.soframel.opendata.ode.utils.JacksonHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration
@ComponentScan(basePackages = { "org.soframel.opendata.ode" })
public class ODEConfig {

	@Bean
	@Description("Scrutins parser")
	public ScrutinsParser createScrutinParser() {
		ScrutinsParser parser = new ScrutinsParser();
		return parser;
	}

	@Bean
	@Description("Votes parser")
	public VotesParser createVotesParser() {
		VotesParser parser = new VotesParser();
		return parser;
	}

	@Bean
	@Description("Acteurs parser")
	public ActeursParser createActeursParser() {
		ActeursParser parser = new ActeursParser();
		return parser;
	}

	@Bean
	@Description("Organes parser")
	public OrganesParser createOrganesParser() {
		OrganesParser parser = new OrganesParser();
		return parser;
	}

	@Bean
	public ODEHttpConnection createConnection() {
		ODEHttpConnection conn = new ElasticConnection();
		return conn;
	}

	@Bean(name = "organeRepository")
	public ODERepository<Organe> createOrganeRepository() {
		ODERepository<Organe> repo = new OrganeRepositoryElastic();
		return repo;
	}

	@Bean(name = "jacksonHelper")
	public JacksonHelper createJacksonHelper() {
		JacksonHelper h = new JacksonHelper();
		return h;
	}

}
