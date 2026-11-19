package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class BdFuncoes {

    // conexão com o banco

    public Connection conectar_ao_bd(String bdnome, String user, String pass) {

        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + bdnome,
                    user,
                    pass
            );
            if (con != null) {
                System.out.println("Conexão estabelecida com o banco de dados.");
            }
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
        return con;
    }


    // CADASTRAR HOTEL

    public void cadastrarHotel(Connection con, String nome_hotel, String CNPJ, String tell_hotel, String endereco_hotel) {
        String verificar = "SELECT cod_hotel FROM hotel WHERE CNPJ = ?";

        try (PreparedStatement pst = con.prepareStatement(verificar)) {
            pst.setString(1, CNPJ);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Já existe um hotel cadastrado com esse CNPJ.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar CNPJ: " + e.getMessage());
            return;
        }
        String sql = "INSERT INTO hotel(nome_hotel, CNPJ, tell_hotel, endereco_hotel) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nome_hotel);
            pst.setString(2, CNPJ);
            pst.setString(3, tell_hotel);
            pst.setString(4, endereco_hotel);

            pst.executeUpdate();

            System.out.println("Hotel cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar hotel: " + e.getMessage());
        }
    }


    // BUSCAR CÓDIGO DO HOTEL PELO NOME

    public int buscarCodHotel(Connection con, String nome_hotel) {

        String sql = "SELECT cod_hotel FROM hotel WHERE LOWER(nome_hotel) = LOWER(?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nome_hotel);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt("cod_hotel");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar hotel: " + e.getMessage());
        }
        return -1;
    }


    // HÓSPEDES


    // CADASTRAR HÓSPEDE

    public void cadastrarHospede(Connection con, String nome_hospede, String CPF_hospede, String tell_hospede, String email_hospede) {

        String sql = "INSERT INTO hospede(nome_hospede, CPF_hospede, " + "tell_hospede, email_hospede) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nome_hospede);
            pst.setString(2, CPF_hospede);
            pst.setString(3, tell_hospede);
            pst.setString(4, email_hospede);

            pst.executeUpdate();

            System.out.println("Hóspede cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar hóspede: " + e.getMessage());
        }
    }


    // ATUALIZAR HÓSPEDE

    public void atualizarHospede(Connection con, int cod_hospede, String nome_hospede, String CPF_hospede, String tell_hospede, String email_hospede) {
        String sql = "UPDATE hospede SET nome_hospede = ?, CPF_hospede = ?, " + "tell_hospede = ?, email_hospede = ? WHERE cod_hospede = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nome_hospede);
            pst.setString(2, CPF_hospede);
            pst.setString(3, tell_hospede);
            pst.setString(4, email_hospede);
            pst.setInt(5, cod_hospede);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Hóspede atualizado com sucesso!");
            } else {
                System.out.println("Hóspede não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar hóspede: " + e.getMessage());
        }
    }


    // EXCLUIR HÓSPEDE

    public void excluirHospede(Connection con, int cod_hospede) {
        String sql = "DELETE FROM hospede WHERE cod_hospede = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, cod_hospede);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Hóspede excluído com sucesso!");
            } else {
                System.out.println("Hóspede não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir hóspede: " + e.getMessage());
        }
    }


    // CONSULTAR HÓSPEDES

    public void consultarHospedes(Connection con) {
        String sql = "SELECT * FROM hospede";

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n===== HÓSPEDES CADASTRADOS =====");

            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("cod_hospede"));
                System.out.println("Nome: " + rs.getString("nome_hospede"));
                System.out.println("CPF: " + rs.getString("CPF_hospede"));
                System.out.println("Telefone: " + rs.getString("tell_hospede"));
                System.out.println("Email: " + rs.getString("email_hospede"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar hóspedes: " + e.getMessage());
        }
    }


    // FUNCIONÁRIOS


    // CADASTRAR FUNCIONÁRIO

    public void cadastrarFuncionario(Connection con, String nome_fun, String CPF_fun, String cargo_fun, int cod_hotel) {
        String sql = "INSERT INTO funcionario(nome_fun, CPF_fun, cargo_fun, cod_hotel) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nome_fun);
            pst.setString(2, CPF_fun);
            pst.setString(3, cargo_fun);
            pst.setInt(4, cod_hotel);

            pst.executeUpdate();

            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }


    // ATUALIZAR FUNCIONÁRIO

    public void atualizarFuncionario(Connection con, int cod_fun,
                                     String nome_fun, String CPF_fun,
                                     String cargo_fun) {

        String sql = "UPDATE funcionario SET nome_fun = ?, CPF_fun = ?, "
                + "cargo_fun = ? WHERE cod_fun = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nome_fun);
            pst.setString(2, CPF_fun);
            pst.setString(3, cargo_fun);
            pst.setInt(4, cod_fun);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }


    // EXCLUIR FUNCIONÁRIO

    public void excluirFuncionario(Connection con, int cod_fun) {
        String sql = "DELETE FROM funcionario WHERE cod_fun = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, cod_fun);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Funcionário excluído com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }


    // CONSULTAR FUNCIONÁRIOS DO HOTEL

    public void consultarFuncionarios(Connection con, int cod_hotel) {
        String sql = "SELECT * FROM funcionario WHERE cod_hotel = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, cod_hotel);

            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== FUNCIONÁRIOS =====");

            while (rs.next()) {

                System.out.println("Código: " + rs.getInt("cod_fun"));
                System.out.println("Nome: " + rs.getString("nome_fun"));
                System.out.println("CPF: " + rs.getString("CPF_fun"));
                System.out.println("Cargo: " + rs.getString("cargo_fun"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar funcionários: " + e.getMessage());
        }
    }


    // QUARTOS


    // CADASTRAR QUARTO

    public void cadastrarQuarto(Connection con, int num_quarto, int andar_quarto, String status_quarto, int cod_hotel) {
        String sql = "INSERT INTO quarto(num_quarto, andar_quarto, "
                + "status_quarto, cod_hotel) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, num_quarto);
            pst.setInt(2, andar_quarto);
            pst.setString(3, status_quarto);
            pst.setInt(4, cod_hotel);

            pst.executeUpdate();
            System.out.println("Quarto cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar quarto: " + e.getMessage());
        }
    }


    // ATUALIZAR QUARTO

    public void atualizarQuarto(Connection con, int cod_quarto, int num_quarto, int andar_quarto, String status_quarto) {
        String sql = "UPDATE quarto SET num_quarto = ?, andar_quarto = ?, "
                + "status_quarto = ? WHERE cod_quarto = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, num_quarto);
            pst.setInt(2, andar_quarto);
            pst.setString(3, status_quarto);
            pst.setInt(4, cod_quarto);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Quarto atualizado com sucesso!");
            } else {
                System.out.println("Quarto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar quarto: " + e.getMessage());
        }
    }


    // EXCLUIR QUARTO

    public void excluirQuarto(Connection con, int cod_quarto) {

        String sql = "DELETE FROM quarto WHERE cod_quarto = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, cod_quarto);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Quarto excluído com sucesso!");
            } else {
                System.out.println("Quarto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir quarto: " + e.getMessage());
        }
    }


    // CONSULTAR QUARTOS DO HOTEL

    public void consultarQuartos(Connection con, int cod_hotel) {

        String sql = "SELECT * FROM quarto WHERE cod_hotel = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, cod_hotel);

            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== QUARTOS =====");

            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("cod_quarto"));
                System.out.println("Número: " + rs.getInt("num_quarto"));
                System.out.println("Andar: " + rs.getInt("andar_quarto"));
                System.out.println("Status: " + rs.getString("status_quarto"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar quartos: " + e.getMessage());
        }
    }


    // RESERVAS


    // CADASTRAR RESERVA

    public void cadastrarReserva(Connection con, String dt_entrada, String dt_saida, int qt_hospede, int cod_quarto, int cod_hospede, int cod_fun) {
        String sql = "INSERT INTO reserva(dt_entrada, dt_saida, qt_hospede, "
                + "cod_quarto, cod_hospede, cod_fun) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, Date.valueOf(dt_entrada));
            pst.setDate(2, Date.valueOf(dt_saida));
            pst.setInt(3, qt_hospede);
            pst.setInt(4, cod_quarto);
            pst.setInt(5, cod_hospede);
            pst.setInt(6, cod_fun);

            pst.executeUpdate();

            System.out.println("Reserva cadastrada com sucesso!");
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar reserva: " + e.getMessage());
        }
    }


    // ATUALIZAR RESERVA

    public void atualizarReserva(Connection con, int cod_rsv, String dt_entrada, String dt_saida, int qt_hospede, int cod_quarto, int cod_hospede, int cod_fun) {
        String sql = "UPDATE reserva SET dt_entrada = ?, dt_saida = ?, "
                + "qt_hospede = ?, cod_quarto = ?, cod_hospede = ?, "
                + "cod_fun = ? WHERE cod_rsv = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, Date.valueOf(dt_entrada));
            pst.setDate(2, Date.valueOf(dt_saida));
            pst.setInt(3, qt_hospede);
            pst.setInt(4, cod_quarto);
            pst.setInt(5, cod_hospede);
            pst.setInt(6, cod_fun);
            pst.setInt(7, cod_rsv);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Reserva atualizada com sucesso!");
            } else {
                System.out.println("Reserva não encontrada.");
            }
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Erro ao atualizar reserva: " + e.getMessage());
        }
    }

    // EXCLUIR RESERVA

    public void excluirReserva(Connection con, int cod_rsv) {
        String sql = "DELETE FROM reserva WHERE cod_rsv = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, cod_rsv);

            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                System.out.println("Reserva excluída com sucesso!");
            } else {
                System.out.println("Reserva não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir reserva: " + e.getMessage());
        }
    }

    // CONSULTAR RESERVAS DO HOTEL

    public void consultarReservas(Connection con, int cod_hotel) {

        String sql = "SELECT r.* FROM reserva r "
                + "INNER JOIN quarto q ON r.cod_quarto = q.cod_quarto "
                + "WHERE q.cod_hotel = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, cod_hotel);

            ResultSet rs = pst.executeQuery();

            System.out.println("\n===== RESERVAS =====");

            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("cod_rsv"));
                System.out.println("Entrada: " + rs.getDate("dt_entrada"));
                System.out.println("Saída: " + rs.getDate("dt_saida"));
                System.out.println("Quantidade de hóspedes: " + rs.getInt("qt_hospede"));
                System.out.println("Código do quarto: " + rs.getInt("cod_quarto"));
                System.out.println("Código do hóspede: " + rs.getInt("cod_hospede"));
                System.out.println("Código do funcionário: " + rs.getInt("cod_fun"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consultar reservas: " + e.getMessage());
        }
    }
}