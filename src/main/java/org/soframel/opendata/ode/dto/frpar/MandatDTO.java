package org.soframel.opendata.ode.dto.frpar;

import java.time.LocalDate;

import org.soframel.opendata.ode.domain.frpar.TypeMandat;

public class MandatDTO {

	private String acteurId;
	private TypeMandat type;
	private String uid;
	private int legislature;
	private String typeOrgane;
	private LocalDate dateDebut;
	private LocalDate datePublication;
	private LocalDate dateFin;
	private String qualite;

	//copied from acteur
	private String acteur_civilite;
	private String acteur_prenom;
	private String acteur_nom;
	//copied from Organe
	private String organe_libelle;
	private String organe_uid;
	private String organe_codeType;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getLegislature() {
		return legislature;
	}

	public void setLegislature(int legislature) {
		this.legislature = legislature;
	}

	public String getTypeOrgane() {
		return typeOrgane;
	}

	public void setTypeOrgane(String organe) {
		this.typeOrgane = organe;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public TypeMandat getType() {
		return type;
	}

	public void setType(TypeMandat type) {
		this.type = type;
	}

	public String getActeurId() {
		return acteurId;
	}

	public void setActeurId(String acteurId) {
		this.acteurId = acteurId;
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

	public String getOrgane_libelle() {
		return organe_libelle;
	}

	public void setOrgane_libelle(String organe_libelle) {
		this.organe_libelle = organe_libelle;
	}

	public String getOrgane_uid() {
		return organe_uid;
	}

	public void setOrgane_uid(String organe_uid) {
		this.organe_uid = organe_uid;
	}

	public String getOrgane_codeType() {
		return organe_codeType;
	}

	public void setOrgane_codeType(String organe_codeType) {
		this.organe_codeType = organe_codeType;
	}

	@Override
	public String toString() {
		return "MandatDTO [acteurId=" + acteurId + ", type=" + type + ", uid=" + uid + ", legislature=" + legislature + ", typeOrgane=" + typeOrgane + ", dateDebut=" + dateDebut + ", datePublication=" + datePublication + ", dateFin=" + dateFin + ", qualite=" + qualite + ", acteur_civilite=" + acteur_civilite + ", acteur_prenom=" + acteur_prenom + ", acteur_nom=" + acteur_nom + ", organe_libelle=" + organe_libelle + ", organe_uid=" + organe_uid + ", organe_codeType=" + organe_codeType + "]";
	}

}
