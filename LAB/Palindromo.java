
public class Palindromo{
	
	public static boolean itsPalindromo(String linha){
		return (itsPalindromo(linha, 0, linha.length() - 1));
	}	
	
	private static boolean itsPalindromo (String linha, int index, int end){
		boolean resp = true;
		if (index >= linha.length()/2){
			resp = true;
		}
		else if (linha.charAt(index) != linha.charAt(end)){
			resp = false;
		}
		else{
			itsPalindromo(linha, index + 1, end - 1);
		}
		return resp;
	}
	public static void main (String args[])
	{
		String linha = MyIO.readLine();
		while(!linha.equals("FIM")){
			System.out.println(itsPalindromo(linha) ? "SIM" : "NAO");
			 linha = MyIO.readLine("");
		}
	}
}