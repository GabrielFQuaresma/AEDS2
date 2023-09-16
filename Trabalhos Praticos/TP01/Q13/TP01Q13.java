import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.Random;

class MyIO {

   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
   private static String charset = "ISO-8859-1";

   public static void setCharset(String charset_){
      charset = charset_;
      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
   }

   public static void print(){
   }

   public static void print(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }
   
   public static void print(float x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }
   
   public static void print(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void print(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(){
   }

   public static void println(int x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(float x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }
   
   public static void println(double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(String x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(boolean x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void println(char x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.println(x);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static void printf(String formato, double x){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.printf(formato, x);// "%.2f"
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
   }

   public static double readDouble(){
      double d = -1;
      try{
         d = Double.parseDouble(readString().trim().replace(",","."));
      }catch(Exception e){}
      return d;
   }

   public static double readDouble(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readDouble();
   }

   public static float readFloat(){
      return (float) readDouble();
   }

   public static float readFloat(String str){
      return (float) readDouble(str);
   }

   public static int readInt(){
      int i = -1;
      try{
         i = Integer.parseInt(readString().trim());
      }catch(Exception e){}
      return i;
   }

   public static int readInt(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readInt();
   }

   public static String readString(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != ' ' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n' && tmp != ' ');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readString(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readString();
   }

   public static String readLine(){
      String s = "";
      char tmp;
      try{
         do{
            tmp = (char)in.read();
            if(tmp != '\n' && tmp != 13){
               s += tmp;
            }
         }while(tmp != '\n');
      }catch(IOException ioe){
         System.out.println("lerPalavra: " + ioe.getMessage());
      }
      return s;
   }

   public static String readLine(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readLine();
   }

   public static char readChar(){
      char resp = ' ';
      try{
         resp  = (char)in.read();
      }catch(Exception e){}
      return resp;
   }

   public static char readChar(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readChar();
   }

   public static boolean readBoolean(){
      boolean resp = false;
      String str = "";

      try{
         str = readString();
      }catch(Exception e){}

      if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || 
            str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
         resp = true;
            }

      return resp;
   }

   public static boolean readBoolean(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      return readBoolean();
   }

   public static void pause(){
      try{
         in.read();
      }catch(Exception e){}
   }

   public static void pause(String str){
      try {
         PrintStream out = new PrintStream(System.out, true, charset);
         out.print(str);
      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
      pause();
   }
}



class MyClass{
	
	
	/*
		Char
		Metodos que retornam string
	*/
	
	//Função que passa de char para inteiro positivo
	public static int ctoi(char character)
	{
			return(('0' <= character && character <= '9') ? (int)(character - '0') : -1);
	}
	


	/*
		Strings
		Metodos que retornam string
	*/
	
		//Metodo que troca todas as letras que serão substituidas
		public static String replaceAll(String oldString, String newString, String str) {
			StringBuilder result = new StringBuilder();
			int index = 0;
			int lastIndex = 0;

			while ((index = str.indexOf(oldString, lastIndex)) != -1) {
				result.append(str, lastIndex, index);
				result.append(newString);
				lastIndex = index + oldString.length();
			}

			result.append(str.substring(lastIndex));

			return result.toString();
		}
	
	//Função que retorna uma string menor, de uma string principal, que começa a partir de um index passado com parâmetro
	public static String subString(int startIndex, String mainString) {
		int length = mainString.length();
		String tmp = "";

		for (int i = startIndex; i < length; i++) {
			tmp += mainString.charAt(i);
		}
		return tmp;
	}
	
	//Função que retorna uma string menor, de uma string principal, que começa a partir de um index passado com parâmetro e termina no segundo parametro
	public static String subString(int startIndex, int endIndex, String mainString)
	{
		int length = mainString.length();
		String tmp = new String();
		if(startIndex < length && endIndex < length)
		{
			for(int i = startIndex; i <= endIndex; i++)
				tmp += mainString.charAt(i);
		}
		return tmp;
	}
	
	//Metodo que troca apenas uma letra por outra
	public static String changeLetters(char oldChar, char newChar, String array)
	{
		int time = 0;
		int length = array.length();
		String tmp = new String();
		for (int i = 0; i < length; i++)
		{
			if(array.charAt(i) == oldChar && time == 0)
			{
				tmp += newChar;
			}
			else{
				tmp += array.charAt(i);
			}
		}
		return tmp;
	}
	
	//Metodo que troca todas as letras que serão substituidas
	public static String changeAllLetters(char oldChar, char newChar, String array)
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
	
	
	/* 
		Strings
		Metodos que retornam int 
	*/ 
	
	//Função que procura uma string dentro de outra string
	public static int howMuchString(String search, String line)
	{
		int amount = 0;
		if (search != null && line != null)
		{
			int lengthLine = line.length();
			int lengthSearch = search.length();
			
			for(int i = 0; i < lengthLine; i++)
			{
				int j = 0;
				boolean isPresent = true;
				if(search.charAt(j) == line.charAt(i))
				{
					j++;
					i++;
					while(j < lengthSearch && i < lengthLine && isPresent)
					{
						isPresent = (search.charAt(j) == line.charAt(i));
						j++;
						i++;
					}
					if(isPresent)
					{
						amount++;
					}
				}
			}
		}
		return amount;
		
	}
	
	//Função que retorna o quanto um simbolo está presente
	public static int howMuchChar(char symbol, String line)
	{
		int amount = 0;
		if(line != null)
		{
			int length = line.length();
			for(int i = 0; i < length; i++)
			{
				if (line.charAt(i) == symbol)
				{
					amount++;
				}
			}
		}
		
		return(amount);
	}
	
	//Metodo que conta quantas vogais estão presentes na linha
	public static int howMuchVowels(String line)
	{
		int amount = 0;
		if (line != null)
		{
			int length = line.length();
			for(int i = 0; i < length; i++)
			{
				if (isVowels(line.charAt(i)))
				{
					amount++;
				}
			}
		}
		return amount;
	}
	
	//Metodo que conta quantas consoantes estão presentes na linha
	public static int howMuchConsonants(String line)
	{
		int amount = 0;
		if (line != null)
		{
			int length = line.length();
			for(int i = 0; i < length; i++)
			{
				if (isConsonant(line.charAt(i)))
				{
					amount++;
				}
			}
		}
		return amount;
	}
	
	/* 
		Strings
		Metodos que retornam boolean 
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
		return((('a' <= letter && letter <= 'z') || ('A' <= letter && letter <= 'Z')) && (letter != 'a' && 
		letter != 'e' && letter != 'i' && letter != 'o' && letter != 'u'&& letter != 'A' && letter != 'E' && 
		letter != 'I' && letter != 'O' && letter != 'U'));
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
	
	
	
}




/* ---------------------------------------------------------------------------------------------------------------------------*/


class TP01Q13{
    private static boolean and(String str, int index) {
        boolean resp = true;
        while (str.charAt(index) != ')' && resp) {
            if (str.charAt(index) == '0') {
                resp = false;
            }
            index++;
        }
        return resp;
    }

    private static String not(String str, int index) {
        String tmp = MyClass.subString(index - 1, str);
        while (str.charAt(index) != ')') {
            if (str.charAt(index) == '1') {
                tmp += '0';
            } else if (str.charAt(index) == '0') {
                tmp += '1';
            }
            index++;
        }
		tmp += MyClass.subString(index, str.length()-1, str);
        return tmp;
    }

    private static boolean or(String str, int index) {
        boolean resp = false;
        while (str.charAt(index) != ')' && resp) {
            if (str.charAt(index) == '1') {
                resp = true;
            }
            index++;
        }
        return resp;
    }

	//qual a posição da proxima expressão
    private static int indexNextExp(String str) {
        int length = str.length();
        int openParentesesCount = 0;
        int result = 0;

        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == '(') openParentesesCount++;
            else if (c == ')' && --openParentesesCount == 0) {
                result = i + 2;
                i = length;
            }
        }
        return result;
    }

	//Contador de quantos parenteses tem abertos
    private static int ParentesesCount(String str) {
        int length = str.length();
        int openParentesesCount = 0;
        int closedParentesesCount = 0;
        int result = 0;

        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == '(') openParentesesCount++;
            else if (c == ')') closedParentesesCount++;
            if (openParentesesCount > 1 && closedParentesesCount == openParentesesCount) {
                i = length;
            }
        }
        return openParentesesCount;
    }

    private static String reformulateString(String str) {
        String tmp = str;
		tmp = MyClass.replaceAll(" ", "", tmp);
        tmp = MyClass.replaceAll("and", "a", tmp);
        tmp = MyClass.replaceAll("or", "o", tmp);
        tmp = MyClass.replaceAll("not", "n", tmp);
        return tmp;
    }

	/*
    private static String algebraBooleana(String sentence, int index) {
        int nextParentesesCount = ParentesesCount(MyClass.subString(index, sentence));
        boolean hasMoreExpressions = (nextParentesesCount > 0);
		
		//System.out.println(sentence + ", " + sentence.length() + ", index =" + index + "parenteses= " + nextParentesesCount + ", " + hasMoreExpressions);
        if (index < sentence.length()) {
            if (hasMoreExpressions) {
                sentence = algebraBooleana(sentence, index + 1);
            }
            char c = sentence.charAt(index);
            if (c == 'a') {
                sentence = MyClass.subString(0, index, sentence) + (and(sentence, index) ? '1' : '0');
				System.out.println(MyClass.subString(index + 1, sentence));
                sentence = algebraBooleana(sentence, index - 1);
            } else if (c == 'o') {
                sentence = MyClass.subString(0, index, sentence) + (or(sentence, index) ? '1' : '0');
                System.out.println(MyClass.subString(index + 1, sentence));
				sentence = algebraBooleana(sentence, index - 1);
            } else if (c == 'n') {
                sentence = algebraBooleana(not(sentence, index), index - 1);
            }
        }
        return sentence;
    }
	*/
	
    private static String algebraBooleana(String sentence)
	{
		if (sentence.length() > 1 )
		{
			//NO
			sentence = MyClass.replaceAll("n(1)", "0", sentence);
			sentence = MyClass.replaceAll("n(0)", "1", sentence);
			//AND
			sentence = MyClass.replaceAll("a(1,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1)", "1", sentence);
			sentence = MyClass.replaceAll("a(0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,1,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,1,1)","0", sentence);
			sentence = MyClass.replaceAll("a(1,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1,1)", "1", sentence);
			sentence = MyClass.replaceAll("a(0,0,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,0,0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,0,1,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,0,1,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,1,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,1,0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(0,1,1,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,0,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,0,0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,0,1,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1,0,1)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1,1,0)", "0", sentence);
			sentence = MyClass.replaceAll("a(1,1,1,1)", "1", sentence);
			//Or
			sentence = MyClass.replaceAll("o(1,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,0)", "0", sentence);
			sentence = MyClass.replaceAll("o(0,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("o(0,0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,1,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,1,1)","1", sentence);
			sentence = MyClass.replaceAll("o(1,0,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,0,0,0)", "0", sentence);
			sentence = MyClass.replaceAll("o(0,0,0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,0,1,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,0,1,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,1,0,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,1,0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(0,1,1,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,0,0,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,0,0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,0,1,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1,0,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1,0,1)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1,1,0)", "1", sentence);
			sentence = MyClass.replaceAll("o(1,1,1,1)", "1", sentence);
			
			
			sentence = algebraBooleana(sentence);
			//System.out.println(sentence + ", " + sentence.length() + ", index =" + index);
			
		}
		
		return sentence;
	}
	


    public static void main(String Args[]) {

        String line = MyIO.readLine();
        while (line.charAt(0) != '0') {

            String expressionString = line;
            int n = MyClass.ctoi(line.charAt(0));
			expressionString = reformulateString(MyClass.subString(1, expressionString)); //Tirar a quantidade da string

            if (n > 0) {
                int[] array = new int[n];
				
				//Pegar os valores e substituir a letra pelo numero respectivo
                for (int i = 0, j = 0; i < n; i++, j++) {
                    array[i] = MyClass.ctoi(expressionString.charAt(j));
                    expressionString = MyClass.replaceAll(String.valueOf((char) ('A' + i)), String.valueOf(array[i]), expressionString);
                }
				expressionString = MyClass.subString(n,expressionString); //Tirar o valor das letras
              	System.out.println(algebraBooleana(expressionString));
				
			}
			line = MyIO.readLine();
        }
    }
}