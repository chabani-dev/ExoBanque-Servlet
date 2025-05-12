// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.banque;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Ceci est la classe Client. <br>
 * <br>
 * Le client possède comme attributs des types Object ainsi que des types
 * simples. <br>
 */
public class Client implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private String nom;
	private String prenom;
	private int age;
	private int numero;
	private Map<Integer, Compte> comptes;

	/**
	 * Constructeur de l'objet. <br>
	 * <br>
	 * Par défaut le client aura un numéro = -1 et un age de 0
	 */
	public Client() {
		this(-1, null, null, 0);
	}

	/**
	 * Constructeur de l'objet.
	 *
	 * @param unNumero un numéro
	 * @param unNom    le nom du client
	 * @param unPrenom le prénom du client
	 * @param unAge    l'age du client
	 */
	public Client(int unNumero, String unNom, String unPrenom, int unAge) {
		super();
		this.setNom(unNom);
		this.setPrenom(unPrenom);
		this.setAge(unAge);
		this.comptes = new HashMap<>();
		this.setNumero(unNumero);
	}

	/**
	 * Retourne l'age du client.
	 *
	 * @return l'age du client
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Retourne le nom du client.
	 *
	 * @return le nom du client
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne le prénom du client.
	 *
	 * @return le prénom du client
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * Fixe l'age du client.
	 *
	 * @param unAge le nouvel age du client
	 */
	public void setAge(int unAge) {
		this.age = unAge;
	}

	/**
	 * Fixe le nom du client.
	 *
	 * @param unNom le nouveau nom du client
	 */
	public void setNom(String unNom) {
		this.nom = unNom;
	}

	/**
	 * Fixe le numéro du client. <br>
	 * <br>
	 * final = on ne peut pas l'overider
	 *
	 * @param unNumero le nouveau numéro du client
	 */
	public final void setNumero(int unNumero) {
		this.numero = unNumero;
	}

	/**
	 * Retourne le numéro du client.
	 *
	 * @return le numéro du client, -1 si ce client n'a pas de numéro
	 */
	public final int getNumero() {
		return this.numero;
	}

	/**
	 * Fixe le prénom du client.
	 *
	 * @param unPrenom le nouveau prénom du client
	 */
	public void setPrenom(String unPrenom) {
		this.prenom = unPrenom;
	}

	/**
	 * Retourne tous les comptes du client.
	 *
	 * @return les comptes du client.
	 */
	public Compte[] getComptes() {
		return this.comptes.values().toArray(new Compte[this.comptes.size()]);
	}

	/**
	 * Retourne un compte particulier. <br>
	 * <br>
	 * Le numéro d'un compte n'a AUCUN rapport avec son emplacement dans le tableau
	 * des comptes.
	 *
	 * @param unNumero numéro du compte
	 * @return le compte visé ou null si il n'existe pas
	 */
	public Compte getCompte(int unNumero) {
		return this.comptes.get(unNumero);
	}

	/**
	 * Ajoute un compte dans le tableau des comptes de l'utilisateur.
	 *
	 * @param unCompte le compte à ajouter
	 */
	public void ajouterCompte(Compte unCompte) {
		this.comptes.put(unCompte.getNumero(), unCompte);
	}

	/**
	 * Formatage du client sous forme de String utilisable directement par
	 * System.out.println(..);. <br>
	 * <br>
	 * La méthode toString() est héritée de la classe java.lang.Object, elle est
	 * très pratique quand on veut debuguer un programme. <br>
	 * Elle est automatiquement appellée quand on fait de la concaténation entre
	 * chaines de charactères : "a"+12+"b"+monClient. <br>
	 *
	 * @return une représentation chainée de l'objet
	 */
	@Override
	public String toString() {
		// L'utilisation du '+' entre chaine de charactères n'est pas très optimisée
		// Il est préférable d'utiliser un StringBuilder pour fabriquer une chaine de
		// charactères et éviter ainsi la
		// lourdeur d'exécution liée au '+' entre chaine de charactères
		var sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" {Nom=");
		sb.append(this.getNom());
		sb.append(", Prénom=");
		sb.append(this.getPrenom());
		sb.append(", Age=");
		sb.append(this.getAge());
		sb.append(", comptes=");
		sb.append(this.comptes);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public int hashCode() {
		// On ne se base que sur le numéro
		return (this.getClass().getName() + "_" + this.getNumero()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		// Class est un invariant, on peut faire usage de == ou != à la place de equals
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		// On ne se base que sur le numéro
		var other = (Client) obj;
		if (this.numero != other.numero) {
			return false;
		}
		return true;
	}
}
