// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.banque;

import java.io.Serial;
import java.io.Serializable;

/**
 * Ceci est la classe Compte.
 */
public class Compte implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private double solde;
	private int numero;
	private String libelle;

	/**
	 * Constructeur de l'objet. <br>
	 * <br>
	 * Par défaut le compte aura un numéro = -1 et un solde de 0
	 */
	public Compte() {
		this(-1, 0D);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero       le numéro du compte
	 * @param unSoldeInitial le solde initial du compte
	 */
	public Compte(int unNumero, double unSoldeInitial) {
		super();
		// On a choisit de ne pas faire de méthode setNumero
		this.numero = unNumero;
		this.setSolde(unSoldeInitial);
	}

	/**
	 * Récupère le libellé du compte.
	 *
	 * @return le libellé du compte
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Fixe le libellé du compte. <br>
	 *
	 * @param unLibelle le nouveau libellé du compte
	 */
	public void setLibelle(String unLibelle) {
		this.libelle = unLibelle;
	}

	/**
	 * Récupère le solde du compte.
	 *
	 * @return le solde du compte
	 */
	public double getSolde() {
		return this.solde;
	}

	/**
	 * Fixe le solde du compte. <br>
	 *
	 * @param unSolde le nouveau solde du compte
	 */
	public void setSolde(double unSolde) {
		this.solde = unSolde;
	}

	/**
	 * Récupère le numéro du compte. <br>
	 *
	 * @return le numero du compte
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * Ajoute un montant au compte. <br>
	 *
	 * @param unMontant le montant ajouté au compte
	 */
	public void ajouter(double unMontant) {
		this.setSolde(this.getSolde() + unMontant);
	}

	/**
	 * Retire un montant du compte. <br>
	 *
	 * @param unMontant le montant retiré du compte
	 * @throws BanqueException si pas assez d'argent
	 */
	public void retirer(double unMontant) throws BanqueException {
		this.setSolde(this.getSolde() - unMontant);
	}

	/**
	 * Formatage du compte sous forme de String utilisable directement par
	 * System.out.println(..);.
	 *
	 * @return une représentation chainée de l'objet
	 */
	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" {Id=");
		sb.append(this.getNumero());
		sb.append(", Solde=");
		sb.append(this.getSolde());
		sb.append('}');
		return sb.toString();
	}

	/**
	 * Indique si deux comptes sont égaux. <br>
	 * <br>
	 * Deux comptes sont égaux si ils ont le même numéro d'identification.
	 *
	 * @param obj l'objet qui sera comparé a this
	 * @return <code>true</code> si les deux sont égaux et <code>false</code> sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Compte c) {
			return this.getNumero() == c.getNumero();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getClass().getName() + "_" + this.getNumero()).hashCode();
	}
}
