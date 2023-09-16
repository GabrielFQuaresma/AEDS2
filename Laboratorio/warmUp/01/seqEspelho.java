import java.util.Scanner;

class seqEspelho{
	
	static String sequeEspelho(int startNum, int endNum){
		
		String tmp = "";
		if (startNum < endNum)
		{
			for(int i = startNum; i < endNum; i++)
				tmp += (char)(i + '0');
		}
		else
		{
			for(int i = startNum; i >= endNum; i--)
				tmp += (char)(i + '0');
		}
		return tmp;
	}
	public static void main (String Args[]){
		
		Scanner sc = new Scanner(System.in);
		do
		{
			int startNum = sc.nextInt();
			int endNum = sc.nextInt();
			
			System.out.println(sequeEspelho(startNum, endNum) + sequeEspelho(endNum, startNum));
		}while(sc.hasNextLine());
		sc.close();
	}
}