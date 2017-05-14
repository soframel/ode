package org.soframel.opendata.ode.parsers;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractContentHandler extends DefaultHandler {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendPattern("yyyy-MM-dd").toFormatter();

	protected StringBuffer currentCharacters;
	protected ArrayDeque<String> namesStack;

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentCharacters.append(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		log.info("Finished parsing XML");
		namesStack.clear();
	}

	protected String removeNewLinesDoubleWhitespaces(String text) {
		String newString = text.replaceAll("\\s+", " ");
		return newString.trim();
	}

}
