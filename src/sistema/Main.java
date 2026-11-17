package sistema;

import java.sql.Connection;
import java.sql.SQLOutput;
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
            switch (i){
                case 1:
                    System.out.println("Insira o nome do hotel:");
                    String nome_hotel = sc.nextLine();

                    System.out.println("Insira o CNPJ do hotel:");

                       }
        }
    }
}
