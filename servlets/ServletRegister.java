package fr.eni.javaee.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.UtilisateurManager;
import fr.eni.javaee.encheres.bo.Utilisateurs;


/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Integer> listeCodesErreur=new ArrayList<>();
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		String pwd, repwd;
		pwd=request.getParameter("password");
		repwd=request.getParameter("repassword");
		try {
		
			// Vérification de l'existence de la session 
			if (session.getAttribute("utilisateurR") == null) {
				session.setAttribute("utilisateurR", new Utilisateurs());
			}
			
			// RECUPERATION de l'utilisateur
			Utilisateurs utilisateurR = (Utilisateurs) session.getAttribute("utilisateurR");
			
			// Partie 1
			if (method.equals("partie_1")) {
				
				if(pwd.equals(repwd)) {
					utilisateurR.setPseudo(request.getParameter("pseudo"));
					utilisateurR.setEmail(request.getParameter("email"));
					utilisateurR.setMot_de_passe(request.getParameter("password"));
					
					// Sauvegarde de l'utilisateur dans la session
					session.setAttribute("utilisateurR", utilisateurR);
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
					rd.forward(request, response);
					return;
					
				} else {
					request.setAttribute("listeCodesErreur",listeCodesErreur);
					listeCodesErreur.add(CodesResultatServlets.FORMAT_PASSWORD_ERREUR);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
					rd.forward(request, response);
					return;
				}
			}
			
			// Partie 2
			if (method.equals("partie_2")) {
				utilisateurR.setNom(request.getParameter("nom"));
				utilisateurR.setPrenom(request.getParameter("prenom"));
				utilisateurR.setTelephone(request.getParameter("telephone"));
				utilisateurR.setRue(request.getParameter("rue"));
				utilisateurR.setCode_postal(request.getParameter("zip"));
				utilisateurR.setVille(request.getParameter("ville"));	
				utilisateurManager.insererUtilisateur(utilisateurR);
				session.setAttribute("identifiant", utilisateurR.getPseudo());
				session.setAttribute("connected", true);
				response.sendRedirect(request.getContextPath() + "/page1");
				return;
			}
			
			
		} catch (BusinessException e) {
			
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			
			
	}

}
}
