package sistema;

public class Main {

    public static void main(String[] args){
        BdFuncoes bd=new BdFuncoes ();
        bd.conectar_ao_bd("sistema-hotelaria", "postgres","1234");
    }
}
