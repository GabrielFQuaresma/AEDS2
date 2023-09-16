class MyClass{
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
}




class TP01Q12{
	
	
	public static String ciframentoCesar(int chave, int index, String palavra, String tmp){
		
		if (index < palavra.length())
		{
			int newValueOfChar = (((int) palavra.charAt(index)) + chave);
			char newChar = (char)(newValueOfChar);
			tmp = (ciframentoCesar(chave, index + 1, palavra, tmp + newChar));
		}
		return(tmp);
	}
	
	public static String ciframentoCesar(int chave, String palavra){
		return (ciframentoCesar(chave, 0, palavra, ""));
	}
	
	
	
	public static void main(String Args[]){
		
		
		String palavra = MyIO.readLine("");
		while(!MyClass.equals(palavra, "FIM")){
			MyIO.println(ciframentoCesar(3, palavra));
			palavra = MyIO.readLine("");
		}		
	}
}