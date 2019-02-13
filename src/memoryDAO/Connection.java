package memoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Connection {

	/**
	 * étape 1 : la connexion à la base de données
	 * @author Alain
	 */


		private static Connection connect = null;

		private static final String ID = "Maxou";
		private static final String MDP = "sio";
		private static final String NOM_SERVEUR = "localhost\\SQLEXPRESS";
		private static final String NOM_BD = "Memory";

		private static final int LARGEUR_COLONNE_TEXTE = 10;
		private static final int LARGEUR_COLONNE_ENTIER = 6;
		private static final int LARGEUR_COLONNE_DATE = 11;

		/**
	 * Patron de conception Singleton
	 * @return l'instance unique de connexion
	 */
	public static Connection getInstance() {
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
	public static void fermer()
	{
		try
		{
			getInstance().close();
			System.out.println("deconnexion ok");
		}
		catch (SQLException e)
		{
			connect=null;
			System.out.println("echec de la fermeture");
		}
	}

	/**
	 * Cette méthode correspond à la méthode create de la classe AvionDAO
	 * On peut la tester dans le main avec
	 * Avion avion=AvionDAO.getInstance().read(5);		
	 * AvionDAO.getInstance().create(avion);
	 * System.out.println(avion);
	 * --> Notez que AvionDAO est défini comme un singleton, unique instance pour tout le programme 
	 * @param av
	 */
	//		PUBLIC STATIC EXEMPLECREATEPREPAREDSTATEMENT(AVION AV) {
	//			BOOLEAN SUCCES=TRUE;
	//			// CONSTANTES QUI PEUVENT ÊTRE DÉCLARÉES DANS VOS CLASSES DAO
	//			STRING TABLE = "AVION";
	//			STRING CLE_PRIMAIRE = "NUMAV";
	//			TRY {
	//				
	//				STRING REQUETEDELETE = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
	//				STRING REQUETEUPDATE = "UPDATE "+TABLE+" SET NOMAV = ?, LOC = ?, CAPACITE = ? WHERE "+CLE_PRIMAIRE+" = ?";
	//				STRING REQUETE = "INSERT INTO "+TABLE+" (NOMAV, LOC, CAPACITE) VALUES (?, ?, ?)";
	//				PREPAREDSTATEMENT PST = CONNEXION.GETINSTANCE().PREPARESTATEMENT(REQUETE, STATEMENT.RETURN_GENERATED_KEYS);
	//				// ON POSE UN STRING EN PARAMÈTRE 1 -1ER '?'- ET CE STRING EST LE NOM DE L'AVION
	//				PST.SETSTRING(1, AV.GETNOM());
	//				PST.SETSTRING(2, AV.GETLOC());
	//				PST.SETINT(3, AV.GETCAPACITE());
	//				// ON EXÉCUTE LA MISE À JOUR
	//				PST.EXECUTEUPDATE();
	//
	//				//RÉCUPÉRER LA CLÉ QUI A ÉTÉ GÉNÉRÉE ET LA POUSSER DANS L'OBJET INITIAL
	//				RESULTSET RS = PST.GETGENERATEDKEYS();
	//				IF (RS.NEXT()) {
	//					AV.SETNUMERO(RS.GETINT(1));
	//				}
	//
	//			} CATCH (SQLEXCEPTION E) {
	//				SUCCES=FALSE;
	//				E.PRINTSTACKTRACE();
	//			}
	//
	//			RETURN SUCCES;
	//		}


	/**
	 * Requête qui permet de voir le contenu d'une table
	 * Attention à ne pas perdre la première ligne en testant la table vide
	 * @param table
	 */
	public static void afficheSelectEtoile(String table, String clauseWhere){
		try{
			// TODO Faire la requête SELECT FROM WHERE
			// l'exécuter pour obtenir un ResultSet
			// récupérer un ResultSetMetaData les méta-données getMetaData()
			// parcourir le resultSet
		}
		catch(SQLException e){
			System.out.println("Echec de la tentative d'interrogation Select * : " + e.getMessage()) ;
		}
	}



	/**
	 * TODO Requête qui permet de récupérer le plus grand id de la table : utiliser MAX
	 * @param cle
	 * @param table
	 * @return
	 */
	public static int getMaxId(String cle, String table) {
		int id= -1;
		try {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
