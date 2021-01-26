import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			ServerSocket ss = new ServerSocket(3333);  
			
			Socket s = ss.accept();  				
		
			DataInputStream din=new DataInputStream(s.getInputStream());  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  				
			
			String str="",str2="";  
			while(!str.equals("stop")){  
			str=din.readUTF();  
			System.out.println("client says: "+str);  
			str2=br.readLine();  
			dout.writeUTF(str2);  
			dout.flush();  
			}  
			din.close();  
			s.close();  
			ss.close();  
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			System.out.println("exception here");
		}
		
		finally {
			// TODO: handle finally clause
			System.out.println("Finally");
		}
	}

}
