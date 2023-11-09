import java.io.File;
import java.util.Scanner;

class Player {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String university;
    private int birthYear;
    private String birthCity;
    private String birthState;


    public Player() {
        this.id = 0;
        this.name = new String();
        this.height = 0;
        this.weight = 0;
        this.university = new String();
        this.birthYear = 0;
        this.birthCity = new String();
        this.birthState = new String();
    }

    public Player(int id, String name, int height, int weight, String university, int birthYear, String birthCity, String birthState) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.university = university;
        this.birthYear = birthYear;
        this.birthCity = birthCity;
        this.birthState = birthState;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setBirthcity(String birthCity) {
        this.birthCity = birthCity;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getUniversity() {
        return university;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public String getBirthState() {
        return birthState;
    }

    public Player clone() {
        return (new Player(this.id, this.name, this.height, this.weight, this.university, this.birthYear, this.birthCity, this.birthState));
    }

    @Override
    public String toString(){
        return "## " + name + " ## " + height + " ## " +  weight + " ## " + birthYear + " ## " + university + " ## " +  birthCity + " ## " + birthState + " ##"; 
    }

    public static void print(Player tmp) {
        System.out.print("[" + tmp.id + " ## ");
        System.out.print(tmp.name + " ## ");
        System.out.print(tmp.height + " ## ");
        System.out.print(tmp.weight + " ## ");
        System.out.print(tmp.birthYear + " ## ");
        System.out.print(tmp.university + " ## ");
        System.out.print(tmp.birthCity + " ## ");
        System.out.println(tmp.birthState + "]");
    }

    public static Player newPlayerByString(String[] strings) {
        int id = Integer.parseInt(strings[0]);
        String name = strings[1];
        int height = Integer.parseInt(strings[2]);
        int weight = Integer.parseInt(strings[3]);
        String university = strings[4];
        int birthYear = Integer.parseInt(strings[5]);
        String birthCity = strings[6];
        String birthState = strings[7];

        return new Player(id, name, height, weight, university, birthYear, birthCity, birthState);
    }

    public static String[] isEmpty(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].isBlank()) {
                strings[i] = "nao informado";
            }
        }
        return strings;
    }

    public static Player read(String filePath, int id) throws Exception {
        Player tmp = new Player();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Deu excessao");
            throw new Exception("Arquivo não existe");
        }

        Scanner sc = new Scanner(file);
        sc.nextLine();
        sc.useDelimiter(",");

        int tmpId = sc.nextInt();

        while (tmpId != id && sc.hasNext()) {
            sc.nextLine();
            tmpId = sc.nextInt();
        }

        if (tmpId == id) {
            String str = sc.nextLine();
            // System.out.println(str);
            String[] strings = isEmpty(str.split(",", -1));

            String name = strings[1];
            int height = Integer.parseInt(strings[2]);
            int weight = Integer.parseInt(strings[3]);
            String university = strings[4];
            int birthYear = Integer.parseInt(strings[5]);
            String birthCity = strings[6];
            String birthState = strings[7];

            tmp = new Player(tmpId, name, height, weight, university, birthYear, birthCity, birthState);

        }
        sc.close();
        return tmp;
    }

    public static int strcmpr(Player player1, Player player2)
    {
        return player1.getName().compareTo(player2.getName());
    }

    public static int birthcmpr(Player player1, Player player2)
    {
        int bigger = 0;

        if(player1.birthYear > player2.birthYear)
            bigger = 1;
        else if(player1.birthYear < player2.birthYear)
            bigger = -1;
        return bigger;
    }

    public static int statecmpr(Player player1, Player player2)
    {
        return player1.getBirthState().compareTo(player2.getBirthState());
    }

}



class Cell {
    public Player player;
    public Cell next;

    public Cell(){
        this.player = null;
        this.next = null;
    }

    public Cell(Player player, Cell next){
        this.player = player;
        this.next = next;
    }
}


class PlayerFlexList {
    private int size;
    private Cell first;
    private Cell last;

    public PlayerFlexList()
    {
        this.first = new Cell();
        last = first;
    }

    public int getSize(){return this.size;}

    //---------------------------------- METODOS DE INSERÇÃO E REMOÇÃO ----------------------------------

    public void beginningInsert(Player player)
    {
        Cell newCell = new Cell(player, first.next);
        if (first == last) last = newCell;
        first.next = newCell;
        this.size++;
    }

    public void endInsert(Player player)
    {
        last.next = new Cell(player, null);
        last = last.next;
        this.size++;
    }

    public void insert(int position, Player player) throws Exception
    {

        if(position < 0){
			throw new Exception("Erro ao inserir posicao (" + position + " / size = " + size + ") invalida!");
        } else if (position == 0){
             beginningInsert(player);
        } else if (position >= size){
            endInsert(player);
        } 
        else {
            Cell index = first;
            
            for(int i = 0; i < position; i++, index = index.next);
            
            Cell newCell = new Cell(player, index.next);
            index.next = newCell;
            this.size++;
        }
    }
    

    public Player beginningRemove() throws Exception
    {
        if(first == last){
            throw new Exception("Erro: Removendo em fila vazia!");
        }
        
        Cell removedCell = first.next;
        Player removed = first.next.player;
        first.next = removedCell.next;

        this.size--;
        return removed;
    }


    public Player endRemove() throws Exception
    {
        if(first == last){
            throw new Exception("Erro: Removendo em fila vazia!");
        }
        
        Cell i;
        Player removed;
		for (i = first; i.next.next != null; i = i.next);

        removed = i.next.player;
        i.next = null;
        last = i;

        this.size--;

        return removed;
    }

    public Player remove(int position) throws Exception {
    if (first == last) {
        throw new Exception("Erro: Removendo em fila vazia!");
    }
    else if (position < 0) {
        throw new Exception("Erro: Posição inválida de remoção!");
    }
    else if (position == 0) return beginningRemove();
    else if(position >= size) return endRemove();
     else {
        Cell index = first;
        for (int i = 0; i < position; i++, index = index.next);
        
        Cell tmp = index.next;
        Player removed = tmp.player;
        index.next = tmp.next;
        return removed;
    }
}

    //---------------------------------- METODOS DE MOSTRAGEM ----------------------------------

    public void show()
    {
        for(Cell tmp = first.next; tmp != null; tmp = tmp.next){
            Player.print(tmp.player);
        }
    }

    public void showPosition()
    {
        Cell tmp = first.next;
        for(int i = 0; tmp != null; tmp = tmp.next, i++){
            System.out.println("[" + i + "] " + tmp.player);
        }
    }

    //---------------------------------- METODOS DE LEITURA ----------------------------------

    public static PlayerFlexList readFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não existe");
        }
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        sc.nextLine();

        PlayerFlexList playerflexlist = new PlayerFlexList();

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] strings = Player.isEmpty(line.split(",", -1));
            
            Player tmp = Player.newPlayerByString(strings);

            try{
                playerflexlist.endInsert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return playerflexlist;
    }
}

public class V03Q05{
    


    public static void commanderReader(String line, PlayerFlexList list) throws Exception{
        Scanner sc = new Scanner(line);
        String command = sc.next();

        if(command.equals("I*"))
        {
            int position = sc.nextInt();
            Player tmp = Player.read("/tmp/players.csv", sc.nextInt());
            list.insert(position, tmp);
        }
        else if(command.charAt(0) == 'I')
        {
            Player tmp = Player.read("/tmp/players.csv", sc.nextInt());
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
                Player tmp = Player.read("/tmp/players.csv", Integer.parseInt(line));
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

