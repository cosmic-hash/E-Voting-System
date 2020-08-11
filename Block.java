//package noobchain;

import java.util.Date;
import java.io.*;
public class Block
{
	
	public String hash;
	public String previousHash; 
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.  
	public Block(String data,String previousHash )
	{
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	public Block(String chash,String phash,String data,long time,int nonce)
	{
		this.data = data;
		this.previousHash = phash;
		this.timeStamp = time;
		this.hash = chash;
		this.nonce = nonce;
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
	public void printer()
	{
		System.out.println("		Hash : "+hash);
		System.out.println("		Previous Hash : "+previousHash);
		System.out.println("		Data : "+data);
		System.out.println("		Time Stamp : "+timeStamp);
		//System.out.println("		Nonce : "+nonce);
	}
	public void addBlock2File() throws IOException
	{
    	FileWriter fw = null;
    	BufferedWriter bw = null;
    	PrintWriter pw = null;
    	try 
    	{ 
    	    fw = new FileWriter("database.txt", true);
    	    bw = new BufferedWriter(fw);
    	    pw = new PrintWriter(bw);
    	    pw.println( hash + " " + previousHash + " " + data + " " + timeStamp + " " + nonce);
    	    pw.flush();
    	} 
    	finally 
    	{ 
    	    try 
    	    { 
    	        pw.close();
    	        bw.close();
    	        fw.close();
    	    } 
    	    catch (IOException io) {}
    	}
	}	
	public int datavalue()
	{
		return Integer.parseInt(data);
	}
}
