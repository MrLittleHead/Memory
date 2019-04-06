package memoryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * étape 1 : la connexion à la base de données
 *
 */
public class Connection {

	private static java.sql.Connection connect = null;

	private static final String ID = "maxou";
	private static final String MDP = "sio";
	private static final String NOM_SERVEUR = "BTSWIN7-99\\SQLEXPRESS";
	private static final String NOM_BD = "Memory";



	/**
	 * Patron de conception Singleton
	 * @return l'instance unique de connexion
	 */
	public static java.sql.Connection getInstance() {
		if (connect==null) {
			// Si la connexion n'a pas encore été faite, on la fait.
			try { 

				SQLServerDataSource ds = new SQLServerDataSource();
				ds.setUser(ID);
				ds.setPassword(MDP);
				ds.setServerName(NOM_SERVEUR);
				ds.setDatabaseName(NOM_BD);
				connect = ds.getConnection();
				System.out.println("connecté");
			}
			catch (SQLException e){
				System.out.println("Echec de la tentative de connexion : " + e.getMessage() + e.getStackTrace()) ;
			}
		}
		return connect;
	}

	/**
	 * Méthode statique d'exécution d'une requête de récupération de données
	 * @param requete
	 * @return
	 */
	public static ResultSet executeQuery(String requete){
		Statement st = null ;
		ResultSet rs = null;
		try{
			st = getInstance().createStatement() ;
			rs = st.executeQuery(requete) ;
		}catch(SQLException e){
			System.out.println("Echec de la tentative d'exécution de requete : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return rs;
	}

	/**
	 * Une méthode statique qui permet de faire une mise à jour d'une table (INSERT / UPDATE / DELETE)
	 * Mais cette méthode n'est pas utilisée puisqu'on passe par des prepared statement
	 * dans les classes DAO et on fait des execute update directement sur ces preparedStatement.
	 * @param requete
	 * @return
	 */
	public static boolean executeUpdate(String requete){
		boolean succes = true;
		//System.out.println(requete);
		Statement st = null ;
		try{
			st = getInstance().createStatement() ;
			st.executeUpdate(requete) ;
		}catch(SQLException e){
			succes = false;
			System.out.println("Echec de la tentative d'exécution de Mise à Jour : " +requete + " ["+ e.getMessage()+"]") ;
		}
		return succes;
	}

	/**
	 * Fermeture de la connexion au SGBD SQL ServerExpress
	 */
	public static void close() throws SQLException
	{
		getInstance();
		Connection.close();
		System.out.println("deconnexion ok");
	}


}
