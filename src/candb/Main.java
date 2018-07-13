package candb;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
	    

	public static void main(String[] args)  {
		new CandB();
		Socket soc;
		try {
			
			
			soc= new Socket(InetAddress.getByName("redhat-pc"),5000);
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
				
		
	}
}
