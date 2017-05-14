package org.soframel.opendata.ode.parsers.frpar.scrutins;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soframel.opendata.ode.parsers.OpenDataAbstractParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class ScrutinsParser extends OpenDataAbstractParser {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void parseAndInsert(InputStream in) {
		try {
			XMLReader xmlReader = this.getSAXXMLReader();
			xmlReader.setContentHandler(new ScrutinsContentHandler());
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

}
