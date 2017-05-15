package org.soframel.opendata.ode.domain.frpar;

public class VotesGroupe {

	private String id;
	private String scrutinId;

	private String organeRef;
	private int nbMembresGroupe;
	private PositionVote positionMajoritaire;
	private int votesPour;
	private int votesContre;
	private int nbAbstentions;
	private int nbNonVotants;

	public void generateId() {
		id = scrutinId + "-" + organeRef;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScrutinId() {
		return scrutinId;
	}

	public void setScrutinId(String scrutinId) {
		this.scrutinId = scrutinId;
	}

	@Override
	public String toString() {
		return "VotesGroupe [scrutinId=" + scrutinId + ", id=" + id + ", organeRef=" + organeRef + ", nbMembresGroupe=" + nbMembresGroupe + ", positionMajoritaire=" + positionMajoritaire + ", votesPour=" + votesPour + ", votesContre=" + votesContre + ", nbAbstentions=" + nbAbstentions + ", nbNonVotants=" + nbNonVotants + "]";
	}

}
