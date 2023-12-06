//Importações de bibliotecas importantes
import java.util.Scanner;
import Libs.Java.*;


public class TP04Q01{


    
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        BinaryTree tree = new BinaryTree();
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", Integer.parseInt(line));
                tree.insert(tmp.getName());
                line = sc.nextLine();
            }

            //Leitura dos comandos
            while(sc.hasNextLine()){
                line = sc.nextLine();
                System.out.print(line + " ");
                System.out.println(tree.search(line) ? "SIM" : "NAOO");
            }

        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
    
}
