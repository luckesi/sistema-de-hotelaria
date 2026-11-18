package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void cadastrarHotel(Connection con, String nome_hotel, String CNPJ, String tell_hotel, String endereco_hotel){
        PreparedStatement statement;
        String query = "INSERT INTO hotel(nome_hotel, CNPJ, tell_hotel, endereco_hotel) VALUES (?, ?, ?, ?)";

        try {
            statement = con.prepareStatement(query);

            statement.setString(1, nome_hotel);
            statement.setString(2, CNPJ);
            statement.setString(3, tell_hotel);
            statement.setString(4, endereco_hotel);

            statement.executeUpdate();

            System.out.println("Hotel " + nome_hotel + " cadastrado.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean consultarHotel(Connection con, String nome_hotel) {
        String sql = "SELECT * FROM hotel WHERE nome_hotel = ?";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome_hotel);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar hotel: " + e.getMessage());
            return false;
        }
    }
}
