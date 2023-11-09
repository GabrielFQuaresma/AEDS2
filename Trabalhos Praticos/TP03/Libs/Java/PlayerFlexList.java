package Libs.Java;

import java.io.File;
import java.util.Scanner;

import Libs.Java.Celulas.Cell;

public class PlayerFlexList {
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