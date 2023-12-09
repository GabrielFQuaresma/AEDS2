package Libs.Java;


public class ReHash {
    public String[] array;
    private final int sizeHash;
    
    public ReHash(int sizeHash)
    {
        this.sizeHash = sizeHash;
        
        this.array = new String[sizeHash];
        for(int i = 0; i < array.length; i++) array[i] = "null"; 
    }

    private boolean positionFree(int position)
    {
        return (array[position].equals("null"));
    }

    public void insert(String name, int height)
    {
        int hashPosition = height % sizeHash;
        if(positionFree(hashPosition)) {
            array[hashPosition] = name;
        }
        else if(positionFree(hashPosition + 1)){
            array[hashPosition + 1] = name;
        }
    }

    public boolean search(String name, int height){

        boolean result = false;
        int hashPosition = height % sizeHash;
        if((!positionFree(hashPosition)) && array[hashPosition].equals(name)){
            result = true;
        }
        else if(!positionFree(hashPosition + 1) && array[hashPosition + 1].equals(name)){
            result = true;
        }
        return result;
    }
}
