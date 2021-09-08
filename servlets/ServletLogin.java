package fr.eni.javaee.encheres.servlets;

import java.io.IOException;

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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Pseudo : "+request.getParameter("identifiant"));
		
		System.out.println("Mot de passe : "+request.getParameter("mot_de_passe"));
		request.setCharacterEncoding("UTF-8");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		try {
			Utilisateurs utilisateur = utilisateurManager.selectionnerUtilisateurParIdentifiantEtPassword(
					request.getParameter("identifiant"),
					request.getParameter("identifiant"),
					request.getParameter("mot_de_passe")
			);
			HttpSession session = request.getSession();
			session.setAttribute("identifiant", utilisateur.getPseudo());
			
			session.setAttribute("connected", true);
			
			
			request.getRequestDispatcher("/WEB-INF/jsp/page1.jsp").include(request, response);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
			rd.forward(request, response);
		}
	}

}
