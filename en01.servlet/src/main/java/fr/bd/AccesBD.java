// -#--------------------------------------
// -# ©Copyright Ferret Renaud 2019 -
// -# Email: admin@ferretrenaud.fr -
// -# All Rights Reserved. -
// -#--------------------------------------

package fr.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import fr.banque.Client;
import fr.banque.Compte;
import fr.banque.CompteASeuil;
import fr.banque.CompteASeuilRemunere;
import fr.banque.CompteRemunere;
import fr.banque.Operation;

/**
 * Cette classe fait un acces a une base de donnees. <br/>
 * Exemple d'utilisation <br/>
 * <!-- HTML generated using hilite.me
 * --><div style="background: #ffffff; overflow:auto;width:auto;border:solid
 * gray;border-width:.1em .1em .1em .8em;padding:.2em .6em;">
 *
 * <pre style="margin: 0; line-height: 125%">
 * <span style=
"color: #008800; font-weight: bold">final</span> String dbDriver <span style=
"color: #333333">=</span>
 * <span style=
"background-color: #fff0f0">&quot;com.mysql.cj.jdbc.Driver&quot;</span><span style
="color:
 * #333333">;</span>
 * <span style=
"color: #008800; font-weight: bold">final</span> String dbUrl <span style=
"color: #333333">=</span> <span
 * style="background-color:
 * #fff0f0">&quot;jdbc:mysql://localhost/banque?useSSL=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true&quot;</span><span
 * style="color: #333333">;</span>
 * <span style=
"color: #008800; font-weight: bold">final</span> String dbLogin <span style=
"color: #333333">=</span>
 * <span style="background-color: #fff0f0">&quot;root&quot;</span><span style=
"color: #333333">;</span>
 * <span style=
"color: #008800; font-weight: bold">final</span> String dbPwd <span style=
"color: #333333">=</span> <span
 * style="background-color: #fff0f0">&quot;root&quot;</span><span style=
"color: #333333">;</span>
 * AccesBD bd <span style="color: #333333">=</span> <span style=
"color: #008800; font-weight: bold">null</span><span
 * style="color: #333333">;</span>
 * <span style="color: #008800; font-weight: bold">try</span> <span style=
"color: #333333">{</span>
 * bd <span style="color: #333333">=</span> <span style=
"color: #008800; font-weight: bold">new</span> AccesBD<span
 * style="color: #333333">(</span>dbDriver<span style="color: #333333">);</span>
 * bd<span style="color: #333333">.</span><span style=
"color: #0000CC">seConnecter</span><span style="color:
 * #333333">(</span>dbUrl<span style=
"color: #333333">,</span> dbLogin<span style=
"color: #333333">,</span> dbPwd<span
 * style="color: #333333">);</span>
 * List<span style="color: #333333">&lt;</span>Client<span style=
"color: #333333">&gt;</span> lClient <span
 * style="color: #333333">=</span> bd<span style=
"color: #333333">.</span><span style="color:
 * #0000CC">selectUtilisateur</span><span style="color: #333333">();</span>
 * <span style="color: #008800; font-weight: bold">for</span> <span style=
"color: #333333">(</span>Client unClient <span
 * style="color: #333333">:</span> lClient<span style=
"color: #333333">)</span> <span style="color: #333333">{</span>
 * System<span style="color: #333333">.</span><span style=
"color: #0000CC">out</span><span style="color:
 * #333333">.</span><span style="color: #0000CC">println</span><span style=
"color: #333333">(</span>unClient<span
 * style="color: #333333">);</span>
 * <span style="color: #333333">}</span>
 * <span style="color: #333333">}</span> <span style=
"color: #008800; font-weight: bold">catch</span> <span
 * style="color: #333333">(</span>SQLException e<span style=
"color: #333333">)</span> <span style="color:
 * #333333">{</span>
 * e<span style="color: #333333">.</span><span style=
"color: #0000CC">printStackTrace</span><span style="color:
 * #333333">();</span>
 * <span style="color: #333333">}</span> <span style=
"color: #008800; font-weight: bold">finally</span> <span
 * style="color: #333333">{</span>
 * <span style="color: #008800; font-weight: bold">if</span> <span style=
"color: #333333">(</span>bd <span style="color:
 * #333333">!=</span> <span style=
"color: #008800; font-weight: bold">null</span><span style=
"color: #333333">)</span>
 * <span style="color: #333333">{</span>
 * bd<span style="color: #333333">.</span><span style=
"color: #0000CC">seDeconnecter</span><span style="color:
 * #333333">();</span>
 * <span style="color: #333333">}</span>
 * <span style="color: #333333">}</span>
 * </pre>
 *
 * </div>
 */
public class AccesBD {

	private Connection cxt;

	/**
	 * Constructeur de la classe qui fournit une connection. <br/>
	 *
	 * C'est dans cette methode qu'est charge le driver. <br/>
	 *
	 * @param aDriverName nom du Driver a charger
	 * @throws SQLException si une erreur survient
	 */
	public AccesBD(String aDriverName) throws SQLException {
		try {
			Class.forName(aDriverName);
		} catch (Exception cnf) {
			throw new SQLException("Impossible de charger le driver '" + aDriverName + "'", cnf);
		}
	}

	/**
	 * Methode de connexion de la base. <br/>
	 *
	 * C'est dans cette methode que l'on recupere un objet Connection
	 *
	 * @param aUrl      un url
	 * @param aLogin    un login
	 * @param aPassword un mot de passe
	 * @throws SQLException si un probleme survient
	 */
	public void seConnecter(String aUrl, String aLogin, String aPassword) throws SQLException {
		this.cxt = DriverManager.getConnection(aUrl, aLogin, aPassword);
	}

	/**
	 * Ferme tout.
	 *
	 * @param resultat le result set
	 * @param request  le statement
	 */
	private final void closeAll(ResultSet resultat, Statement request) {
		// Tres IMPORTANT, on ferme tout
		if (resultat != null) {
			try {
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (request != null) {
			try {
				request.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Methode de deconnexion de la base. <br/>
	 *
	 * C'est dans cette methode que l'on ferme l'objet Connection
	 */
	public void seDeconnecter() {
		try {
			if (this.cxt != null) {
				this.cxt.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	/**
	 * Methode qui verifie que le login et le password vont bien ensemble. <br/>
	 *
	 * @param unEmail un email
	 * @param unMdp   un mot de passe
	 * @return
	 *         <ul>
	 *         <li>null: si un probleme provient de l'email ou du mot de passe</li>
	 *         <li>l'id du client si tout se passe bien</li>
	 *         </ul>
	 * @throws SQLException si une erreur survient
	 */
	public Integer authentifier(String unEmail, String unMdp) throws SQLException {
		if (this.cxt == null || this.cxt.isClosed()) {
			throw new SQLException("Connexion invalide!");
		}
		PreparedStatement request = null;
		ResultSet resultat = null;
		try {
			// Creation de l'objet de requete
			request = this.cxt.prepareStatement("select id, password from utilisateur where email=?");
			request.setString(1, unEmail);

			// Envoie de la requete et recuperation du resultat
			resultat = request.executeQuery();

			// Parcours du resultat, toujours commencer par un .next
			while (resultat.next()) {
				var id = Integer.valueOf(resultat.getInt("id"));
				var noid = resultat.wasNull();
				var password = resultat.getString("password");
				if (noid) {
					return null;
				}
				return password == unMdp || password.equals(unMdp) ? id : null;
			}
			return null;
		} finally {
			this.closeAll(resultat, request);
		}
	}

	/**
	 * Methode qui recupere toutes les operations d'un compte.
	 *
	 * @param unCompteId un id de compte
	 * @return la liste de ses operations, une liste vide si aucune
	 * @throws SQLException si une erreur survient
	 */
	public List<Operation> selectOperation(Integer unCompteId) throws SQLException {
		return this.selectOperation(unCompteId, null, null, null);
	}

	/**
	 * Methode qui recupere les operations d'un compte en fonction des criteres
	 * indiques.
	 *
	 * @param unCompteId  un id de compte
	 * @param dateDeb     date debut
	 * @param dateFin     date fin
	 * @param creditDebit TRUE = credit, FALSE = debit, null = les deux
	 * @return la liste de ses operations, une liste vide si aucune
	 * @throws SQLException si une erreur survient
	 */
	public List<Operation> selectOperation(Integer unCompteId, LocalDate dateDeb, LocalDate dateFin,
			Boolean creditDebit) throws SQLException {
		List<Operation> listeOperation = new ArrayList<>();
		if (this.cxt == null || this.cxt.isClosed()) {
			throw new SQLException("Connexion invalide!");
		}
		PreparedStatement request = null;
		ResultSet resultat = null;
		try {
			var requete = new StringBuilder();
			requete.append("select * from operation where compte_id=?");
			if (dateDeb != null) {
				requete.append(" and date_op >= ?");
			}
			if (dateFin != null) {
				requete.append(" and date_op <= ?");
			}
			if (creditDebit != null) {
				if (creditDebit.booleanValue()) {
					requete.append(" and montant >= 0");
				} else {
					requete.append(" and montant <= 0");
				}
			}
			requete.append(" order by date_op desc");
			request = this.cxt.prepareStatement(requete.toString());
			var idParam = 1;
			request.setInt(idParam, unCompteId);
			idParam++;
			if (dateDeb != null) {
				request.setDate(idParam, java.sql.Date.valueOf(dateDeb));
				idParam++;
			}
			if (dateFin != null) {
				request.setDate(idParam, java.sql.Date.valueOf(dateFin));
				idParam++;
			}
			resultat = request.executeQuery();
			while (resultat.next()) {
				var id = resultat.getInt("id");
				var libelle = resultat.getString("libelle");
				var montant = resultat.getBigDecimal("montant");
				var date = resultat.getDate("date_op");
				var heure = resultat.getTime("heure_op");
				var compte_id = resultat.getInt("compte_id");
				listeOperation.add(new Operation(id, libelle, montant.doubleValue(), date.toLocalDate(),
						heure.toLocalTime(), compte_id));
			}
		} finally {
			this.closeAll(resultat, request);
		}
		return listeOperation;

	}

	/**
	 * Methode qui recupere un compte en fonction de son id.
	 *
	 * @param unCompteId un id de compte
	 * @return le compte, null si pas trouve
	 * @throws SQLException si une erreur survient
	 */
	public Compte selectUnCompte(int unCompteId) throws SQLException {
		Compte cpt = null;
		if (this.cxt == null || this.cxt.isClosed()) {
			throw new SQLException("Connexion invalide!");
		}
		PreparedStatement request = null;
		ResultSet resultat = null;
		try {
			request = this.cxt.prepareStatement("select * from compte where id=?");
			request.setInt(1, unCompteId);
			resultat = request.executeQuery();
			if (resultat.next()) {
				var id = resultat.getInt("id");
				// String libelle = resultat.getString("libelle");
				var solde = resultat.getBigDecimal("solde");
				var decouvert = resultat.getBigDecimal("decouvert");
				var taux = resultat.getBigDecimal("taux");

				if (decouvert == null && taux == null) {
					cpt = new Compte(id, solde.doubleValue());
				} else if (decouvert == null && taux != null) {
					cpt = new CompteRemunere(id, solde.doubleValue(), taux.doubleValue());
				} else if (decouvert != null && taux == null) {
					cpt = new CompteASeuil(id, solde.doubleValue(), decouvert.doubleValue());
				} else {
					cpt = new CompteASeuilRemunere(id, solde.doubleValue(), taux.doubleValue(),
							decouvert.doubleValue());
				}
				cpt.setLibelle(resultat.getString("libelle"));
			}

		} finally {
			this.closeAll(resultat, request);
		}
		return cpt;

	}

	/**
	 * Methode qui recupere tous les comptes d'un utilisateur.
	 *
	 * @param unUtilisateurId un id d'utilisateur
	 * @return la liste de ses comptes, une liste vide si aucun
	 * @throws SQLException si une erreur survient
	 */
	public List<Compte> selectCompte(int unUtilisateurId) throws SQLException {
		List<Compte> listeCompte = new ArrayList<>();
		if (this.cxt == null || this.cxt.isClosed()) {
			throw new SQLException("Connexion invalide!");
		}
		PreparedStatement request = null;
		ResultSet resultat = null;
		try {
			request = this.cxt.prepareStatement("select * from compte where utilisateur_id=?");
			request.setInt(1, unUtilisateurId);
			resultat = request.executeQuery();
			while (resultat.next()) {
				var id = resultat.getInt("id");
				// String libelle = resultat.getString("libelle");
				var solde = resultat.getBigDecimal("solde");
				var decouvert = resultat.getBigDecimal("decouvert");
				var taux = resultat.getBigDecimal("taux");
				Compte cpt = null;
				if (decouvert == null && taux == null) {
					cpt = new Compte(id, solde.doubleValue());
				} else if (decouvert == null && taux != null) {
					cpt = new CompteRemunere(id, solde.doubleValue(), taux.doubleValue());
				} else if (decouvert != null && taux == null) {
					cpt = new CompteASeuil(id, solde.doubleValue(), decouvert.doubleValue());
				} else {
					cpt = new CompteASeuilRemunere(id, solde.doubleValue(), taux.doubleValue(),
							decouvert.doubleValue());
				}
				cpt.setLibelle(resultat.getString("libelle"));
				listeCompte.add(cpt);
			}

		} finally {
			this.closeAll(resultat, request);
		}

		return listeCompte;
	}

	/**
	 * Methode qui recupere tous les utilisateurs avec leurs comptes.
	 *
	 * @return la liste des utilisateurs avec leurs comptes
	 * @throws SQLException si une erreur survient
	 */
	public List<Client> selectUtilisateur() throws SQLException {
		List<Client> listeClient = new ArrayList<>();
		if (this.cxt == null || this.cxt.isClosed()) {
			throw new SQLException("Connexion invalide!");
		}
		Statement request = null;
		ResultSet resultat = null;
		try {
			// Recuperation de tous les clients
			request = this.cxt.createStatement();
			resultat = request.executeQuery("select * from utilisateur");
			while (resultat.next()) {
				var id = resultat.getInt("id");
				var nom = resultat.getString("nom");
				var prenom = resultat.getString("prenom");
				var dateNaissance = resultat.getDate("date_de_naissance");
				var age = -1;
				if (dateNaissance != null) {
					// On calcul l'age
					var now = LocalDate.now();
					var naissance = dateNaissance.toLocalDate();
					var p = Period.between(naissance, now);
					age = p.getYears();
				}
				listeClient.add(new Client(id, nom, prenom, age));
			}

			for (Client client : listeClient) {
				var listeCpt = this.selectCompte(client.getNumero());
				for (Compte element : listeCpt) {
					client.ajouterCompte(element);
				}
			}
		} finally {
			this.closeAll(resultat, request);
		}
		return listeClient;
	}

	/**
	 * Effectue un virement entre deux comptes.
	 *
	 * @param cptSrc    le compte source
	 * @param cptDest   le compte destination
	 * @param unMontant le montant qui sera retire du compte source et ajoute au
	 *                  compte destination
	 * @throws SQLException si une erreur survient
	 */
	public void faireVirement(Integer cptSrc, Integer cptDest, Double unMontant) throws SQLException {
		if (this.cxt == null || this.cxt.isClosed()) {
			throw new SQLException("Connexion invalide!");
		}
		this.cxt.setAutoCommit(false);
		Statement request = null;
		try {
			request = this.cxt.createStatement();
			request.executeUpdate("update compte set solde=(solde-" + unMontant + ") where id=" + cptSrc);
			request.close();
			request = this.cxt.createStatement();
			request.executeUpdate("update compte set solde=(solde+" + unMontant + ") where id=" + cptDest);
			request.close();
			request = this.cxt.createStatement();

			request.executeUpdate(
					"insert into operation (libelle, montant, date_op, heure_op, compte_id) values ('Virement',"
							+ -unMontant + ",current_date, current_time," + cptSrc + ")");
			request.close();
			request = this.cxt.createStatement();
			request.executeUpdate(
					"insert into operation (libelle, montant, date_op, heure_op, compte_id) values ('Virement',"
							+ unMontant + ",current_date, current_time," + cptDest + ")");
			this.cxt.commit();
		} catch (SQLException sql) {
			this.cxt.rollback();
			// On la relance
			throw sql;
		} finally {
			this.cxt.setAutoCommit(true);
			this.closeAll(null, request);
		}
	}
}
