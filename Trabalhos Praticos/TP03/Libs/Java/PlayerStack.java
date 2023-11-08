package Libs.Java;

import java.io.File;
import java.util.Scanner;

public class PlayerStack{
    Player[] array;
    int size;
    int maxSize;
    
    public PlayerStack()
    {
        this.array = new Player[1];
        this.size = 0;
        this.maxSize = 1;
    }

    public PlayerStack(int maxSize) {
        this.array = new Player[maxSize];
        this.size = 0;
        this.maxSize = maxSize;
    }
    public PlayerStack(Player[] array, int size, int maxSize) {
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

    public void insert(Player player) throws Exception
    {
        if(size == maxSize){
            throw new Exception("Erro: Inserindo numa fila cheia");
        }

        this.array[size++] = player;
    }

    public Player remove() throws Exception
    {
        if(size <= 0){
            throw new Exception("Erro: Removendo numa fila vazia");
        }

        Player removed = this.array[--size];
        
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

    public static PlayerStack readFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não existe");
        }
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        sc.nextLine();

        PlayerStack playerStack = new PlayerStack(6000);

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] strings = Player.isEmpty(line.split(",", -1));
            
            Player tmp = Player.newPlayerByString(strings);

            try{
                playerStack.insert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return playerStack;
    }
}