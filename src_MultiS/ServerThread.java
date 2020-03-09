import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
	ServerSocket server = null;
	Socket client = null;
	String stringaR = null;
	String stringaM = null;
	BufferedReader in;
	DataOutputStream out;
	
	public ServerThread (Socket socket) {
		this.client = socket;
	}
	
	public void run() {
		try {
			comunica();
		}catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public void comunica() throws Exception{
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new DataOutputStream(client.getOutputStream());
		for(;;) {
			stringaR =in.readLine();
			if(stringaR == null || stringaR.equals("FINE")) {
				out.writeBytes(stringaR + " (->server in chiusura...)" + "\n");
				System.out.println("Echo sul server in chiusura : " + stringaR);
				break;
			}else {
				out.writeBytes(stringaR + " (ricevuta e ritrasmessa" + "\n");
				System.out.println("6 Echo sul server :" + stringaR);
			}
		}
		out.close();
		in.close();
		System.out.println("9 Chiusura socket " + client);
		client.close();
	}

}
