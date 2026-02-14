package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class Conexao {

    public Connection conectar() {
        try {
            Properties props = new Properties();

            InputStream input = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config/db.properties");

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco", e);
        }
    }
}
