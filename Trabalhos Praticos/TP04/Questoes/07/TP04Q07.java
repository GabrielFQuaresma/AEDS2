//Importações de bibliotecas importantes
import java.util.Scanner;
import Libs.Java.BasicHash;
import Libs.Java.Player;

public class TP04Q07 {
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        BasicHash hash = new BasicHash(21, 9);
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", Integer.parseInt(line));
                hash.insert(tmp.getName(), tmp.getHeight());
                line = sc.nextLine();
            }

            //Leitura dos comandos
            while(sc.hasNextLine()){
                line = sc.nextLine();
                Player tmp = Player.searchByName("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", line);
                System.out.print(line + " ");
                System.out.println(hash.search(line, tmp.getHeight()) ? "SIM" : "NAO");
            }

        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
}
