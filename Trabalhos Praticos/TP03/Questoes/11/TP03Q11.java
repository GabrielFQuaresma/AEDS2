import java.util.Scanner;
import Libs.java.PlayerFlexDualList;
import Libs.java.Player;


class TP03Q11{
    
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        PlayerFlexDualList list = new PlayerFlexDualList();
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/tmp/players.csv", Integer.parseInt(line));
                list.endInsert(tmp);
                line = sc.nextLine();
            }
            list.quickSort();
            list.show();
        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
    
}