package Libs.Java.Celulas;

import Libs.Java.Player;

public class Cell {
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
