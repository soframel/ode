package org.soframel.opendata.ode;

import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.domain.frpar.Scrutin;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.domain.frpar.VotesGroupe;
import org.soframel.opendata.ode.parsers.frpar.acteurs.ActeursParser;
import org.soframel.opendata.ode.parsers.frpar.acteurs.OrganesParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.ScrutinsParser;
import org.soframel.opendata.ode.parsers.frpar.scrutins.VotesParser;
import org.soframel.opendata.ode.repository.ODEHttpConnection;
import org.soframel.opendata.ode.repository.ODERepository;
import org.soframel.opendata.ode.repository.elastic.ElasticConnection;
import org.soframel.opendata.ode.repository.frpar.elastic.ActeurRepositoryElastic;
import org.soframel.opendata.ode.repository.frpar.elastic.MandatRepositoryElastic;
import org.soframel.opendata.ode.repository.frpar.elastic.OrganeRepositoryElastic;
import org.soframel.opendata.ode.repository.frpar.elastic.ScrutinRepositoryElastic;
import org.soframel.opendata.ode.repository.frpar.elastic.VoteRepositoryElastic;
import org.soframel.opendata.ode.repository.frpar.elastic.VotesGroupeRepositoryElastic;
import org.soframel.opendata.ode.scripts.frpar.FrparProcessor;
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

	@Bean(name = "acteurRepository")
	public ODERepository<Acteur> createActeurRepository() {
		ODERepository<Acteur> repo = new ActeurRepositoryElastic();
		return repo;
	}

	@Bean(name = "mandatRepository")
	public ODERepository<Mandat> createMandatRepository() {
		ODERepository<Mandat> repo = new MandatRepositoryElastic();
		return repo;
	}

	@Bean(name = "scrutinRepository")
	public ODERepository<Scrutin> createScrutinRepository() {
		ODERepository<Scrutin> repo = new ScrutinRepositoryElastic();
		return repo;
	}

	@Bean(name = "votesGroupeRepository")
	public ODERepository<VotesGroupe> createVotesGroupeRepository() {
		ODERepository<VotesGroupe> repo = new VotesGroupeRepositoryElastic();
		return repo;
	}

	@Bean(name = "voteRepository")
	public ODERepository<Vote> createVoteRepository() {
		ODERepository<Vote> repo = new VoteRepositoryElastic();
		return repo;
	}

	@Bean(name = "jacksonHelper")
	public JacksonHelper createJacksonHelper() {
		JacksonHelper h = new JacksonHelper();
		return h;
	}

	@Bean(name = "frparProcessor")
	public FrparProcessor createFrparProcessor() {
		FrparProcessor h = new FrparProcessor();
		return h;
	}

}
