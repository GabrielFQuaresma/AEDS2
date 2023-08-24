class Arquivo{
	public static void saveString (){
		String fileName = MyIO.readString("Nome do arquivo: ");
		String line = MyIO.readLine("O que você deseja escrever: ");
		
		Arq.openWrite(fileName + ".txt");
		
		Arq.print(line);
		
		Arq.close();
	}
	
	public static void printArchive(){
		String fileName = MyIO.readString("Nome do arquivo: ");
		String content = "";
		
		Arq.openRead(fileName + ".txt");
		
		content = Arq.readAll();
		
		Arq.close();
		System.out.print(content);
	}
	
	public static void printArchiveHigher(){
		String fileName = MyIO.readString("Nome do arquivo: ");
		char letter = ' ';
		String content = "";
		
		Arq.openRead(fileName + ".txt");
		
		content = Arq.readAll();
		
		content = content.toUpperCase();
		Arq.close();
		System.out.print(content);
	}
	
	public static void copyArchive(){
		
		Arq.openRead(MyIO.readString("Nome do arquivo: ") + ".txt");
        
        String str = Arq.readAll();

        Arq.close();

        Arq.openWrite(MyIO.readString("Nome do segundo arquivo: ") + ".txt");
        Arq.print(str);
       
        Arq.close();
	}
	public static void main(String args[]){
		
		int x = MyIO.readInt("Qual metodo voce deseja testar: ");
		
		switch(x)
		{
			case 1:
			saveString();
			break;
			case 2:
			printArchive();
			break;
			case 3:
			printArchiveHigher();
			break;
			case 4:
			copyArchive();
			break;
			default:
			System.out.println("Valor nao existe");
			break;
		}
	}
}