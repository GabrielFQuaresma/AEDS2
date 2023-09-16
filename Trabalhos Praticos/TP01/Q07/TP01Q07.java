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
						if(search.charAt(j) != line.charAt(i))
						{
							isPresent = false;
						}
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

class TP01Q07{
	
	
	// Função que retorna se o char é consoante minúscula ou não
	public static boolean isConsonantLower(char letter) {
    return (('a' < letter && letter <= 'z') && (letter != 'a' &&
            letter != 'e' && letter != 'i' && letter != 'o' && letter != 'u'
            && letter != '\u00E1' && letter != '\u00FA' && letter != '\u00E9' && letter != '\u00ED' && letter != '\u00F3' && letter != '\u00E3' && letter != '\u00F5' && letter != '\u00E2' && letter != '\u00EA' && letter != '\u00EE' && letter != '\u00F4' && letter != '\u00FB' && letter != '\u00E0' && letter != '\u00E8' && letter != '\u00EC' && letter != '\u00F2' && letter != '\u00F9'));
	}

	
	//Metodo que conta quantas consoantes minusculas estão presentes na linha
	public static int howMuchConsonantsLower(String line)
	{
		
		
		int amount = 0;
		if (line != null)
		{
			int length = line.length();
			for(int i = 0; i < length; i++)
			{
				if (isConsonantLower(line.charAt(i)))
				{
					amount++;
				}
			}
		}
		return amount;
	}
	
	 public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } 

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }
	
	public static void main(String[] args) {
		String pageName = MyIO.readLine("");
		String address = MyIO.readString("");
		String html;
		
		//letras acentuadas
		char a_acute = (char) 225; // á
		char e_acute = (char) 233; // é
		char i_acute = (char) 237; // í
		char o_acute = (char) 243; // ó
		char u_acute = (char) 250; // ú
		char a_grave = (char) 224; // à
		char e_grave = (char) 232; // è
		char i_grave = (char) 236; // ì
		char o_grave = (char) 242; // ò
		char u_grave = (char) 249; // ù
		char a_tilde = (char) 227; // ã
		char o_tilde = (char) 245; // õ
		char a_circumflex = (char) 226; // â
		char e_circumflex = (char) 234; // ê
		char i_circumflex = (char) 238; // î
		char o_circumflex = (char) 244; // ô
		char u_circumflex = (char) 251; // û

		
		
		
		while(!(MyClass.equals(pageName, "FIM")))
		{
			html = getHtml(address);
			System.out.println("a(" + MyClass.howMuchChar('a', html) + ") e(" + MyClass.howMuchChar('e', html) + ") i(" + MyClass.howMuchChar('i', html) + ") o(" + MyClass.howMuchChar('o', html) + ") u(" + MyClass.howMuchChar('u', html) + ") " + a_acute + "(" + MyClass.howMuchChar(a_acute, html) + ") " + e_acute + "(" + MyClass.howMuchChar(e_acute, html) + ") " + i_acute + "(" + MyClass.howMuchChar(i_acute, html) + ") " + o_acute + "(" + MyClass.howMuchChar(o_acute, html) + ") " + u_acute + "(" + MyClass.howMuchChar(u_acute, html) + ") " + a_grave + "(" + MyClass.howMuchChar(a_grave, html) + ") " + e_grave + "(" + MyClass.howMuchChar(e_grave, html) + ") " + i_grave + "(" + MyClass.howMuchChar(i_grave, html) + ") " + o_grave + "(" + MyClass.howMuchChar(o_grave, html) + ") " + u_grave + "(" + MyClass.howMuchChar(u_grave, html) + ") " + a_tilde + "(" + MyClass.howMuchChar(a_tilde, html) + ") " + o_tilde + "(" + MyClass.howMuchChar(o_tilde, html) + ") " + a_circumflex + "(" + MyClass.howMuchChar(a_circumflex, html) + ") " + e_circumflex + "(" + MyClass.howMuchChar(e_circumflex, html) + ") " + i_circumflex + "(" + MyClass.howMuchChar(i_circumflex, html) + ") " + o_circumflex + "(" + MyClass.howMuchChar(o_circumflex, html) + ") " + u_circumflex + "(" + MyClass.howMuchChar(u_circumflex, html) + ") consoante(" + howMuchConsonantsLower(html)  + ") <br>(" + MyClass.howMuchString("<br>", html) + ") <table>(" + MyClass.howMuchString("<table>", html) + ") " + pageName);

			
			pageName = MyIO.readLine("");
			if (!(MyClass.equals(pageName, "FIM"))){
				address = MyIO.readString("");
			}

		}

   }
	
}
