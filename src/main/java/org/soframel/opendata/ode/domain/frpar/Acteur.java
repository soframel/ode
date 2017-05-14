package org.soframel.opendata.ode.domain.frpar;

import java.time.LocalDate;
import java.util.List;

public class Acteur {

	private String uid;
	private String civilite;
	private String prenom;
	private String nom;
	private String alpha;
	private LocalDate dateNaissance;
	private String villeNaissance;
	private String depNaissance;
	private String paysNaissance;
	private LocalDate dateDeces;
	private String profession;
	private String catSocPro;
	private String famSocPro;
	private List<Mandat> mandats;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getVilleNaissance() {
		return villeNaissance;
	}

	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

	public String getDepNaissance() {
		return depNaissance;
	}

	public void setDepNaissance(String depNaissance) {
		this.depNaissance = depNaissance;
	}

	public String getPaysNaissance() {
		return paysNaissance;
	}

	public void setPaysNaissance(String paysNaissance) {
		this.paysNaissance = paysNaissance;
	}

	public LocalDate getDateDeces() {
		return dateDeces;
	}

	public void setDateDeces(LocalDate dateDeces) {
		this.dateDeces = dateDeces;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCatSocPro() {
		return catSocPro;
	}

	public void setCatSocPro(String catSocPro) {
		this.catSocPro = catSocPro;
	}

	public String getFamSocPro() {
		return famSocPro;
	}

	public void setFamSocPro(String famSocPro) {
		this.famSocPro = famSocPro;
	}

	@Override
	public String toString() {
		return "Acteur [uid=" + uid + ", civilite=" + civilite + ", prenom="
				+ prenom + ", nom=" + nom + ", alpha=" + alpha
				+ ", dateNaissance=" + dateNaissance + ", villeNaissance="
				+ villeNaissance + ", depNaissance=" + depNaissance
				+ ", paysNaissance=" + paysNaissance + ", dateDeces="
				+ dateDeces + ", profession=" + profession + ", catSocPro="
				+ catSocPro + ", famSocPro=" + famSocPro + "]";
	}

	public List<Mandat> getMandats() {
		return mandats;
	}

	public void setMandats(List<Mandat> mandats) {
		this.mandats = mandats;
	}

}
