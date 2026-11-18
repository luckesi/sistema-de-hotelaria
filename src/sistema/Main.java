package sistema;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        BdFuncoes bd=new BdFuncoes ();
        Connection con = bd.conectar_ao_bd("sistema-hotelaria", "postgres","1234");
        int i = 0;
        while (i<3){
            System.out.println("-------Bem vindo!-------");
            System.out.println("-------Sistema de hotelaria-------");

            System.out.println("1. Cadastrar Hotel");
            System.out.println("2. Entrar em hotel existente");
            System.out.println("3. Sair do sistema");

            i = sc.nextInt();
            sc.nextLine();
            switch (i){
                case 1: {
                    System.out.println("Insira o nome do hotel:");
                    String nome_hotel = sc.nextLine();

                    System.out.println("Insira o CNPJ do hotel:");
                    String CNPJ = sc.nextLine();

                    System.out.println("Insira o telefone do hotel");
                    String tell_hotel = sc.nextLine();

                    System.out.println("Insira o endereço do hotel");
                    String endereco_hotel = sc.nextLine();

                    bd.cadastrarHotel(con, nome_hotel, CNPJ, tell_hotel, endereco_hotel);
                    break;
                }
                case 2: {
                    System.out.println("Digite o nome do hotel:");
                    String nome_hotel = sc.nextLine();

                    if (bd.consultarHotel(con, nome_hotel)) {
                        System.out.println("Hotel encontrado!");

                    } else {
                        System.out.println("Hotel não encontrado!");
                    }

                    break;
                }
            }
        }
    }
}
