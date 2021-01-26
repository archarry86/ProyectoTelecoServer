import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {
	
	static Socket socket;  
	static ServerSocket serverSocket;
	static DataInputStream dataInputStream;  
	static DataOutputStream dataOutputStream;

	public static void main(String args[]){  
		System.out.println("INICIANDO SERVER");
		try {
			serverSocket = new ServerSocket(3333);  
			socket = serverSocket.accept();  
		} catch (Exception e) {
			System.out.println(e);
		}
		Runnable runnable = () -> listenToClient();
		Thread thread = new Thread(runnable);
		thread.start();		
		}
	
	private static void listenToClient () {
		try {
			
			dataInputStream = new DataInputStream(socket.getInputStream());  
			dataOutputStream = new DataOutputStream(socket.getOutputStream());  

			String message = "";
			while(!message.equals("EXIT")) {
				System.out.println("Ejecutando....");
				message = dataInputStream.readUTF();
				System.out.println("Message from Client : "+ message);
				executeMessage(message);				
			}
				
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
			
		} catch (Exception e) {
			System.out.println("e : " + e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e.getLocalizedMessage());
		} finally {
			System.out.println("FIN ");
			try {
				dataInputStream.close();  
				socket.close();  
				serverSocket.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	private static void executeMessage(String message) {
		switch (message) {
		case "MONITOR_DISK":
			getDiskData();
			break;
		case "MONITOR_MEMORY_JVM":
			getRamMemoryData();
			break;

		default:
			break;
		}
	}
	
	private static void getDiskData() {
		File cDrive = new File("C:");
		System.out.println(String.format("Total space: %.2f GB",
		  (double)cDrive.getTotalSpace() /1073741824));
		System.out.println(String.format("Free space: %.2f GB", 
		  (double)cDrive.getFreeSpace() /1073741824));
		System.out.println(String.format("Usable space: %.2f GB", 
		  (double)cDrive.getUsableSpace() /1073741824));
	}
	
	private static void getRamMemoryData() {
		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		System.out.println(String.format("Initial memory: %.2f GB", 
		  (double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824));
		System.out.println(String.format("Used heap memory: %.2f GB", 
		  (double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824));
		System.out.println(String.format("Max heap memory: %.2f GB", 
		  (double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824));
		System.out.println(String.format("Committed memory: %.2f GB", 
		  (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824));
	}
	
	
	//https://www.baeldung.com/java-metrics
}
