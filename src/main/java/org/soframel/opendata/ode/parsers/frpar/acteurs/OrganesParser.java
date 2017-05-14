package org.soframel.opendata.ode.parsers.frpar.acteurs;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.parsers.OpenDataAbstractParser;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class OrganesParser extends OpenDataAbstractParser {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ODERepository<Organe> organeRepository;

	@Override
	public void parseAndInsert(InputStream in) {
		try {
			XMLReader xmlReader = this.getSAXXMLReader();
			xmlReader.setContentHandler(new OrganesContentHandler(organeRepository));
			InputSource source = new InputSource(in);
			xmlReader.parse(source);
		}
		catch (ParserConfigurationException e) {
			log.error("ParserConfigurationException " + e.getMessage(), e);
		}
		catch (SAXException e) {
			log.error("SAXException " + e.getMessage(), e);
		}
		catch (IOException e) {
			log.error("IOException " + e.getMessage(), e);
		}
	}

	public ODERepository<Organe> getOrganeRepository() {
		return organeRepository;
	}

	public void setOrganeRepository(ODERepository<Organe> organeRepository) {
		this.organeRepository = organeRepository;
	}

}
