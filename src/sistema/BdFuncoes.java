package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
    public void cadastrarHotel(Connection con, String nomeTabela, String nome_hotel, String CNPJ, String tell_hotel, String endereco_hotel){
        Statement statement;
        try{
            String query=String.format("insert into %s(nome_hotel,CNPJ,tell_hotel,endereco_hotel) values('%s','%s');", nomeTabela,nome_hotel,CNPJ,tell_hotel,endereco_hotel);
            statement=con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Hotel "+ nome_hotel +"cadastrado.");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
