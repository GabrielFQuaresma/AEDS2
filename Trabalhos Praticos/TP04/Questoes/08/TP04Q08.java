import java.util.Scanner;
import Libs.Java.ReHash;
import Libs.Java.Player;

class TP04Q08 {
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        ReHash hash = new ReHash(25);
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", Integer.parseInt(line));
                hash.insert(tmp.getName(), tmp.getHeight());
                line = sc.nextLine();
            }

            line = sc.nextLine();
            //Leitura dos comandos
            while(!line.equals("FIM")){
                Player tmp = Player.searchByName("workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", line);
                System.out.print(line + " ");
                System.out.println(hash.search(line, tmp.getHeight()) ? "SIM" : "NAO");
                line = sc.nextLine();
            }

        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
}
