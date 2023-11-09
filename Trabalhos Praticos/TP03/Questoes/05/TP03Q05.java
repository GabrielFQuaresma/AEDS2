//Importações de bibliotecas importantes
import java.util.Scanner;
import Libs.Java.Player;
import Libs.Java.PlayerFlexList;


public class TP03Q05{
    


    public static void commanderReader(String line, PlayerFlexList list) throws Exception{
        Scanner sc = new Scanner(line);
        String command = sc.next();

        if(command.equals("I*"))
        {
            int position = sc.nextInt();
            Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", sc.nextInt());
            list.insert(position, tmp);
        }
        else if(command.charAt(0) == 'I')
        {
            Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", sc.nextInt());
            
            if(command.equals("IF")) list.endInsert(tmp);
            else list.beginningInsert(tmp);
        }
        else if(command.equals("R*"))
        {
            int position = sc.nextInt();
            System.out.println("(R) " + list.remove(position).getName()); 
        }
        else if(command.charAt(0) == 'R')
        {
            System.out.println("(R) " + (command.equals("RF") ? list.endRemove().getName() : list.beginningRemove().getName()));
        }
        sc.close();

    }

    
    public static void main(String[] Args){
        Scanner sc = new Scanner(System.in);

        PlayerFlexList list = new PlayerFlexList();
        try{
            String line = sc.nextLine();

            //Leitura dos players da lista
            while(!line.equals("FIM")){
                Player tmp = Player.read("/workspaces/AEDS2/Trabalhos Praticos/TP03/Arquivo/players.csv", Integer.parseInt(line));
                list.endInsert(tmp);
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
