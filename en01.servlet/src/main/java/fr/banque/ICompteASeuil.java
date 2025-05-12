package fr.banque;

/**
 * Interface qui représente un compte à seuil.
 */
public interface ICompteASeuil {

	/**
	 * Récupère le seuil.
	 *
	 * @return le seuil
	 */
	public abstract double getSeuil();

	/**
	 * Modifie la valeur du seuil.
	 *
	 * @param unSeuil le nouveau seuil
	 */
	public abstract void setSeuil(double unSeuil);

	/**
	 * Retire un montant du compte. <br>
	 * <br>
	 * Sauf si le seuil est atteint ou dépassé.
	 *
	 * @param unMontant le montant retiré du compte
	 * @throws BanqueException si pas assez d'argent
	 */
	public abstract void retirer(double unMontant) throws BanqueException;

}
