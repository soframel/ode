package org.soframel.opendata.ode.parsers.frpar.acteurs;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import org.soframel.opendata.ode.domain.frpar.Acteur;
import org.soframel.opendata.ode.domain.frpar.Mandat;
import org.soframel.opendata.ode.domain.frpar.TypeMandat;
import org.soframel.opendata.ode.parsers.AbstractContentHandler;
import org.soframel.opendata.ode.repository.ODERepository;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ActeursContentHandler extends AbstractContentHandler {

	private Acteur acteur;

	private Mandat mandat;

	private ODERepository<Acteur> acteurRepo;
	private ODERepository<Mandat> mandatRepo;

	private List<Mandat> mandats = new ArrayList<Mandat>();

	public ActeursContentHandler(ODERepository<Acteur> repo, ODERepository<Mandat> mandatRepo) {
		this.acteurRepo = repo;
		this.mandatRepo = mandatRepo;
		namesStack = new ArrayDeque<>();
		mandats = new ArrayList<Mandat>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentCharacters = new StringBuffer();
		namesStack.push(localName);
		if (localName.equals("acteur")) {
			log.debug("parsing acteur");
			acteur = new Acteur();
		}
		else if (localName.equals("mandat")) {
			log.debug("parsing mandat");
			mandat = new Mandat();
			if (attributes.getLength() > 0) {
				mandat.setType(TypeMandat.valueOf(attributes.getValue(0)));
			}
			List<String> organesRef = new ArrayList<String>();
			mandat.setOrganesRef(organesRef);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		String currentElementName = namesStack.pop();
		String previousElementName = namesStack.peek();

		if (localName.equals("acteur")) {
			log.debug("inserting Acteur " + acteur.getUid());
			try {
				acteurRepo.save(acteur);
			}
			catch (Exception e) {
				throw new SAXException(e);
			}

			for (Mandat m : mandats) {
				log.debug("inserting Mandat " + m.getUid());
				try {
					mandatRepo.save(m);
				}
				catch (Exception e) {
					throw new SAXException(e);
				}
			}
		}
		else if (localName.equals("mandat")) {

			mandat.setActeurId(acteur.getUid());
			mandats.add(mandat);
			//insert mandat after acteur: add it to list only

			mandat = null;
		}
		else if ("acteur".equals(previousElementName)) {
			if (localName.equals("uid")) {
				acteur.setUid(currentCharacters.toString());
			}
		}
		else if ("ident".equals(previousElementName)) {
			if (localName.equals("civ")) {
				acteur.setCivilite(currentCharacters.toString());
			}
			else if (localName.equals("prenom")) {
				acteur.setPrenom(currentCharacters.toString());
			}
			else if (localName.equals("nom")) {
				acteur.setNom(currentCharacters.toString());
			}
			else if (localName.equals("alpha")) {
				acteur.setAlpha(currentCharacters.toString());
			}
		}
		else if ("infoNaissance".equals(previousElementName)) {
			if (localName.equals("dateNais") && currentCharacters.length() > 0) {
				acteur.setDateNaissance(LocalDate.parse(currentCharacters.toString(), formatter));
			}
			else if (localName.equals("villeNais")) {
				acteur.setVilleNaissance(currentCharacters.toString());
			}
			else if (localName.equals("depNais")) {
				acteur.setDepNaissance(currentCharacters.toString());
			}
			else if (localName.equals("paysNais") && currentCharacters.length() > 0) {
				acteur.setPaysNaissance(currentCharacters.toString());
			}
		}
		else if ("etatCivil".equals(previousElementName)) {
			if (localName.equals("dateDeces") && currentCharacters.length() > 0) {
				acteur.setDateDeces(LocalDate.parse(currentCharacters.toString(), formatter));
			}
		}
		else if ("profession".equals(previousElementName)) {
			if (localName.equals("libelleCourant")) {
				acteur.setProfession(this.removeNewLinesDoubleWhitespaces(currentCharacters.toString()));
			}
		}
		else if ("socProcINSEE".equals(previousElementName)) {
			if (localName.equals("catSocPro")) {
				acteur.setCatSocPro(this.removeNewLinesDoubleWhitespaces(currentCharacters.toString()));
			}
			else if (localName.equals("famSocPro")) {
				acteur.setFamSocPro(this.removeNewLinesDoubleWhitespaces(currentCharacters.toString()));
			}
		}
		else if ("mandat".equals(previousElementName)) {
			if (localName.equals("uid")) {
				mandat.setUid(currentCharacters.toString());
			}
			else if (localName.equals("legislature") && currentCharacters.length() > 0) {
				mandat.setLegislature(Integer.parseInt(currentCharacters.toString()));
			}
			else if (localName.equals("typeOrgane") && currentCharacters.length() > 0) {
				mandat.setTypeOrgane(currentCharacters.toString());
			}
			else if (localName.equals("dateDebut") && currentCharacters.length() > 0) {
				mandat.setDateDebut(LocalDate.parse(currentCharacters.toString(), formatter));
			}
			else if (localName.equals("datePublication") && currentCharacters.length() > 0) {
				mandat.setDatePublication(LocalDate.parse(currentCharacters.toString(), formatter));
			}
			else if (localName.equals("dateFin") && currentCharacters.length() > 0) {
				mandat.setDateFin(LocalDate.parse(currentCharacters.toString(), formatter));
			}
		}
		else if ("infosQualite".equals(previousElementName)) {
			if (localName.equals("codeQualite")) {
				mandat.setQualite(currentCharacters.toString());
			}
		}
		else if ("organes".equals(previousElementName)) {
			if (localName.equals("organeRef")) {
				mandat.getOrganesRef().add(currentCharacters.toString());
			}
		}

		currentCharacters = new StringBuffer();
	}

}
