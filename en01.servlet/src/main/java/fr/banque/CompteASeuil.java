// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.banque;

import java.io.Serial;

/**
 * Ceci est la classe Compte a seuil. <br>
 * <br>
 * Il n'est pas possible de descendre en dessous du seuil.
 */
public class CompteASeuil extends Compte implements ICompteASeuil {
	@Serial
	private static final long serialVersionUID = 1L;

	private double seuil;

	/**
	 * Constructeur. Le seuil par defaut est de 0
	 */
	public CompteASeuil() {
		this(-1, 0D, 0D);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero       le numéro du compte
	 * @param unSoldeInitial le solde initial du compte
	 * @param unSeuil        un seuil
	 */
	public CompteASeuil(int unNumero, double unSoldeInitial, double unSeuil) {
		super(unNumero, unSoldeInitial);
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
		var simu = this.getSolde() - unMontant;
		if (simu <= this.getSeuil()) {
			// Pas bon, on ne fait rien, pour l'instant
			throw new BanqueException(
					"Pas possible de retirer la somme " + unMontant + " sur le compte " + this.getNumero());
		}
		super.retirer(unMontant);
	}
}
