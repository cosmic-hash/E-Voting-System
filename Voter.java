import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
class Voter
{
	public String linef;
	//public String mlinef;
	public String data;
	public int voterList(String vID) throws FileNotFoundException
	{
		//URL path = Voter.class.getResource("voterlist.txt");
    	Scanner scanner = new Scanner(new File("voterlist.txt"));
    	int f = -1;
    	while (scanner.hasNextLine())
    	{
    	    String line = scanner.nextLine();
    	    
    	    String[] arr = line.split("\\s");
    	    if( arr[0].equals(vID)==true )
    	    {
    	        if( arr[2].equals("0")==false )
    	        {
    	        	f=0;
    	        	data=arr[2];
    	        }
    	        else
    	        {
    	            f = Integer.parseInt(arr[1]);
    	            //modifyFile("voterlist.txt",line,mline);
    	            linef=line;
    	            //mlinef=mline;
    	            
    	        }
    	        break;
    	    }
    	}
    	return f;
	}	
	public void modifyFile(String filePath, String oldString)
    {
   	    File fileToBeModified = new File(filePath);
   	    Calendar cal = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SS");
    	String strDate = sdf.format(cal.getTime());
   	    String mline = linef.substring(0,15)+" "+strDate;
   	    String oldContent = "";
   	    BufferedReader reader = null;
   	    FileWriter writer = null;
   	    try
        {
   	        reader = new BufferedReader(new FileReader(fileToBeModified));
    	                //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, mline);
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();               
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    /*public static void main(String args[]) throws FileNotFoundException
    {
    	Scanner s=new Scanner(System.in);
    	String inp = s.nextLine();
    	int v=voterList(inp);
    	if(v==0)
    	{
    		System.out.println("found record");
    	}
    	else if(v==-1)
    	{
    		System.out.println("record not found");
    	}
    	else
    	{
    		System.out.println("already voted " + v);
    	}
    }*/
}
