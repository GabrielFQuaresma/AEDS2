package Libs.Java;


public class TreeCell{
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

