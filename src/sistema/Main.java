package sistema;

import java.sql.Connection;

public class Main {

    public static void main(String[] args){
        BdFuncoes bd=new BdFuncoes ();
        Connection con = bd.conectar_ao_bd("sistema-hotelaria", "postgres","1234");
    }
}
