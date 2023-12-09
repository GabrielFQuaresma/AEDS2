
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

    public static Player searchByName(String filePath, String name) throws Exception {
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
        String tmpName = sc.next();

        while (!tmpName.equals(name) && sc.hasNext()) {
            sc.nextLine();
            tmpId = sc.nextInt();
            tmpName = sc.next();
        }
        
        if (tmpName.equals(name)) {
            String str = sc.nextLine();
            String[] strings = isEmpty(str.split(",", -1));
            
            int height = Integer.parseInt(strings[1]);
            int weight = Integer.parseInt(strings[2]);
            String university = strings[3];
            int birthYear = Integer.parseInt(strings[4]);
            String birthCity = strings[5];
            String birthState = strings[6];
            
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
    public String name;
    public DualCell left; //Anterior
    public DualCell right;

    public DualCell(){
        this.name = null;
        this.right = null;
    }

    public DualCell(String name, DualCell right){
        this.name = name;
        this.right = right;
    }

    public DualCell(String name, DualCell left, DualCell right){
        this.name = name;
        this.right = right;
        this.left = left;
    }
}

class BinaryTree {
    private DualCell root;

    public BinaryTree()
    {
        root = null;
    }

    public void insert(String nameString)
    {
        root = insert(root, nameString);
    }

    private DualCell insert(DualCell root, String nameString)
    {
        if (root == null)
        {
            root = new DualCell(nameString, null, null);
        }
        else if (nameString.compareTo(root.name) < 0)
        {
            root.left = insert(root.left, nameString);
        }
        else if (nameString.compareTo(root.name) > 0)
        {
            root.right = insert(root.right, nameString);
        }

        return root;
    }

    private boolean search(String nameString, DualCell root)
    {
        boolean found = false;
        if(root != null)
        {
            if(nameString.compareTo(root.name) == 0){
                return true;
            }

            if(!found){
                System.out.print("ESQ ");
                found = search(nameString, root.left);
            }

            if(!found){
                System.out.print("DIR ");
                found = search(nameString, root.right);
            }
        }
        return found;
    }

    public boolean search(String nameString)
    {
        return search(nameString, root);
    }
}

class TreeCell{
    public BinaryTree stringTree;
    public TreeCell right;
    public TreeCell left;
    public int height;

    public TreeCell(int height)
    {
        this.stringTree = new BinaryTree();
        this.height = height;
    }
    
}

class TreeBinaryTree {
    public TreeCell root;

    public TreeBinaryTree()
    {
        root = new TreeCell(-1);
    }



    private boolean search(TreeCell root, String name)
    {
        boolean result = false;
        if(root != null)
        {
            result = root.stringTree.search(name);

            if(!result){
                System.out.print("esq ");
                result = search(root.left, name);
            }
            
            if(!result)
            {
                System.out.print("dir ");
                result = search(root.right, name);
            }

        }
        return result;
    }

    public boolean search(String name)
    {
        System.out.print("raiz ");
        return search(root, name);
    }

    private TreeCell insert(TreeCell root, int height, String name)
    {
        if(height > root.height){
            root.right = insert(root.right, height, name);
        }
        else if(height < root.height){
            root.left = insert(root.left, height, name);
        }
        else{
            root.stringTree.insert(name);
        }
        return root;
    }

    public void insert(int height, String name)
    {
        root = insert(root, height ,name);
    }


    private TreeCell insert(TreeCell root, int num)
    {
        if(root == null){
            root = new TreeCell(num);
        }
        else if(num > root.height){
            root.right = insert(root.right, num);
        }
        else if(num < root.height){
            root.left = insert(root.left, num);
        }
        return root;
    }

    public void insert(int num){
        root = insert(root, num);    
    }
}

public class V04Q02 {
    
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
                Player tmp = Player.read("/tmp/players.csv", Integer.parseInt(line));
                tree.insert(tmp.getHeight() % 15, tmp.getName());
                line = sc.nextLine();
            }

            line = sc.nextLine();
            //Leitura dos comandos
            while(!line.equals("FIM")){
                System.out.print(line + " ");
                System.out.println(tree.search(line) ? "SIM" : "NAO");
                line = sc.nextLine();
            }

        }catch(Exception e){
            e.getMessage();
        }
        sc.close();
    }
}
