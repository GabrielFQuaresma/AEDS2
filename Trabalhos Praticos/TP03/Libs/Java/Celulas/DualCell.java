package Libs.Java.Celulas;

import Libs.Java.Player;

public class DualCell {
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