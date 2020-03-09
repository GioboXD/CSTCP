import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
	public void connetti() {
		try {
			ServerSocket SS = new ServerSocket(2001);
			for(;;) {
				System.out.println("1 Server in attesa...");
				Socket socket = SS.accept();
				System.out.println("3 Server socket " + socket);
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Errore durante l'istanza del server!");
			System.exit(1);
		}
	}
}
