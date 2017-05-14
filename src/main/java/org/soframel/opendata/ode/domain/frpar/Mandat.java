package org.soframel.opendata.ode.domain.frpar;

import java.time.LocalDate;
import java.util.List;

public class Mandat {

	private TypeMandat type;
	private String uid;
	private int legislature;
	private String typeOrgane;
	private LocalDate dateDebut;
	private LocalDate datePublication;
	private LocalDate dateFin;
	private String qualite;
	private List<String> organesRef;

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

	public List<String> getOrganesRef() {
		return organesRef;
	}

	public void setOrganesRef(List<String> organesRef) {
		this.organesRef = organesRef;
	}

	@Override
	public String toString() {
		return "Mandat [uid=" + uid + ", legislature=" + legislature
				+ ", organe=" + typeOrgane + ", dateDebut=" + dateDebut
				+ ", datePublication=" + datePublication + ", dateFin="
				+ dateFin + ", qualite=" + qualite + ", organesRef="
				+ organesRef + "]";
	}

	public TypeMandat getType() {
		return type;
	}

	public void setType(TypeMandat type) {
		this.type = type;
	}

}
