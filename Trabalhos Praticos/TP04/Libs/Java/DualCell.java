package Libs.Java;


public class DualCell {
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