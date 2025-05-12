// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.banque;

import java.io.Serial;

/**
 * Ceci est la classe Compte à seuil rémunéré. <br>
 * <br>
 * On a choisi ici de prendre comme parent le CompteRemunere. <br>
 * Ce choix est basé sur le fait que c'est la classe qui a le plus de code. <br>
 * Ce qui nous fait le moins de choses a recopier.
 */
public class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuil {
	@Serial
	private static final long serialVersionUID = 1L;

	private double seuil;

	/**
	 * Constructeur. <br>
	 * <br>
	 * Le seuil par défaut est de 0
	 */
	public CompteASeuilRemunere() {
		this(-1, 0D, 0D, 0D);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero       le numéro du compte
	 * @param unSoldeInitial le solde initial du compte
	 * @param unTaux         un taux [0, 1[
	 * @param unSeuil        un seuil
	 */
	public CompteASeuilRemunere(int unNumero, double unSoldeInitial, double unTaux, double unSeuil) {
		super(unNumero, unSoldeInitial, unTaux);
		this.setSeuil(unSeuil);
	}

	@Override
	public double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(double unSeuil) {
		this.seuil = unSeuil;
	}

	@Override
	public String toString() {
		var sb = new StringBuilder();
		sb.append(super.toString());
		sb.delete(sb.length() - 1, sb.length());
		sb.append(", seuil=").append(this.getSeuil()).append('}');
		return sb.toString();
	}

	@Override
	public void retirer(double unMontant) throws BanqueException {
		// Ici on a recopié le code qui était dans CompteASeuil
		var simu = this.getSolde() - unMontant;
		if (simu <= this.getSeuil()) {
			// Pas bon, on ne fait rien, pour l'instant
			throw new BanqueException(
					"Pas possible de retirer la somme " + unMontant + " sur le compte " + this.getNumero());
		}
		super.retirer(unMontant);
	}
}
