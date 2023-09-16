import java.util.Scanner;

class questao01{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		double number = 0.0;

		while(sc.hasNext())
		{
			number = sc.nextDouble();
			int integerPart = (int)(number);
			double cutOff = sc.nextDouble();

			number -= integerPart;
			if(number > cutOff)
			{
				System.out.println(integerPart + 1);
			}
			else
			{
				System.out.println(integerPart);
			}
		}
	}
	
}