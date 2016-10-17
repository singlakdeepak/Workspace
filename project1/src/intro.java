import java.util.Scanner;
public class intro
{		
	String converting (String a)
	{
		if (a=="0")
			return "0000";
		else if (a=="1")
			return "0001";
		else if (a=="2")
			return "0010";
		else if (a=="3")
			return "0011";
		else if (a=="4")
			return "0100";
		else if (a=="5")
			return "0101";
		else if (a=="6")
			return "0110";
		else if (a=="7")
			return "0111";
		else if (a=="8")
			return "1000";
		else if (a=="9")
			return "1001";
		else if (a=="A")
			return "1010";
		else if (a=="B")
			return "1011";
		else if (a=="C")
			return "1100";
		else if (a=="D")
			return "1101";
		else if (a=="E")
			return "1110";
		else if (a=="F")
			return "1111";
		return a;
		
	}
	public static void main (String args[])
	{ 	
		Scanner s = new Scanner(System.in);
	    System.out.println("The no is ");
	    String hex= s.nextLine();
	    String dec = "";
	    intro cha = new intro();
	    for (int i=0; i< hex. length (); i++)
	    {
	    	dec= dec + cha.converting("A");
	    }
	    System.out.println(dec);
	   
	    }
}    
	