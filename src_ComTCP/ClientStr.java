import java.io.*;
import java.net.*;

public class ClientStr {
    String serverName = "127.0.0.1";
    int serverPort = 2000;
    Socket mySocket;
    BufferedReader tastiera;
    String userString;
    String serverString;
    DataOutputStream out;
    BufferedReader in;

    public Socket connect(){
        System.out.println("2 CLIENT started ...");
        try{
            tastiera = new BufferedReader(new InputStreamReader((System.in)));
            mySocket = new Socket(serverName,serverPort);
            out = new DataOutputStream(mySocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        }
        catch (UnknownHostException e){
            System.err.println("UNKNOWN HOST");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.err.println("CONNECTION ERROR");
            System.exit(1);
        }
        return mySocket;
    }

    public void talk(){
        try{
            System.out.println("4 ... type the string message:"+'\n');
            userString= tastiera.readLine();
            System.out.println("5 ... sending the message ...");
            out.writeBytes( userString+'\n');
            serverString=in.readLine();
            System.out.println("8 ... server Answer:"+'\n'+serverString);
            System.out.println("9 CLIENT close connection");
            mySocket.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.err.println("COMUNICATION SERVER ERROR");
            System.exit(1);
        }
    }
}
