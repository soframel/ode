package org.soframel.opendata.ode.parsers.frpar.scrutins;

import java.util.ArrayDeque;

import org.soframel.opendata.ode.domain.frpar.CausePositionVote;
import org.soframel.opendata.ode.domain.frpar.PositionVote;
import org.soframel.opendata.ode.domain.frpar.Vote;
import org.soframel.opendata.ode.parsers.AbstractContentHandler;
import org.soframel.opendata.ode.repository.ODERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class VotesContentHandler extends AbstractContentHandler {

	private Vote vote;

	private String scrutinId;
	private PositionVote currentPosition;

	@Autowired
	private ODERepository<Vote> repository;

	public VotesContentHandler(ODERepository<Vote> repository) {
		this.repository = repository;
		namesStack = new ArrayDeque<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentCharacters = new StringBuffer();
		namesStack.push(localName);
		if (localName.equals("votant")) {
			vote = new Vote();
			vote.setScrutinId(scrutinId);
			vote.setVote(currentPosition);
		}
		else if (localName.equals("nonVotants")) {
			this.currentPosition = PositionVote.NONVOTANT;
		}
		else if (localName.equals("pours")) {
			this.currentPosition = PositionVote.POUR;
		}
		else if (localName.equals("contres")) {
			this.currentPosition = PositionVote.CONTRE;
		}
		else if (localName.equals("abstentions")) {
			this.currentPosition = PositionVote.ABSTENTION;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		namesStack.pop();
		String previousElementName = namesStack.peek();

		if (localName.equals("votant")) {
			vote.generateVoteId();
			log.debug("inserting vote " + vote.getVoteId());
			try {
				repository.save(vote);
			}
			catch (Exception e) {
				throw new SAXException(e);
			}
		}
		else if ("scrutin".equals(previousElementName)) {
			if (localName.equals("uid")) {
				this.scrutinId = currentCharacters.toString();
			}
		}
		else if ("votant".equals(previousElementName)) {
			if (localName.equals("acteurRef")) {
				vote.setActeurRef(currentCharacters.toString());
			}
			else if (localName.equals("mandatRef")) {
				vote.setMandatRef(currentCharacters.toString());
			}
			else if (localName.equals("causePositionVote")) {
				vote.setCausePositionVote(CausePositionVote.valueOf(currentCharacters.toString()));
			}
		}

		currentCharacters = new StringBuffer();
	}

}
