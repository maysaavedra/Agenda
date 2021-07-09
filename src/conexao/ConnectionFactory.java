package conexao;

import java.sql.*;

public class ConnectionFactory {
	private static final java.io.File DATABASE = new java.io.File(
			System.getProperty("user.home")
                        + System.getProperty("file.separator")
			+ "AULA_POO_BD"
			+ System.getProperty("file.separator")
			+ ".contatos"
			+ System.getProperty("file.separator")
			+ "contatos.db");

	//Cria conexoes com o banco
	public static Connection getConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		if (!DATABASE.exists()) {
			createNewDatabase();
		}
		Connection conn =
				DriverManager.getConnection("jdbc:sqlite:" + DATABASE.getPath());
		return conn;
	}

	//a) - Rodo na inicializacao do programa
	//Se nao existir o arquivo de banco de dados, o programa roda o metodo para criar um arquivo de banco de dados
	public static void checkDatabase() throws Exception {
		if (!DATABASE.exists()) {
			createNewDatabase();
		}
	}

	/*  b) - Cria um novo banco de dados
	     Voce deve rodar aqui todos os comandos necessarios para fazer a configuracao inicial do banco -
	     criacao de tabelas, usuarios (se o banco comportar esse recurso), insercao de registros iniciais, etc.
	 */
	public static void createNewDatabase() throws Exception {
		try {
			DATABASE.getParentFile().mkdirs();    //Cria os diretorios pai do arquivo (caso nao existam)
			DATABASE.createNewFile();    //Cria o arquivo do banco
			if (!DATABASE.exists()) {    //Caso o arquivo ainda nao exista, apos os comandos acima, dispara excecao
				throw new Exception("Erro ao gravar o arquivo de banco de dados.");
			}
			Connection conn = getConnection();
			Statement s = conn.createStatement();
			//Execucao dos comandos sql para configuracao inicial do banco
			s.execute("CREATE TABLE IF NOT EXISTS CONTATOS ("
					+ "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " 
					+ "nome TEXT, "
					+ "email TEXT, "
					+ "endereco TEXT, "
					+ "dataNascimento TEXT"
					+ ")");
		} catch (Exception ex) {
			throw new Exception("Erro na criacao do banco de dados\n" + ex.getMessage());
		}
	}
}
