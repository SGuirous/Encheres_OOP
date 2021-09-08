package fr.eni.javaee.encheres.dal;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
}
