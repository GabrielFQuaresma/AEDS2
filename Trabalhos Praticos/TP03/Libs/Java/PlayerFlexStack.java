package Libs.Java;

import java.io.File;
import java.util.Scanner;

import Libs.Java.Celulas.Cell;

public class PlayerFlexStack {
    private Cell top;
    private int size;

    public PlayerFlexStack()
    {
        this.top = null;
        this.size = 0;
    }

    public int getSize(){return this.size;}

    //---------------------------------- METODOS DE INSERÇÃO E REMOÇÃO ----------------------------------

    public void insert(Player player)
    {
        Cell newCell = new Cell(player, top);
        top = newCell;
        this.size++;
    }

    public Player remove() throws Exception
    {
        if(top == null){
            throw new Exception("Erro: Removendo em fila vazia!");
        }
        Player removed = top.player;
        top = top.next;

        this.size--;

        return removed;
    }

    //---------------------------------- METODOS DE MOSTRAGEM ----------------------------------

    public void show() {
		show(top);
	}

	private void show(Cell i) {
		if (i != null) {
			show(i.next);
			Player.print(i.player);
		}
	}

    public void showPosition()
    {
        showPosition(size - 1, top);
    }

    private void showPosition(int index, Cell i) {
		if (i != null) {
			showPosition(index - 1, i.next);
			System.out.println("[" + index + "] " + i.player);
		}
	}

    //---------------------------------- METODOS DE LEITURA ----------------------------------

    public static PlayerFlexStack readFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("Arquivo não existe");
        }
        Scanner sc = new Scanner(file);
        sc.useDelimiter(",");
        sc.nextLine();

        PlayerFlexStack playerflexstack = new PlayerFlexStack();

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] strings = Player.isEmpty(line.split(",", -1));
            
            Player tmp = Player.newPlayerByString(strings);

            try{
                playerflexstack.insert(tmp);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return playerflexstack;
    }
}
