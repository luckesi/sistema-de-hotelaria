package sistema;

import java.sql.Connection;
import java.sql.DriverManager;

public class BdFuncoes {
    public Connection conectar_ao_bd(String bdnome, String user, String pass){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdnome, user, pass);
            if(con!=null){
                System.out.println("Conexão estabelecida com o banco de dados.");
            }else{
                System.out.println("Conexão com o banco de dados falhou.");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}
