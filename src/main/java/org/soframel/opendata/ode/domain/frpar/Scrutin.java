package org.soframel.opendata.ode.domain.frpar;

import java.time.LocalDate;

public class Scrutin {

	private String uid;
	private String organeRef;
	private LocalDate dateScrutin;
	private TypeVote typeVote;
	private boolean adopte;
	private String titre;
	private String demandeur;
	private String objet;
	private ModePublicationVotes modePublicationVotes;
	private int nbVotants;
	private int suffragesExprimes;
	private int suffragesRequis;
	private int votesPour;
	private int votesContre;
	private int abstention;
	private int nonVotants;

	//private List<VotesGroupe> votesGroupes = new ArrayList<VotesGroupe>();

	@Override
	public String toString() {
		return "Scrutin [uid=" + uid + ", organeRef=" + organeRef + ", dateScrutin=" + dateScrutin + ", typeVote=" + typeVote + ", adopte=" + adopte + ", titre=" + titre + ", demandeur=" + demandeur + ", objet=" + objet + ", modePublicationVotes=" + modePublicationVotes + ", nbVotants=" + nbVotants + ", suffragesExprimes=" + suffragesExprimes + ", suffragesRequis=" + suffragesRequis + ", votesPour=" + votesPour + ", votesContre=" + votesContre + ", abstention=" + abstention + ", nonVotants=" + nonVotants + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOrganeRef() {
		return organeRef;
	}

	public void setOrganeRef(String organeRef) {
		this.organeRef = organeRef;
	}

	public LocalDate getDateScrutin() {
		return dateScrutin;
	}

	public void setDateScrutin(LocalDate dateScrutin) {
		this.dateScrutin = dateScrutin;
	}

	public TypeVote getTypeVote() {
		return typeVote;
	}

	public void setTypeVote(TypeVote typeVote) {
		this.typeVote = typeVote;
	}

	public boolean isAdopte() {
		return adopte;
	}

	public void setAdopte(boolean adopte) {
		this.adopte = adopte;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(String demandeur) {
		this.demandeur = demandeur;
	}

	public String getObjet() {
		return objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public ModePublicationVotes getModePublicationVotes() {
		return modePublicationVotes;
	}

	public void setModePublicationVotes(ModePublicationVotes modePublicationVotes) {
		this.modePublicationVotes = modePublicationVotes;
	}

	public int getNbVotants() {
		return nbVotants;
	}

	public void setNbVotants(int nbVotants) {
		this.nbVotants = nbVotants;
	}

	public int getSuffragesExprimes() {
		return suffragesExprimes;
	}

	public void setSuffragesExprimes(int suffragesExprimes) {
		this.suffragesExprimes = suffragesExprimes;
	}

	public int getSuffragesRequis() {
		return suffragesRequis;
	}

	public void setSuffragesRequis(int suffragesRequis) {
		this.suffragesRequis = suffragesRequis;
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

	public int getAbstention() {
		return abstention;
	}

	public void setAbstention(int abstention) {
		this.abstention = abstention;
	}

	public int getNonVotants() {
		return nonVotants;
	}

	public void setNonVotants(int nonVotants) {
		this.nonVotants = nonVotants;
	}

	/*public List<VotesGroupe> getVotesGroupes() {
		return votesGroupes;
	}

	public void setVotesGroupes(List<VotesGroupe> votesGroupes) {
		this.votesGroupes = votesGroupes;
	}*/

}
