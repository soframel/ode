package org.soframel.opendata.ode.domain.frpar;


public class VotesGroupe {

	private String organeRef;
	private int nbMembresGroupe;
	private PositionVote positionMajoritaire;
	private int votesPour;
	private int votesContre;
	private int nbAbstentions;
	private int nbNonVotants;
	public String getOrganeRef() {
		return organeRef;
	}
	public void setOrganeRef(String organeRef) {
		this.organeRef = organeRef;
	}
	public int getNbMembresGroupe() {
		return nbMembresGroupe;
	}
	public void setNbMembresGroupe(int nbMembresGroupe) {
		this.nbMembresGroupe = nbMembresGroupe;
	}
	public PositionVote getPositionMajoritaire() {
		return positionMajoritaire;
	}
	public void setPositionMajoritaire(PositionVote positionMajoritaire) {
		this.positionMajoritaire = positionMajoritaire;
	}
	public int getVotesPour() {
		return votesPour;
	}
	public void setVotesPour(int votesPour) {
		this.votesPour = votesPour;
	}
	public int getVotesContre() {
		return votesContre;
	}
	public void setVotesContre(int votesContre) {
		this.votesContre = votesContre;
	}
	public int getNbAbstentions() {
		return nbAbstentions;
	}
	public void setNbAbstentions(int nbAbsentions) {
		this.nbAbstentions = nbAbsentions;
	}
	public int getNbNonVotants() {
		return nbNonVotants;
	}
	public void setNbNonVotants(int nbnonVotants) {
		this.nbNonVotants = nbnonVotants;
	}
	@Override
	public String toString() {
		return "VotesGroupe [organeRef=" + organeRef + ", nbMembresGroupe="
				+ nbMembresGroupe + ", positionMajoritaire="
				+ positionMajoritaire + ", votesPour=" + votesPour
				+ ", votesContre=" + votesContre + ", nbAbsentions="
				+ nbAbstentions + ", nbnonVotants=" + nbNonVotants + "]";
	}
	
	
	
}
