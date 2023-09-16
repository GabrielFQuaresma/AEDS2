
class Exercicios{
	
	public static int exercicio01(int number, int times){
		if(times > 1){
			return(number + exercicio01(number, times-1));
		}
		else if (times < 0){
			return(-exercicio01(number, times*-1));
		}
		return(number);
	}
	
	public static int exercicio02(int number, int length, int array[]){
		int max = number;
		if(length > 0){
			
			max = (number > array[length-1]) ? number : array[length-1];
			max = exercicio02(max, length - 1, array);
		}
		return (max);
	}
	
	public static int exercicio03(int length, int cont, char array[]){
		int vowels = cont;
		if (length > 0)
		{
			vowels += (array[length - 1] == 'a' || array[length - 1] == 'e' || array[length - 1] == 'i' || 
			array[length - 1] == 'o' || array[length - 1] == 'u') ? 1 : 0;
			vowels = exercicio03(length - 1, vowels, array);			
		}
		return(vowels);
	}
	
	public static void main(String args[]){
		int x = MyIO.readInt("Qual metodo voce deseja testar: ");
		
		switch(x)
		{
			case 1:
			System.out.println(exercicio01(MyIO.readInt("Numero 1: "), MyIO.readInt("Numero 2: ")));
			break;
			case 2:
				int length = MyIO.readInt("Tamanho: ");
			
				
				int array[] = new int[length];
				
				for(int i = 0; i < length; i++){
					array[i] = MyIO.readInt("Valor: ");
				}
			
				System.out.println(exercicio02(array[0], length, array));
			break;
			case 3:
				String word = MyIO.readString("Digite a palavra: ");
				int length2 = word.length();
				word = word.toLowerCase();
				
				
				char c_array[] = word.toCharArray();
				System.out.println(exercicio03(length2, 0, c_array));
			break;
			default:
			System.out.println("Valor nao existe");
			break;
		}
	}
}