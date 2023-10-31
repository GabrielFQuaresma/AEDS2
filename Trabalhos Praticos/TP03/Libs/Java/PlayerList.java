package Libs.Java;

import java.util.Scanner;
import java.io.File;

public class PlayerList{
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
