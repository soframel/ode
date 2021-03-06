package org.soframel.opendata.ode.parsers.frpar.acteurs;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.parsers.OpenDataAbstractParser;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ActeursParser extends OpenDataAbstractParser {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ODERepository<Acteur> repository;
	@Autowired
	private ODERepository<Mandat> mandatRepository;

	@Override
	public void parseAndInsert(InputStream in) {
		try {
			XMLReader xmlReader = this.getSAXXMLReader();
			xmlReader.setContentHandler(new ActeursContentHandler(repository, mandatRepository));
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
		log.info("finished parsing acteurs");
	}

}
