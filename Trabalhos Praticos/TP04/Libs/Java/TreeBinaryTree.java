package Libs.Java;

public class TreeBinaryTree {
    public TreeCell root;

    public TreeBinaryTree()
    {
        root = new TreeCell(-1);
    }



    private boolean search(TreeCell root, int height, String name)
    {
        boolean result;
        if(height > root.height){
            result = search(root.right, height, name);
        }
        else if(height < root.height){
            result = search(root.left, height, name);
        }
        else{
            result = root.stringTree.search(name);
        }
        return result;
    }

    public boolean search(int height, String name)
    {
        return search(root, height ,name);
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
