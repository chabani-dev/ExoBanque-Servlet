// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.banque;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe représentant une opération.
 */
public class Operation implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private int numero;
	private String libelle;
	private double montant;
	private LocalDate dateOp;
	private LocalTime heureOp;

	private int compteId;

	/**
	 * Constructeur. <br>
	 *
	 * Id aura une valeur de -1 par défaut.
	 */
	public Operation() {
		this(-1, null, 0, LocalDate.now(), LocalTime.now(), -1);
	}

	/**
	 * Constructeur.
	 *
	 * @param unNumero   un numéro d'opération
	 * @param unLibelle  un libellé
	 * @param unMontant  un montant
	 * @param uneDate    une date
	 * @param unCompteId un id de compte
	 *
	 */
	public Operation(int unNumero, String unLibelle, double unMontant, LocalDate uneDate, LocalTime uneHeure,
			int unCompteId) {
		super();
		this.setNumero(unNumero);
		this.setLibelle(unLibelle);
		this.setMontant(unMontant);
		this.setDateOp(uneDate);
		this.setHeureOp(uneHeure);
		this.setCompteId(unCompteId);
	}

	/**
	 * Récupère la valeur de l'attribut.
	 *
	 * @return la valeur de dateOp
	 */
	public LocalDate getDateOp() {
		return this.dateOp;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param pDateOp la nouvelle valeur de dateOp
	 */
	public void setDateOp(LocalDate pDateOp) {
		this.dateOp = pDateOp;
	}

	/**
	 * Récupère la valeur de l'attribut.
	 *
	 * @return la valeur de heureOp
	 */
	public LocalTime getHeureOp() {
		return this.heureOp;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param pHeureOp la nouvelle valeur de heureOp
	 */
	public void setHeureOp(LocalTime pHeureOp) {
		this.heureOp = pHeureOp;
	}

	/**
	 * Récupère la valeur de l'attribut.
	 *
	 * @return la valeur de numéro
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param unNumero la nouvelle valeur de numéro
	 */
	public void setNumero(int unNumero) {
		this.numero = unNumero;
	}

	/**
	 * Récupère la valeur de l'attribut.
	 *
	 * @return la valeur de libellé
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param unLibelle la nouvelle valeur de libellé
	 */
	public void setLibelle(String unLibelle) {
		this.libelle = unLibelle;
	}

	/**
	 * Récupère la valeur de l'attribut.
	 *
	 * @return la valeur de montant
	 */
	public double getMontant() {
		return this.montant;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param unMontant la nouvelle valeur de montant
	 */
	public void setMontant(double unMontant) {
		this.montant = unMontant;
	}

	/**
	 * Récupère la valeur de l'attribut.
	 *
	 * @return la valeur de compteId
	 */
	public int getCompteId() {
		return this.compteId;
	}

	/**
	 * Modifie la valeur de l'attribut.
	 *
	 * @param unCompteId la nouvelle valeur de compteId
	 */
	public void setCompteId(int unCompteId) {
		this.compteId = unCompteId;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName());
		builder.append(" {numero=");
		builder.append(this.getNumero());
		builder.append(", libelle=");
		builder.append(this.getLibelle());
		builder.append(", montant=");
		builder.append(this.getMontant());
		builder.append(", date=");
		builder.append(this.getDateOp());
		builder.append(", heure=");
		builder.append(this.getHeureOp());
		builder.append(", compteId=");
		builder.append(this.getCompteId());
		builder.append("}");
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Operation c) {
			return this.getNumero() == c.getNumero();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getClass().getName() + "_" + this.getNumero()).hashCode();
	}
}
