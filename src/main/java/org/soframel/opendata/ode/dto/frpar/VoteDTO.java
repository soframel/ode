package org.soframel.opendata.ode.dto.frpar;

import java.time.LocalDate;

import org.soframel.opendata.ode.domain.frpar.CausePositionVote;
import org.soframel.opendata.ode.domain.frpar.PositionVote;

public class VoteDTO {
	private String voteId;

	private String acteurRef;
	private String mandatRef;
	private PositionVote vote;
	private CausePositionVote causePositionVote;
	private String scrutinId;
	private String voteGroupeId;
	//copied from scrutin
	private LocalDate scrutin_dateScrutin;
	private String scrutin_titre;
	private String scrutin_objet;
	//copied from acteur
	private String acteur_civilite;
	private String acteur_prenom;
	private String acteur_nom;

	public void generateVoteId() {
		voteId = scrutinId + "-" + acteurRef + "-" + mandatRef;
	}

	public String getActeurRef() {
		return acteurRef;
	}

	public void setActeurRef(String acteurRef) {
		this.acteurRef = acteurRef;
	}

	public String getMandatRef() {
		return mandatRef;
	}

	public void setMandatRef(String mandatRef) {
		this.mandatRef = mandatRef;
	}

	public PositionVote getVote() {
		return vote;
	}

	public void setVote(PositionVote vote) {
		this.vote = vote;
	}

	public CausePositionVote getCausePositionVote() {
		return causePositionVote;
	}

	public void setCausePositionVote(CausePositionVote causePositionVote) {
		this.causePositionVote = causePositionVote;
	}

	public String getVoteId() {
		return voteId;
	}

	public String getScrutinId() {
		return scrutinId;
	}

	public void setScrutinId(String scrutinId) {
		this.scrutinId = scrutinId;
	}

	public String getVoteGroupeId() {
		return voteGroupeId;
	}

	public void setVoteGroupeId(String voteGroupeId) {
		this.voteGroupeId = voteGroupeId;
	}

	public LocalDate getScrutin_dateScrutin() {
		return scrutin_dateScrutin;
	}

	public void setScrutin_dateScrutin(LocalDate scrutin_dateScrutin) {
		this.scrutin_dateScrutin = scrutin_dateScrutin;
	}

	public String getScrutin_titre() {
		return scrutin_titre;
	}

	public void setScrutin_titre(String scrutin_titre) {
		this.scrutin_titre = scrutin_titre;
	}

	public String getScrutin_objet() {
		return scrutin_objet;
	}

	public void setScrutin_objet(String scrutin_objet) {
		this.scrutin_objet = scrutin_objet;
	}

	public String getActeur_civilite() {
		return acteur_civilite;
	}

	public void setActeur_civilite(String acteur_civilite) {
		this.acteur_civilite = acteur_civilite;
	}

	public String getActeur_prenom() {
		return acteur_prenom;
	}

	public void setActeur_prenom(String acteur_prenom) {
		this.acteur_prenom = acteur_prenom;
	}

	public String getActeur_nom() {
		return acteur_nom;
	}

	public void setActeur_nom(String acteur_nom) {
		this.acteur_nom = acteur_nom;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	@Override
	public String toString() {
		return "VoteDTO [voteId=" + voteId + ", acteurRef=" + acteurRef + ", mandatRef=" + mandatRef + ", vote=" + vote + ", causePositionVote=" + causePositionVote + ", scrutinId=" + scrutinId + ", voteGroupeId=" + voteGroupeId + ", scrutin_dateScrutin=" + scrutin_dateScrutin + ", scrutin_titre=" + scrutin_titre + ", scrutin_objet=" + scrutin_objet + ", acteur_civilite=" + acteur_civilite + ", acteur_prenom=" + acteur_prenom + ", acteur_nom=" + acteur_nom + "]";
	}

}
