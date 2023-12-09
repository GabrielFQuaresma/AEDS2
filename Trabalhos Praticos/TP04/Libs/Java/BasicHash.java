package Libs.Java;


public class BasicHash {
    public String[] array;
    private final int sizeHash;
    private final int reservationHash;
    private int lastPositionReservation;
    
    public BasicHash(int sizeHash, int reservationHash)
    {
        this.sizeHash = sizeHash;
        this.reservationHash = reservationHash;

        this.lastPositionReservation = sizeHash;
        
        this.array = new String[sizeHash + reservationHash];
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
        else if(lastPositionReservation < array.length){
            array[lastPositionReservation++] = name;
        }
    }

    public boolean search(String name, int height){

        boolean result = false;
        int hashPosition = height % sizeHash;
        if((!positionFree(hashPosition)) && array[hashPosition].equals(name)){
            result = true;
        }
        else if(!positionFree(hashPosition)){
            for(int i = sizeHash; i < lastPositionReservation; i++){
                if(array[i].equals(name)){
                    result = true;
                }
            }
        }
        return result;
    }
}
