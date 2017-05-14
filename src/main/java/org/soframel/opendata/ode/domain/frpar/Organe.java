package org.soframel.opendata.ode.domain.frpar;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Organe {

	private TypeOrgane type;

	@JsonIgnore
	private String uid;
	private String codeType;
	private String libelle;
	private String libelleEdition;
	private String libelleAbrege;
	private String libelleAbrev;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateDebut;
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateAgrement;
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateFin;
	private String regime;
	private int legislature;

	public TypeOrgane getType() {
		return type;
	}

	public void setType(TypeOrgane type) {
		this.type = type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelleEdition() {
		return libelleEdition;
	}

	public void setLibelleEdition(String libelleEdition) {
		this.libelleEdition = libelleEdition;
	}

	public String getLibelleAbrege() {
		return libelleAbrege;
	}

	public void setLibelleAbrege(String libelleAbrege) {
		this.libelleAbrege = libelleAbrege;
	}

	public String getLibelleAbrev() {
		return libelleAbrev;
	}

	public void setLibelleAbrev(String libelleAbrev) {
		this.libelleAbrev = libelleAbrev;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateAgrement() {
		return dateAgrement;
	}

	public void setDateAgrement(LocalDate dateAgrement) {
		this.dateAgrement = dateAgrement;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public int getLegislature() {
		return legislature;
	}

	public void setLegislature(int legislature) {
		this.legislature = legislature;
	}

	@Override
	public String toString() {
		return "Organe [type=" + type + ", uid=" + uid + ", codeType=" + codeType + ", libelle=" + libelle + ", libelleEdition=" + libelleEdition + ", libelleAbrege=" + libelleAbrege + ", libelleAbrev=" + libelleAbrev + ", dateDebut=" + dateDebut + ", dateAgrement=" + dateAgrement + ", dateFin=" + dateFin + ", regime=" + regime + ", legislature=" + legislature + "]";
	}

}
