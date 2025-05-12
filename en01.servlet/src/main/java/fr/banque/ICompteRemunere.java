package fr.banque;

/**
 * Interface qui représente un compte rémunéré.
 */
public interface ICompteRemunere {

	/**
	 * Récupère le taux.
	 *
	 * @return le taux
	 */
	public abstract double getTaux();

	/**
	 * Modifie la valeur du taux.
	 *
	 * @param unTaux le nouveau taux, entre [0, 1[.
	 */
	public abstract void setTaux(double unTaux);

	/**
	 * Calcule les intérêts par rapport au solde actuel.
	 *
	 * @return les intérêts par rapport au solde actuel.
	 */
	public abstract double calculerInterets();

	/**
	 * Verse les intérêts par rapport au solde actuel.
	 */
	public abstract void verserInterets();

}
