package fr.eni.javaee.encheres.bo;

/**
 * Creation of the BO users
 *  * @author Stéphane Guirous
 *
 */

public class Utilisateurs {
	private int 		no_utilisateur;
	private String		pseudo;           
	private String		nom;
	private String		prenom;
	private String		email; 
	private String		telephone;
	private	String		rue;
	private String		code_postal;
	private String		ville;
	private	String		mot_de_passe;
	private	int			credit;
	private	int			administrateur;   
	
	/**This is the constructor with all parameters
	 * 
	 * @param no_utilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param code_postal
	 * @param ville
	 * @param mot_de_passe
	 * @param credit
	 * @param administrateur
	 */
	
	public Utilisateurs(int no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mot_de_passe, int credit, int administrateur) {
		
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}
/**
 * This is the constructor with all parameters but no_utilisateur
 * @param pseudo
 * @param nom
 * @param prenom
 * @param email
 * @param telephone
 * @param rue
 * @param code_postal
 * @param ville
 * @param mot_de_passe
 * @param credit
 * @param administrateur
 */
	public Utilisateurs(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mot_de_passe, int credit, int administrateur) {
		
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	/**
	 * this is the constructor with no parameters
	 */
	public Utilisateurs() {
		
	}
	
	/**
	 * This is the constructor with all parameters but no_utilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param code_postal
	 * @param ville
	 * @param mot_de_passe
	/*
	 */
	/*
		public Utilisateurs(String pseudo, String nom, String prenom, String email, String telephone, String rue,
				String code_postal, String ville, String mot_de_passe) {
			
			this.pseudo = pseudo;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.telephone = telephone;
			this.rue = rue;
			this.code_postal = code_postal;
			this.ville = ville;
			this.mot_de_passe = mot_de_passe;
			
		}
*/
	/**
	 * no_utilsateur has no setter since this is a primary key with auto-increment
	 * in the SQL-database
	 * @return
	 */
	public int getno_utilisateur() {
		return no_utilisateur;
	}
	/**
	 * getter for the attribut pseudo
	 * @return
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**getter for the attribut nom
	 * 
	 * @return
	 */
	
	public String getNom() {
		return nom;
	}
	/**getter for the attribut prenom
	 * 
	 * @return
	 */
	public String getPrenom() {
		return prenom;
	}
	/**getter for the attribut email
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getRue() {
		return rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public String getVille() {
		return ville;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public int getCredit() {
		return credit;
	}

	public int getAdministrateur() {
		return administrateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	public void setAdministrateur(int administrateur) {
		// TODO Auto-generated method stub
		this.administrateur = administrateur;
	}  
	public String toString() {
		return "Utilisateur [UtilisateurID=" + no_utilisateur + ", pseudo= " + pseudo + ", nom=" + nom + ", prenom= " + prenom + ", email= " + email+"]";
	}
	
}



