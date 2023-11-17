package Libs.Java;

import java.io.File;
import java.util.Scanner;

import Libs.Java.Celulas.DualCell;

public class PlayerFlexDualList {
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

    private void swap(DualCell firstCell, DualCell secondCell)
    {
        DualCell cloneFirstCell = new DualCell(secondCell.player, firstCell.prior, firstCell.next);
        firstCell.next = secondCell.next;
        firstCell.next.prior = firstCell;
        firstCell.prior = secondCell.prior;
        firstCell.prior.next = firstCell;

        secondCell = cloneFirstCell;
        secondCell.next.prior = secondCell;
        secondCell.prior.next = secondCell;

        if(secondCell == last) last = firstCell;
        else if(firstCell == last) last = secondCell;
        
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

    private void RecursiveQuickSort(int x, int z, DualCell esq, DualCell dir)
    {
        Player pivo = esq.player;
        int numEsq = x;
        int numDir = z;
        DualCell i = esq;
        DualCell j = dir;
        while ((Player.statecmpr(i.player, pivo)) < 0 || ((Player.statecmpr(i.player, pivo) == 0) && (Player.strcmpr (i.player, pivo)) < 0)) {i = i.next; x++;}
        while ((Player.statecmpr(i.player, pivo)) > 0 || ((Player.statecmpr(i.player, pivo) == 0) && (Player.strcmpr(i.player, pivo)) > 0)) {j = j.prior; z--;}//Desempate por nome 

        if (x <= z){
            swap(i, j);
            i = i.next;
            j = j.prior;
        }
            
        if(numEsq < z)
            RecursiveQuickSort(numEsq, z, esq, j);
        if(numDir > x)
            RecursiveQuickSort(x, numDir, i, dir);
    }

    void QuickSortPartial()
    {
        RecursiveQuickSort(0, size - 1, first, last);
    }
}