


class Excessao extends Exception{

    public static char[] numerosPares(int numero)
    {
        if(numero % 2 == 1)
        {
            throw new Exception("Valor impar");
        }
        return("par".toCharArray());
    }

    public static void main(String Args[])
    {
        int i = 2;
        
        try{
            System.out.println(numerosPares(i));
        } catch(Exception e)
        {
           System.out.println(e);
        }

    }
}