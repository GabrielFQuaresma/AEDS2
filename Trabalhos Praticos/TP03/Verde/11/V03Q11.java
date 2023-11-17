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


class DualCell {
    public Player player;
    public DualCell prior; //Anterior
    public DualCell next;

    public DualCell(){
        this.player = null;
        this.next = null;
    }

    public DualCell(Player player, DualCell next){
        this.player = player;
        this.next = next;
    }

    public DualCell(Player player, DualCell prior, DualCell next){
        this.player = player;
        this.next = next;
        this.prior = prior;
    }
}

class PlayerFlexDualList {
    private int size;
    private DualCell first;
    private DualCell last;

    public PlayerFlexDualList()
    {
        this.first = new DualCell();
        last = first;
    }

    public int getSize(){return this.size;}

    //---------------------------------- METODOS DE INSERÇÃO E REMOÇÃO ----------------------------------

    public void beginningInsert(Player player)
    {
        DualCell newCell = new DualCell(player, first, first.next);
        if (first == last) last = newCell;
        first.next.prior = newCell;
        first.next = newCell;
        this.size++;
    }

    public void endInsert(Player player)
    {
        last.next = new DualCell(player, last, null);
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
            DualCell index = first;
            
            for(int i = 0; i < position; i++, index = index.next);
            
            DualCell newCell = new DualCell(player, index.next);
            index.next.prior = newCell;
            index.next = newCell;
            this.size++;
        }
    }
    

    public Player beginningRemove() throws Exception
    {
        if(first == last){
            throw new Exception("Erro: Removendo em fila vazia!");
        }
        
        DualCell removedCell = first.next;
        Player removed = first.next.player;
        first.next = removedCell.next;
        first.next.prior = first;

        this.size--;
        return removed;
    }


    public Player endRemove() throws Exception
    {
        if(first == last){
            throw new Exception("Erro: Removendo em fila vazia!");
        }
        
        DualCell i;
        Player removed;
		for (i = first; i.next.next != null; i = i.next);

        removed = i.next.player;
        i.next.prior = null;
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
            DualCell index = first;
            for (int i = 0; i < position; i++, index = index.next);
            
            DualCell tmp = index.next;
            Player removed = tmp.player;
            index.next = tmp.next;
            index.next.prior = index;
            return removed;
        }
    }

    //---------------------------------- METODOS DE MOSTRAGEM ----------------------------------

    public void show()
    {
        for(DualCell tmp = first.next; tmp != null; tmp = tmp.next){
            Player.print(tmp.player);
        }
    }

    public void showPosition()
    {
        DualCell tmp = first.next;
        for(int i = 0; tmp != null; tmp = tmp.next, i++){
            System.out.println("[" + i + "] " + tmp.player);
        }
    }

    //---------------------------------- METODOS DE LEITURA ----------------------------------

    public static PlayerFlexDualList readFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não existe");
        }
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        sc.nextLine();

        PlayerFlexDualList playerflexduallist = new PlayerFlexDualList();

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] strings = Player.isEmpty(line.split(",", -1));
            
            Player tmp = Player.newPlayerByString(strings);

            try{
                playerflexduallist.endInsert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return playerflexduallist;
    }

    // private void RecursiveQuickSort(int x, int z, DualCell esq, DualCell dir)
    // {
    //     Player pivo = esq.player;
    //     int numEsq = x;
    //     int numDir = z;
    //     DualCell i = esq;
    //     DualCell j = dir;
    //     while ((Player.statecmpr(i.player, pivo)) < 0 || ((Player.statecmpr(i.player, pivo) == 0) && (Player.strcmpr (i.player, pivo)) < 0) && i.next != null) {i = i.next; x++;}
    //     while ((Player.statecmpr(i.player, pivo)) > 0 || ((Player.statecmpr(i.player, pivo) == 0) && (Player.strcmpr(i.player, pivo)) > 0) && j.next != null) {j = j.prior; z--;}//Desempate por nome 

    //     if (x <= z){
    //         swap(i, j);
    //         i = i.next;
    //         j = j.prior;
    //     }
    
    //     if(numEsq < z)
    //         RecursiveQuickSort(numEsq, z, esq, j);
    //     if(numDir > x)
    //         RecursiveQuickSort(x, numDir, i, dir);
    // }
    
    // void QuickSortPartial()
    // {
    //     RecursiveQuickSort(0, size - 1, first, last);
    // }

    public void quickSort(){
        quickSort(first.next, last);
    }
    
    private void quickSort(DualCell esq, DualCell dir) {
        if (esq != null && dir != null && esq != dir && esq.prior != dir) {
            DualCell pivo = partition(esq, dir);
            quickSort(esq, pivo.prior);
            quickSort(pivo.next, dir);
        }
    }

    private DualCell partition(DualCell esq, DualCell dir) {
        DualCell i = esq.prior;
        for (DualCell j = esq; j != dir; j = j.next) {
            if (((Player.statecmpr(j.player, dir.player)) < 0) || ((Player.statecmpr(j.player, dir.player) == 0) && (Player.strcmpr (j.player, dir.player)) < 0)) {
                i = (i == null) ? esq : i.next;
                swap(i, j);
            }
        }
        i = (i == null) ? esq : i.next;
        swap(i, dir);
        return i;
    }

    private void swap(DualCell i, DualCell j) {
        Player temp = i.player;
        i.player = j.player;
        j.player = temp;
    }

}

class V03Q11{


    
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
