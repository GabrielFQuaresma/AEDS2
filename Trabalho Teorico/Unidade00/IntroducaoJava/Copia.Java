

class Copia{
    public static void main(String[] args){
		
        Arq.openRead("Exemplo.txt");
        
        String str = Arq.readAll();

        Arq.close();

        Arq.openWrite("Copia.txt");
        Arq.print(str);
       
        Arq.close();
    }
}