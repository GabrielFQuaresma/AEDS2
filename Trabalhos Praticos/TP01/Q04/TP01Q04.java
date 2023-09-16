import java.util.Random;

class MyClass{
	
	//Função que retorna a igualdade entre duas strings
	public static boolean equals(String word1, String word2)
	{
		boolean result = true;
		int length = word1.length();
		if(length != word2.length())
		{
			result = false;
		}
		else{
			
			for(int i = 0; i < length && result; i++)
			{
				result = (word1.charAt(i) == word2.charAt(i));
			}
		}
		return(result);
	}
	
	
	//Função que retorna o randomize com uma seed e com limite
	public static int randomize(int seed, int limit)
	{
		//Cria um objeto "Random" com a seed passada e utiliza o metodo nextInt
		return(Math.abs(new Random(seed).nextInt()) % limit);
	}
	
		//Metodo que troca as letras que serão substituidas
	public static String changeLetters(char oldChar, char newChar, String array)
	{
		int length = array.length();
		String tmp = new String();
		
		for(int i = 0; i < length; i++)
		{
			if(array.charAt(i) == oldChar){
				tmp += newChar;
			}
			else{
				tmp += array.charAt(i);
			}
		}
		return(tmp);
	}
}

	
	
class TP01Q04{
	
	//Metodo que randomiza a string toda trocando duas letras aleatorias
	public static String randomChange(String line)
	{
		//Troca duas letras aleatorias usando a função da classe String 
		return(line.replace((char) ('a' + MyClass.randomize(4, 26)), (char) ('a' + MyClass.randomize(4, 26))));
	}
	
	//Metodo que troca as letras que serão substituidas
	public static String changeLetters(char oldChar, char newChar, String array)
	{
		int length = array.length();
		String tmp = new String();
		
		for(int i = 0; i < length; i++)
		{
			if(array.charAt(i) == oldChar){
				tmp += newChar;
			}
			else{
				tmp += array.charAt(i);
			}
		}
		return(tmp);
	}
	
	public static void main(String Args[])
	{
		MyIO.setCharset("UTF-8");
		String palavra = MyIO.readLine("");
		
		//Cria um objeto "Random" com a seed 4 
			Random gerador = new Random(4);
		
		while(!MyClass.equals(palavra, "FIM"))
		{
			
			//Utiliza o metodo nextInt
		char oldChar = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
			
		char newChar = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
			
			//Troca duas letras aleatorias usando a função 
		palavra = changeLetters(oldChar, newChar, palavra);
			
			System.out.println(palavra);
			palavra = MyIO.readLine("");
		}
	}
	
} 