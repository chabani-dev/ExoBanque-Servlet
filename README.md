Servlet
Présentation

La base se compose de trois tables, un utilisateur peut posséder des comptes.

Un compte n’appartient qu’a un seul utilisateur.

Les comptes peuvent générer des opérations. Une opération n’est liée qu’a un seul compte.

les opérations sur le comptes :
la classe Client
la classe Compte
la classe Compte a seuil
 * Il n'est pas possible de descendre en dessous du seuil.

la classe Compte à seuil rémunéré. 

 * On a choisi ici de prendre comme parent le CompteRemunere. <br>
 * Ce choix est basé sur le fait que c'est la classe qui a le plus de code. <br>
 * Ce qui nous fait le moins de choses a recopier.
 * 
la classe Compte rémunéré

/**
 * Interface qui représente un compte à seuil.
 */

/**
 * Interface qui représente un compte rémunéré.
 */

/**
 * Classe représentant une opération.
 */


