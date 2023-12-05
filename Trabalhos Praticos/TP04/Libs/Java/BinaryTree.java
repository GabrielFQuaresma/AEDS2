package Libs.Java;

import Libs.Java.DualCell;

public class BinaryTree {
    private DualCell root;

    public BinaryTree()
    {
        root = null;
    }

    public void insert(String nameString)
    {
        root = insert(root, nameString);
    }

    private DualCell insert(DualCell root, String nameString)
    {
        if (root == null)
        {
            root = new DualCell(nameString, null, null);
        }
        else if (nameString.compareTo(root.name) < 0)
        {
            root.left = insert(root.left, nameString);
        }
        else if (nameString.compareTo(root.name) > 0)
        {
            root.right = insert(root.right, nameString);
        }

        return root;
    }

    private boolean search(String nameString, DualCell root)
    {
        boolean found;
        if(root == null){
            found = false;
        }
        else if(nameString.compareTo(root.name) > 0){
            System.out.print("dir ");
            found = search(nameString, root.right);
        }
        else if(nameString.compareTo(root.name) < 0){
            System.out.print("esq ");
            found = search(nameString, root.left);
        }
        else{
            found = true;
        }
        return found;
    }

    public boolean search(String nameString)
    {
        System.out.print("raiz ");
        return search(nameString, root);
    }
}
