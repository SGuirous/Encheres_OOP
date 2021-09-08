package fr.eni.javaee.encheres.bll;

import java.util.regex.Pattern;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateurs;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.UtilisateurDAO;






public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	public Utilisateurs selectionnerUtilisateurParIdentifiantEtPassword(String pseudo, String email, String mot_de_passe) throws BusinessException
	{return this.utilisateurDAO.selectbyPseudo(pseudo, email, mot_de_passe);}
	
	public Utilisateurs insererUtilisateur(Utilisateurs utilisateur) throws BusinessException{
		boolean matchok=true;
		BusinessException businessException = new BusinessException();
		
		
		this.validerEmail(utilisateur.getEmail(), businessException);
		
		
		matchok = validerPseudo(utilisateur.getPseudo());
		//si le test retourne faux alors on jette une exception
		if(!matchok) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ALPHA_ERREUR);
		throw businessException;}
		
		
	
		if(!businessException.hasErreurs())
		{
			
	
		try {	
			this.utilisateurDAO.insert(utilisateur);
			}
		catch(SQLServerException exception) {
			System.out.println(exception.getMessage());
			if (exception.getMessage().contains("UC_EMAIL")) {
					businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_ERREUR);
			
			}
		
			else if ((exception.getMessage().contains("UC_PSEUDO"))) 
			{
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
			} throw businessException;
		}
		}
		return utilisateur;
	
		
		
	}
	/**
	 * This function will test the regex against the pseudo and return 
	 * true if there is a match or false if there is no match.
	 * The pseudo must have between 1 and 30 alphanumerical characters.
	 * @param pseudo
	 * @return
	 */
	public boolean  validerPseudo(String pseudo) {
		boolean okmatch=false;
		
		okmatch=Pattern.compile("^[a-zA-z0-9]{1,30}$").matcher(pseudo).matches();
		
		
		return okmatch;
	}
	
		public void validerEmail(String email, BusinessException businessException) {
		if(email==null){
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_ERREUR);
			
		}
			
		}
}
