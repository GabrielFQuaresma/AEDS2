
import Libs.Java.PlayerFlexStack;
import java.util.Scanner;
import Libs.Java.Player;

class TP03Q06{


    public static void commanderReader(String line, PlayerFlexStack stack) throws Exception{
        Scanner sc = new Scanner(line);
        String command = sc.next();

        if(command.charAt(0) == 'I'){
            Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", sc.nextInt());
            stack.insert(tmp);
        }
        else if(command.charAt(0) == 'R'){
            System.out.println("(R) " + stack.remove().getName());
        }
        sc.close();

    }

    
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        PlayerFlexStack list = new PlayerFlexStack();
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", Integer.parseInt(line));
                list.insert(tmp);
                line = sc.nextLine();
            }

            //Leitura dos comandos
            while(sc.hasNextLine()){
                line = sc.nextLine();
                commanderReader(line, list);
            }

            list.showPosition();
        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
    
}
