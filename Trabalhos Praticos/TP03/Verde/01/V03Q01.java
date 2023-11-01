import java.util.Scanner;
import java.io.File;

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

class PlayerList{
    private Player[] array;
    private int size;
    private int maxSize;

    public PlayerList()
    {
        this.array = new Player[1];
        this.size = 0;
        this.maxSize = 1;
    }

    public PlayerList(int maxSize) {
        this.array = new Player[maxSize];
        this.size = 0;
        this.maxSize = maxSize;
    }
    public PlayerList(Player[] array, int size, int maxSize) {
        this.array = array;
        this.size = size;
        this.maxSize = maxSize;
    }

    public void setArray(Player[] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void beginningInsert(Player player) throws Exception
    {
        if(size == maxSize){
            throw new Exception("Erro: Inserindo numa fila cheia");
        }

        size++;
        for(int i = size; i > 0; i--) array[i] = array[i - 1];

        this.array[0] = player;
    }

    public void endInsert(Player player) throws Exception
    {
        if(size == maxSize){
            throw new Exception("Erro: Inserindo numa fila cheia");
        }

        this.array[size++] = player;
    }

    public void insert(int position, Player player) throws Exception
    {
        if(size == maxSize || position < 0){
            throw new Exception("Erro: Inserção incorreta");
        }
        else if(position > size) endInsert(player);
        else if(position == 0) beginningInsert(player);
        else{
            size++;

            for(int i = size; i > position; i--) array[i] = array[i - 1]; 
            array[position] = player;
        }
    }
    

    public Player beginningRemove() throws Exception
    {
        if(size <= 0){
            throw new Exception("Erro: Removendo numa fila vazia");
        }

        Player removed = this.array[0];
        size--;
        for(int i = 0; i < size; i++)
        {
            this.array[i] = this.array[i + 1];
        }
        return removed;
    }


    public Player endRemove() throws Exception
    {
        if(size <= 0){
            throw new Exception("Erro: Removendo numa fila vazia");
        }

        Player removed = this.array[--size];
        
        return removed;
    }

    public Player remove(int position) throws Exception
    {
        if(size <= 0 || position < 0 || position > size ){
            throw new Exception("Erro: Removendo incorretamente");
        }

        Player removed = this.array[position];
        size--;

        for(int i = position; i < size; i++)
        {
            this.array[i] = this.array[i + 1];
        }
        return removed;
    }

    public void show()
    {
        for(int i = 0; i < size; i++)
        {
            Player.print(array[i]);
        }
    }

    public void show(int k) throws Exception
    {
        if(k > size)
        {
            throw new Exception("ERROR: Partial is greater then the size of playerlist");
        }

        for(int i = 0; i < k; i++)
        {
            Player.print(array[i]);
        }
    }

    public void showPosition()
    {
        for(int i = 0; i < size; i++)
        {
            System.out.println("[" + i + "] " + array[i]);
        }
    }

    private static void swap(Player[] array, int i, int j) 
    {
        Player tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static PlayerList readFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não existe");
        }
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        sc.nextLine();

        PlayerList playerlist = new PlayerList(6000);

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] strings = Player.isEmpty(line.split(",", -1));
            
            Player tmp = Player.newPlayerByString(strings);

            try{
                playerlist.endInsert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return playerlist;
    }


// --------------------------------------------  Pesquisa  --------------------------------------------

    public boolean sequencialSearch(String name)
    {
        boolean findIt = false;
        for (int i = 0; i < size; i++)
        {
            Player tmp = array[i];
            if(tmp.getName().contains(name))
            {
                findIt = true;
                i = size;
            }
        }
        return findIt;
    }



// -------------------------------------------- Ordenação --------------------------------------------



    public void nameSelectionPartialSort(int k) throws Exception
    {
        if (size <= 0 && k > size) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }


        for(int i = 0; i < k; i++)
        {
            int menor = i;
            for(int j = i + 1; j < size; j++){
                if(Player.strcmpr(array[menor], array[j]) > 0) 
                    menor = j;
            }
            swap(array, i, menor);
        }
            
    }


    public void birthYearInsertionSort() throws Exception
    {
        if (size <= 0) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }
 
        for (int i = 1; i < size; i++){
            int j = i;
            Player tmp = array[i];
            while(j > 0 && Player.birthcmpr(tmp, array[j-1]) < 0) array[j] = array[--j];
            array[j] = tmp;
        }
    }



    //Ordenação por nome com metodo de inserção
    public void nameInsertionSort() throws Exception
    {

        if (size <= 0) {
            throw new Exception("Erro: Fila com tamanho inválido");
        }


        for (int i = 1; i < size; i++)
        {
            int j = i;
            Player tmp = array[i];
            while(j > 0 && Player.strcmpr(tmp, array[j-1]) < 0)
            {
                array[j] = array[--j];
            }
            array[j] = tmp;
        }
    }



    //------------------------------- Quick Sort -------------------------------

    private void RecursiveQuickSortPartial(int k, int esq, int dir)
    {
        Player pivo = array[(esq + dir)/2];
        int i = esq;
        int j = dir;
        while ((Player.statecmpr(array[i], pivo)) < 0 || ((Player.statecmpr(array[i], pivo) == 0) && (Player.strcmpr (array[i], pivo)) < 0)) i++;
        while ((Player.statecmpr(array[j], pivo)) > 0 || ((Player.statecmpr(array[j], pivo) == 0) && (Player.strcmpr(array[j], pivo)) > 0)) j--; //Desempate por nome 
    
        if (i <= j)
            swap(array, i++, j--);
        if(esq < j)
            RecursiveQuickSortPartial(k, esq, j);
        if(dir > i && i < k)
            RecursiveQuickSortPartial(k, i, dir);
    }

    void QuickSortPartial(int k)
    {
        RecursiveQuickSortPartial(k, 0, size - 1);
    }

}


public class V03Q01{
    
    static final int MaxSizeList = 6000;


    public static void commanderReader(String line, PlayerList list) throws Exception{
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

        PlayerList list = new PlayerList(MaxSizeList);
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
