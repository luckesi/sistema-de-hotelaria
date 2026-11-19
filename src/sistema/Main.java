package sistema;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BdFuncoes bd = new BdFuncoes();

        Connection con = bd.conectar_ao_bd("sistema-hotelaria", "postgres", "1234");

        if (con == null) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            sc.close();
            return;
        }

        int x = 0;

        while (x != 3) {
            System.out.println("\n===== SISTEMA DE HOTELARIA =====");
            System.out.println("1 - Cadastrar hotel");
            System.out.println("2 - Entrar em hotel existente");
            System.out.println("3 - Sair");
            System.out.print("Escolha: ");

            x = sc.nextInt();
            sc.nextLine();
            switch (x) {
                case 1:
                    System.out.print("Nome do hotel: ");
                    String nome_hotel = sc.nextLine();

                    System.out.print("CNPJ: ");
                    String CNPJ = sc.nextLine();

                    System.out.print("Telefone: ");
                    String tell_hotel = sc.nextLine();

                    System.out.print("Endereço: ");
                    String endereco_hotel = sc.nextLine();

                    bd.cadastrarHotel(con, nome_hotel, CNPJ, tell_hotel, endereco_hotel);
                    break;
                case 2:
                    System.out.print("Digite o nome do hotel: ");
                    String nomeHotel = sc.nextLine();
                    int cod_hotel = bd.buscarCodHotel(con, nomeHotel);
                    if (cod_hotel != -1) {
                        System.out.println("\nHotel encontrado!");
                        System.out.println("Código do hotel: " + cod_hotel);
                        menuHotel(sc, bd, con, cod_hotel);
                    } else {
                        System.out.println("Hotel não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        try {
            con.close();
            System.out.println("Conexão encerrada.");
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão.");
        }
        sc.close();
    }


    // MENU DO HOTEL

    public static void menuHotel(Scanner sc, BdFuncoes bd, Connection con, int cod_hotel) {

        int x = 0;
        while (x != 5) {
            System.out.println("\n===== MENU DO HOTEL =====");
            System.out.println("1 - Hóspedes");
            System.out.println("2 - Funcionários");
            System.out.println("3 - Quartos");
            System.out.println("4 - Reservas");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            x = sc.nextInt();
            sc.nextLine();
            switch (x) {
                case 1:
                    menuHospedes(sc, bd, con);
                    break;

                case 2:
                    menuFuncionarios(sc, bd, con, cod_hotel);
                    break;

                case 3:
                    menuQuartos(sc, bd, con, cod_hotel);
                    break;

                case 4:
                    menuReservas(sc, bd, con, cod_hotel);
                    break;

                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }


    // MENU DE HÓSPEDES

    public static void menuHospedes(Scanner sc, BdFuncoes bd, Connection con) {

        int z = 0;
        while (z != 5) {
            System.out.println("----MENU DE HÓSPEDES----");
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Atualizar hóspede");
            System.out.println("3 - Excluir hóspede");
            System.out.println("4 - Consultar hóspedes");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            z = sc.nextInt();
            sc.nextLine();

            switch (z) {
                case 1:
                    System.out.print("Nome: ");
                    String nome_hospede = sc.nextLine();

                    System.out.print("CPF: ");
                    String CPF_hospede = sc.nextLine();

                    System.out.print("Telefone: ");
                    String tell_hospede = sc.nextLine();

                    System.out.print("Email: ");
                    String email_hospede = sc.nextLine();

                    bd.cadastrarHospede(con, nome_hospede, CPF_hospede, tell_hospede, email_hospede);

                    break;
                case 2:
                    System.out.print("Código do hóspede: ");
                    int cod_hospede = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo nome: ");
                    nome_hospede = sc.nextLine();

                    System.out.print("Novo CPF: ");
                    CPF_hospede = sc.nextLine();

                    System.out.print("Novo telefone: ");
                    tell_hospede = sc.nextLine();

                    System.out.print("Novo email: ");
                    email_hospede = sc.nextLine();

                    bd.atualizarHospede(con, cod_hospede, nome_hospede, CPF_hospede, tell_hospede, email_hospede);

                    break;
                case 3:
                    System.out.print("Código do hóspede: ");
                    cod_hospede = sc.nextInt();
                    sc.nextLine();

                    bd.excluirHospede(con, cod_hospede);

                    break;
                case 4:
                    bd.consultarHospedes(con);
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // MENU DE FUNCIONÁRIOS

    public static void menuFuncionarios(Scanner sc, BdFuncoes bd, Connection con, int cod_hotel) {

        int z = 0;
        while (z != 5) {
            System.out.println("----MENU DE FUNCIONÁRIOS----");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Atualizar funcionário");
            System.out.println("3 - Excluir funcionário");
            System.out.println("4 - Consultar funcionários");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            z = sc.nextInt();
            sc.nextLine();
            switch (z) {
                case 1:
                    System.out.print("Nome: ");
                    String nome_fun = sc.nextLine();

                    System.out.print("CPF: ");
                    String CPF_fun = sc.nextLine();

                    System.out.print("Cargo: ");
                    String cargo_fun = sc.nextLine();

                    bd.cadastrarFuncionario(con, nome_fun, CPF_fun, cargo_fun, cod_hotel);
                    break;
                case 2:
                    System.out.print("Código do funcionário: ");
                    int cod_fun = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo nome: ");
                    nome_fun = sc.nextLine();

                    System.out.print("Novo CPF: ");
                    CPF_fun = sc.nextLine();

                    System.out.print("Novo cargo: ");
                    cargo_fun = sc.nextLine();

                    bd.atualizarFuncionario(con, cod_fun, nome_fun, CPF_fun, cargo_fun);

                    break;
                case 3:
                    System.out.print("Código do funcionário: ");
                    cod_fun = sc.nextInt();
                    sc.nextLine();

                    bd.excluirFuncionario(con, cod_fun);
                    break;
                case 4:
                    bd.consultarFuncionarios(con, cod_hotel);
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // MENU DE QUARTOS

    public static void menuQuartos(Scanner sc, BdFuncoes bd, Connection con, int cod_hotel) {

        int z = 0;
        while (z != 5) {
            System.out.println("----MENU DE QUARTOS----");
            System.out.println("1 - Cadastrar quarto");
            System.out.println("2 - Atualizar quarto");
            System.out.println("3 - Excluir quarto");
            System.out.println("4 - Consultar quartos");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            z = sc.nextInt();
            sc.nextLine();

            switch (z) {
                case 1:
                    System.out.print("Número do quarto: ");
                    int num_quarto = sc.nextInt();

                    System.out.print("Andar: ");
                    int andar_quarto = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Status: ");
                    String status_quarto = sc.nextLine();

                    bd.cadastrarQuarto(con, num_quarto, andar_quarto, status_quarto, cod_hotel);
                    break;
                case 2:
                    System.out.print("Código do quarto: ");
                    int cod_quarto = sc.nextInt();

                    System.out.print("Novo número: ");
                    num_quarto = sc.nextInt();

                    System.out.print("Novo andar: ");
                    andar_quarto = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Novo status: ");
                    status_quarto = sc.nextLine();

                    bd.atualizarQuarto(con, cod_quarto, num_quarto, andar_quarto, status_quarto);
                    break;
                case 3:
                    System.out.print("Código do quarto: ");
                    cod_quarto = sc.nextInt();
                    sc.nextLine();

                    bd.excluirQuarto(con, cod_quarto);
                    break;
                case 4:
                    bd.consultarQuartos(con, cod_hotel);
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // MENU DE RESERVAS

    public static void menuReservas(Scanner sc, BdFuncoes bd, Connection con, int cod_hotel) {
        int z = 0;
        while (z != 5) {
            System.out.println("----MENU DE RESERVAS----");
            System.out.println("1 - Cadastrar reserva");
            System.out.println("2 - Atualizar reserva");
            System.out.println("3 - Excluir reserva");
            System.out.println("4 - Consultar reservas");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            z = sc.nextInt();
            sc.nextLine();

            switch (z) {

                case 1:
                    System.out.print("Data de entrada (AAAA-MM-DD): ");
                    String dt_entrada = sc.nextLine();

                    System.out.print("Data de saída (AAAA-MM-DD): ");
                    String dt_saida = sc.nextLine();

                    System.out.print("Quantidade de hóspedes: ");
                    int qt_hospede = sc.nextInt();

                    System.out.print("Código do quarto: ");
                    int cod_quarto = sc.nextInt();

                    System.out.print("Código do hóspede: ");
                    int cod_hospede = sc.nextInt();

                    System.out.print("Código do funcionário: ");
                    int cod_fun = sc.nextInt();
                    sc.nextLine();

                    bd.cadastrarReserva(con, dt_entrada, dt_saida, qt_hospede, cod_quarto, cod_hospede, cod_fun);
                    break;

                case 2:
                    System.out.print("Código da reserva: ");
                    int cod_rsv = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nova data de entrada (AAAA-MM-DD): ");
                    dt_entrada = sc.nextLine();

                    System.out.print("Nova data de saída (AAAA-MM-DD): ");
                    dt_saida = sc.nextLine();

                    System.out.print("Quantidade de hóspedes: ");
                    qt_hospede = sc.nextInt();

                    System.out.print("Código do quarto: ");
                    cod_quarto = sc.nextInt();

                    System.out.print("Código do hóspede: ");
                    cod_hospede = sc.nextInt();

                    System.out.print("Código do funcionário: ");
                    cod_fun = sc.nextInt();
                    sc.nextLine();

                    bd.atualizarReserva(con, cod_rsv, dt_entrada, dt_saida, qt_hospede, cod_quarto, cod_hospede, cod_fun);
                    break;

                case 3:
                    System.out.print("Código da reserva: ");
                    cod_rsv = sc.nextInt();
                    sc.nextLine();

                    bd.excluirReserva(con, cod_rsv);
                    break;
                case 4:
                    bd.consultarReservas(con, cod_hotel);
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}