import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server =null;
    Socket client =null;
    String queryString =null;
    String answerString =null;
    BufferedReader in;
    DataOutputStream out;

    public Socket Wait(){
        try{
            System.out.println("1 SERVER started ...");
            server = new ServerSocket(2000);
            client = server.accept();
            server.close();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.err.println("SERVER ERROR");
            System.exit(1);
        }
        return client;
    }
    public void talk(){
        try{
           System.out.println("3 HI client, type the string and i will make it all CAPS. wait ...");
           queryString = in.readLine();
           System.out.println("6 client string:"+queryString);
           answerString=queryString.toUpperCase();
           System.out.println("7 sending the CAPS string ...");
           out.writeBytes(answerString);
           System.out.println("9 SERVER finished.");
           client.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.err.println("CLIENT COMUNICATION ERROR");
            System.exit(1);
        }
    }
}
