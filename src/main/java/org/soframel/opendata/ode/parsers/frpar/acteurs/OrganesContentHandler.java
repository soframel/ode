package org.soframel.opendata.ode.parsers.frpar.acteurs;

import java.time.LocalDate;
import java.util.ArrayDeque;

import org.soframel.opendata.ode.domain.frpar.Organe;
import org.soframel.opendata.ode.domain.frpar.TypeOrgane;
import org.soframel.opendata.ode.parsers.AbstractContentHandler;
import org.soframel.opendata.ode.repository.ODERepository;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class OrganesContentHandler extends AbstractContentHandler {

	private Organe organe;
	private ODERepository<Organe> repo;

	public OrganesContentHandler(ODERepository<Organe> organeRepository) {
		this.repo = organeRepository;
		namesStack = new ArrayDeque<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentCharacters = new StringBuffer();
		namesStack.push(localName);
		if (localName.equals("organe")) {
			log.debug("parsing organe");
			organe = new Organe();
			if (attributes.getLength() > 0) {
				organe.setType(TypeOrgane.valueOf(attributes.getValue(0)));
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String currentElementName = namesStack.pop();
		String previousElementName = namesStack.peek();

		if (localName.equals("organe")) {
			log.debug("inserting Organe " + organe.getUid());
			try {
				repo.save(organe);
			}
			catch (Exception e) {
				throw new SAXException("Exception while saving organe: " + e.getMessage(), e);
			}

		}
		else if ("organe".equals(previousElementName)) {
			if (localName.equals("uid")) {
				organe.setUid(currentCharacters.toString());
			}
			else if (localName.equals("codeType")) {
				organe.setCodeType(currentCharacters.toString());
			}
			else if (localName.equals("libelle")) {
				organe.setLibelle(currentCharacters.toString());
			}
			else if (localName.equals("libelleEdition")) {
				organe.setLibelleEdition(removeNewLinesDoubleWhitespaces(currentCharacters.toString()));
			}
			else if (localName.equals("libelleAbrege")) {
				organe.setLibelleAbrege(currentCharacters.toString());
			}
			else if (localName.equals("libelleAbrev")) {
				organe.setLibelleAbrev(currentCharacters.toString());
			}
			else if (localName.equals("regime")) {
				organe.setRegime(currentCharacters.toString());
			}
			else if (localName.equals("legislature") && currentCharacters.length() > 0) {
				organe.setLegislature(Integer.valueOf(currentCharacters.toString()));
			}
		}
		else if ("viMoDe".equals(previousElementName)) {
			if (localName.equals("dateDebut") && currentCharacters.length() > 0) {
				organe.setDateDebut(LocalDate.parse(currentCharacters.toString(), formatter));
			}
			else if (localName.equals("dateAgrement") && currentCharacters.length() > 0) {
				organe.setDateAgrement(LocalDate.parse(currentCharacters.toString(), formatter));
			}
			else if (localName.equals("dateFin") && currentCharacters.length() > 0) {
				organe.setDateFin(LocalDate.parse(currentCharacters.toString(), formatter));
			}
		}
		currentCharacters = new StringBuffer();
	}

}
