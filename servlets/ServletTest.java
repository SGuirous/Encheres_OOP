package fr.eni.javaee.encheres.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import fr.eni.javaee.encheres.bll.UtilisateurManager;
import fr.eni.javaee.encheres.bo.Utilisateurs;
import fr.eni.javaee.encheres.dal.DAOFactory;




/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/test")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateurs utilisateur;
		
		try {
			
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			utilisateurManager.insererUtilisateur(new Utilisateurs("dddd","ee","ffff","ijklmno@jmail.com","0910111213","rue du hetre","76460","Dieppe","1234",0,1));
			utilisateur = DAOFactory.getUtilisateurDAO().selectbyPseudo("dddd", "ijklmno@jmail.com", "1234");
			System.out.println("Sélection de l'utilisateur par identifiant et mot de passe  : " + utilisateur.toString());
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
