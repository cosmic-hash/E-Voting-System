import java.util.Scanner;
import java.util.Random;
import java.util.*;
class checker
{
	//static long x=123;
	static long g=9381;
	static long p=19793393;
	static long compute(long ha,long ga,long xa,long pa)
	{
		long pro=1;
		for(int i=0;i<xa;i++)
		{
			pro=pro*ga;
			if(pro>pa)
				pro=pro%pa;
		}
		pro=(ha%pa)*pro;
		return pro%pa;
	}
	static int flag=1;
	static int verifyTransaction(long y)
	{
		Random rand = new Random();
		Scanner s= new Scanner(System.in);
		HashSet<Long> hs= new HashSet<Long>();
		System.out.println("remember g = 9381 and p = 19793393");
		for(int i=0;i<5;i++)
		{
			System.out.println("enter h = g^r mod p");
			long h=Long.parseLong(s.nextLine());
			if(hs.contains(h)==true)
			{
				System.out.println("You already used this h-value!!! Pls try using another h-value");
				i--;
			}
			else
			{
				hs.add(h);
				int b=(rand.nextInt(1000))%2;
				System.out.println("b value : "+b+"\nenter the value r+bx mod (p-1) :");
				long sa=Long.parseLong(s.nextLine());
				if(compute(1,g,sa,p)!=compute(h,y,b,p))
				{
					flag=0;
					System.out.println("Verification failed!!!");
					break;
				}
			}
		}
		if(flag==1)
			System.out.println("Verification successful (:");
		return flag;
	}
}
