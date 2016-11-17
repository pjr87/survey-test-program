import java.util.InputMismatchException;
import java.util.Scanner;

public class TextIO extends InputOutput{
	
	protected int getInput(){
		int choice = 0;
		Scanner reader = null;
		try{
			reader = new Scanner(System.in); 
			choice = reader.nextInt(); 
		}
		catch( InputMismatchException e )
		{
			Output("Error, Please input a number");
			reader.next();
			choice = 0;
		}
		return choice;
	}
	
	protected String getInputString(){
		String choice = "";
		Scanner reader = null;
		try{
			reader = new Scanner(System.in); 
			choice = reader.nextLine();
		}
		catch( InputMismatchException e )
		{
			Output("Error, Please input a String");
			reader.next();
			choice = "";
		}
		return choice;
	}
	
	protected boolean interpretResponse(String Response)
	{
		boolean tmp = false;
		
		if( Response.equals("Yes") || Response.equals("yes") || Response.equals("Y") || Response.equals("y"))
		{
			tmp = true;
		}
		
		return tmp;
	}
	
	protected void Output(String output)
	{
		System.out.println(output);
	}
	
	protected void Output1(String output)
	{
		System.out.print(output);
	}
}