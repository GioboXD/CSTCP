import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	String nomeServer = "127.0.0.1";
    int portaServer = 2001;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
	public void talk() {
		for(;;) {
			try {
				System.out.println("4... utente, inserisci la stringa al server:");
				stringaUtente = tastiera.readLine();
				//la spedizione al server
				System.out.println("5... invio la stringa al serever e attendo...");
				outVersoServer.writeBytes(stringaUtente+"\n");
				stringaRicevutaDalServer=inDalServer.readLine();
				System.out.println("7 ... risposta dal server"+"\n"+ stringaRicevutaDalServer);
				if (stringaUtente.equals("FINE")) {
					System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
					miosocket.close();
					break;
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
				System.err.println("Errore durante la comunicazione con il server!");
				System.exit(1);
				}
			}
		
		}
	public Socket connetti() {
		System.out.println("2 CLIENT partito in esecuzione ...");
		try {
			tastiera = new BufferedReader(new InputStreamReader(System.in));
			miosocket = new Socket(nomeServer, portaServer);
			outVersoServer = new DataOutputStream(miosocket.getOutputStream());
			inDalServer = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
		}catch(UnknownHostException e) {
			System.err.println("Host sconosciuto");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.err.println("Errore durante la connessione!");
			System.exit(1);
		}
		return miosocket;
	}

}
