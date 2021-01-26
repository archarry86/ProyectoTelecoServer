import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {
	
	static Socket socket;  
	static DataInputStream dataInputStream;  
	static DataOutputStream dataOutputStream;

	public static void main(String args[]){  
		System.out.println("INICIANDO SERVER");
		Runnable runnable = () -> listenToClient();
		Thread thread = new Thread(runnable);
		thread.start();
		
		listenToClient();			 
			  
		}
	
	private static void listenToClient () {
		try {
			ServerSocket serverSocket = new ServerSocket(3333);  
			socket = serverSocket.accept();  
			dataInputStream = new DataInputStream(socket.getInputStream());  
			dataOutputStream = new DataOutputStream(socket.getOutputStream());  
			//BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));  
			 
			
			dataInputStream.close();  
			socket.close();  
			serverSocket.close();
			
			/*
			String str = "", str2 = "";  
			while(!str.equals("stop")){  
				str = dataInputStream.readUTF();  
				System.out.println("client says: " + str);  
				//str2 = bufferedReader.readLine();  
				dataOutputStream.writeUTF(str2);  
				dataOutputStream.flush();  
			}  
			*/
			while(true) {
				System.out.println("Ejecuntado....");
			}
			
			//dataInputStream.close();  
			//socket.close();  
			//serverSocket.close();
		} catch (Exception e) {
			System.out.println("e : " + e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e.getLocalizedMessage());
		} finally {
			System.out.println("FIN ");
		}
	}
	
}
