import java.util.Scanner;
import Libs.Java.TreeBinaryTree;
import Libs.Java.Player;

public class TP04Q02 {
    
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        TreeBinaryTree tree = new TreeBinaryTree();
        try{
            String line = sc.nextLine();
            
            tree.insert(7);
            tree.insert(3);
            tree.insert(11);
            tree.insert(1);
            tree.insert(5);
            tree.insert(9);
            tree.insert(12);
            tree.insert(0);
            tree.insert(2);
            tree.insert(4);
            tree.insert(6);
            tree.insert(8);
            tree.insert(10);
            tree.insert(13);
            tree.insert(14);

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", Integer.parseInt(line));
                tree.insert(tmp.getHeight() % 15, tmp.getName());
                line = sc.nextLine();
            }

            line = sc.nextLine();
            //Leitura dos comandos
            while(!line.equals("FIM")){
                Player tmp = Player.searchByName("workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", line);
                System.out.print(line + " ");
                System.out.println(tree.search(tmp.getHeight() % 15, line) ? "SIM" : "NAO");
                line = sc.nextLine();
            }

        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
}
