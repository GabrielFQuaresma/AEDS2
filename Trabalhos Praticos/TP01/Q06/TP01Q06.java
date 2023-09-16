
import java.util.Random;

class MyClass{
	
	/* 
		Strings
		Metodos que retorna boolean 
	*/ 
	
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
	
	//Função que passa por toda string para ver se todo os valores são vogais ou não
	public static boolean isOnlyVowels(String sentence)
	{
		int length = sentence.length();
		boolean result = true;
		
		for(int i = 0; i < length && result; i++)
		{
			result = (sentence.charAt(i) == 'a' || sentence.charAt(i) == 'e' || sentence.charAt(i) == 'i' 
					  || sentence.charAt(i) == 'o' || sentence.charAt(i) == 'u' || sentence.charAt(i) == 'A' 
					  || sentence.charAt(i) == 'E' || sentence.charAt(i) == 'I' || sentence.charAt(i) == 'O' 
					  || sentence.charAt(i) == 'U');
		}
		return (result);
	}
	
	//Função que passa por toda string para ver se todos os valores são consoantes ou não
	public static boolean isOnlyConsonant(String sentence)
	{
		int length = sentence.length();
		boolean result = true;
		
		for(int i = 0; i < length && result; i++)
		{
			char currentChar = sentence.charAt(i);
			boolean isLowerCaseLetter = ('a' <= currentChar && currentChar <= 'z');
			boolean isUpperCaseLetter = ('A' <= currentChar && currentChar <= 'Z');
			boolean isVowel = (currentChar == 'a' || currentChar == 'e' || currentChar == 'i' ||
							   currentChar == 'o' || currentChar == 'u' || currentChar == 'A' ||
							   currentChar == 'E' || currentChar == 'I' || currentChar == 'O' ||
							   currentChar == 'U');
							
			result = (isLowerCaseLetter || isUpperCaseLetter) && !isVowel;
		}
		return (result); 
	}
	
	//Função que passa por toda string para ver se todos os valores são inteiros
	public static boolean isOnlyInteger(String sentence)
	{
		int length = sentence.length();
		boolean result = true;
		
		for(int i = 0; i < length && result; i++)
		{
			result = ( '0' <= sentence.charAt(i) && sentence.charAt(i) <= '9');
		}
		return result;
	}
	
	//Função que passa por toda string para ver se todos os valores são floats
	public static boolean isOnlyFloat(String sentence)
	{
		int length = sentence.length();
		int dots = 0;
		
		boolean result = true;
		
		for(int i = 0; i < length && result; i++)
		{
			result = ('0' <= sentence.charAt(i) && sentence.charAt(i) <= '9');
			
			if(sentence.charAt(i) == '.' || sentence.charAt(i) == ',')
			{
				dots++;
				result = true;
			}
		}
		return (result && dots < 2);
	}
	
	/* 
		Char
		Metodos que retorna boolean 
	*/ 
	//Função que retorna se o char é vogal ou não
	public static boolean isVowels(char letter)
	{
		return(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u'
			   || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U');
	}
	
	//Função que retorna se o char é consoante ou não
	public static boolean isConsonant(char letter)
	{
		return((('a' <= letter && letter <= 'z') || ('A' <= letter && letter <= 'Z')) && (letter != 'a' || 
		letter != 'e' || letter != 'i' || letter != 'o' || letter != 'u'|| letter != 'A' || letter != 'E' || 
		letter != 'I' || letter != 'O' || letter != 'U'));
	}
	
	//Função que retorna se o char é numero ou não
	public static boolean isNumber(char number)
	{
		return('0' <= number && number <= '9');
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



class TP01Q06{
	
	public static void main (String Args[])
	{
		String frase = MyIO.readLine();
		
		while(!(MyClass.equals(frase, "FIM")))
		{
			System.out.print(MyClass.isOnlyVowels(frase) ? "SIM " : "NAO ");
			System.out.print(MyClass.isOnlyConsonant(frase) ? "SIM " : "NAO ");
			System.out.print(MyClass.isOnlyInteger(frase) ? "SIM " : "NAO ");
			System.out.println(MyClass.isOnlyFloat(frase) ? "SIM " : "NAO ");
			frase = MyIO.readLine();
		}
	}
}