package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateurs;





public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	private static final String SELECT_BY_IDENTIFIANT_AND_PASSWORD=""
			+ "SELECT *"
			+ " FROM UTILISATEURS"
			+ " WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ? ";
	private static final String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue,"
			+ "code_postal, ville, mot_de_passe) values(?,?,?,?,?,?,?,?,?)";		
	@Override
	public Utilisateurs selectbyPseudo(String pseudo, String email, String mot_de_passe) throws BusinessException {
		Utilisateurs utilisateur ;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_IDENTIFIANT_AND_PASSWORD);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, email);
			pstmt.setString(3, mot_de_passe);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateurs(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getInt("administrateur")
						);
				System.out.println("Sélection de l'utilisateur par identifiant et mot de passe  : " +utilisateur.toString());
				return utilisateur;
			}	else {
				throw new Exception("Pas d'utilisateur trouvé");
			}
			
		}	catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ECHEC_CONNEXION_UTILISATEUR);
			throw businessException;
		}
}
	@Override
	public void insert(Utilisateurs utilisateur) throws BusinessException, SQLServerException {
		if(utilisateur==null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				
					pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, utilisateur.getPseudo());
					pstmt.setString(2, utilisateur.getNom());
					pstmt.setString(3, utilisateur.getPrenom());
					pstmt.setString(4, utilisateur.getEmail());
					pstmt.setString(5, utilisateur.getTelephone());
					pstmt.setString(6, utilisateur.getRue());
					pstmt.setString(7, utilisateur.getCode_postal());
					pstmt.setString(8, utilisateur.getVille());
					pstmt.setString(9, utilisateur.getMot_de_passe());
					
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if(rs.next())
					{
						utilisateur.setNo_utilisateur(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				
				
				cnx.commit();
			}
			catch(Exception e)
			{
				
				cnx.rollback();
				throw e;
			}
		}catch(SQLServerException e)
		{
			
			throw e;
		}
		catch(Exception e)
		{
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}
	
}
