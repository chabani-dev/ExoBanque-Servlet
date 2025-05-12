// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.banque;

import java.io.Serial;

/**
 * Ceci est la classe Compte rémunéré.
 */
public class CompteRemunere extends Compte implements ICompteRemunere {
	@Serial
	private static final long serialVersionUID = 1L;

	private double taux;

	/**
	 * Constructeur.
	 */
	public CompteRemunere() {
		this(-1, 0D, 0D);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param unNumero       le numéro du compte
	 * @param unSoldeInitial le solde initial du compte
	 * @param unTaux         un taux entre [0, 1[
	 */
	public CompteRemunere(int unNumero, double unSoldeInitial, double unTaux) {
		super(unNumero, unSoldeInitial);
		this.setTaux(unTaux);
	}

	@Override
	public double getTaux() {
		return this.taux;
	}

	@Override
	public void setTaux(double unTaux) {
		if (unTaux < 0 || unTaux >= 1) {
			System.err.println("Le taux doit être entre [0, 1[");
		} else {
			this.taux = unTaux;
		}
	}

	@Override
	public double calculerInterets() {
		return super.getSolde() * this.getTaux();
	}

	@Override
	public void verserInterets() {
		super.ajouter(this.calculerInterets());
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append(super.toString());
		sb.delete(sb.length() - 1, sb.length());
		sb.append(", taux=").append(this.getTaux()).append('}');
		return sb.toString();
	}

}
