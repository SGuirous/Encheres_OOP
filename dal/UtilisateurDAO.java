package fr.eni.javaee.encheres.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateurs;


public interface UtilisateurDAO {

	public Utilisateurs selectbyPseudo(String pseudo, String email, String mot_de_passe) throws BusinessException;
	public void insert(Utilisateurs utilisateur) throws BusinessException, SQLServerException;
}
