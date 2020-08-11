import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.io.*;
public class BlockChain
{
	
	public static ArrayList<Block> blockchain;
	public static int difficulty = 5;
	public static Voter v = new Voter();
	public static void main(String[] args) throws IOException
	{	
		ArrayList<Block> b = file2arraylist();
		blockchain=b;
		Scanner scan = new Scanner(System.in);
		int flag;
		System.out.println("Please enter your Voter ID");
		String voterid = scan.nextLine();
		
		int ivote = v.voterList(voterid);
		if(ivote == -1)
		{
			System.out.println("VoterID not found");
			return;
		}
		else if(ivote == 0)
		{
			System.out.println("Your vote is already casted on "+v.data);//+new Date().getTime(v.data));
			return;
		}
		else
			flag=checker.verifyTransaction(ivote);
		if(flag == 0)
			return;
		// after verification ask for vote and add block
		System.out.println("Welcome, Please cast your Vote\npress 1 for Bharatiya Janata Party\npress 2 for Indian National Congress\npress 3 for Aam Admi Party\npress 4 for Communist Party of India");
		String data = scan.nextLine();
		
		System.out.println("Trying to Mine block... ");
		addBlock(createBlock(data, blockchain.get(blockchain.size()-1).hash));
		v.modifyFile("voterlist.txt",v.linef);
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		System.out.println("\nThe block chain: ");
		printBlockChain();
	}
	
	
	public static Boolean isChainValid() 
	{
		Block currentBlock; 
		Block previousBlock;
		 
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		int arr[] = new int[4];
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) 
		{
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) )
			{
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) 
			{
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) 
			{
				System.out.println("This block hasn't been mined");
				return false;
			}
			arr[currentBlock.datavalue()-1]++;
		}
		System.out.println("Voting Results so far:\nBharatiya Janata Party:   "+arr[0]+"\nIndian National Congress: "+arr[1]+"\nAam Admi Party:           "+arr[2]+"\nCommunist Party of India: "+arr[3]);
		
		return true;
	}
	
	public static void addBlock(Block newBlock) throws IOException
	{
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
		newBlock.addBlock2File();
	}
	public static void printBlockChain()
	{
		int size=blockchain.size();
		for(int i=0;i<size;i++)
		{
			System.out.println("	Block "+(i+1)+"\n	{");
			blockchain.get(i).printer();
			System.out.println("	}");
		}
	}
	public static Block createBlock(String data,String hash)
	{
		Block temp=new Block(data,hash);
		return temp;
	}	
	public static ArrayList<Block> file2arraylist() throws FileNotFoundException
	{
	    ArrayList<Block> Blockchain = new ArrayList<Block>();
	    Scanner scanner = new Scanner(new File("database.txt"));
	    
	    while (scanner.hasNextLine())
	    {
	        String line = scanner.nextLine();
	        
	        String[] arr = line.split("\\s");
	        Block temp = new Block(arr[0],arr[1],arr[2],Long.parseLong(arr[3]),Integer.parseInt(arr[4]));
	        Blockchain.add(temp);
	    }
	    return Blockchain;
	}	
}
