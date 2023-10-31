
import java.util.Scanner;

// class FilaRecreio{
//     private int[] array;
//     private int size;
//     private int maxSize;


//     public void insert(int number)
//     {
//         int i = 0;
//         while(i < size && number > array[i]) i++;
        
//         for(int j = size + 1; j > i && j < maxSize; j++) array[j] = array[j - 1];
        
//         array[i] = number;
//     }

//     public
// }




public class WUp02Q01 {
    public static void swap(int position1, int position2, int[] array)
    {
        int tmp = array[position1];
        array[position1] = array[position2];
        array[position2] = tmp;
    }

    public static int InvertedSelectionSort(int[] array)
    {
        int swaps = 0;
        for(int i = 0; i < array.length - 1; i++){
            int maior = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[maior] < array[j]) maior = j;
            }
            if(maior != i){
                swaps += 2;
                swap(maior, i, array);
            } 
        }
        return array.length - swaps;
    }

    public static void main(String[] Args)
    {
        Scanner sc = new Scanner(System.in);

        int quantidade = sc.nextInt();
        // System.out.println("quantidade:" + quantidade);
        for(int i = 0; i < quantidade; i++)
        {
            int numeros = sc.nextInt();
            // System.out.println("numeros:" + numeros);
            int[] array = new int[numeros];
            for(int j = 0; j < numeros; j++)
            {
                array[j] = sc.nextInt();
            //    System.out.println("numero:" + array[j]);
            }
             System.out.println(InvertedSelectionSort(array));
        }
        sc.close();
    }

}
