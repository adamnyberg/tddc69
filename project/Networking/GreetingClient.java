package se.liu.ida.adany869.tddc69.project.Networking;
// File Name GreetingClient.java

import java.net.*;
import java.io.*;

public class GreetingClient
{
    public static void main(String [] args)
    {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        String message = "";
            try
            {
                //System.out.println("Connecting to " + serverName
                //        + " on port " + port);
                Socket client = new Socket(serverName, port);
                //System.out.println("Just connected to "
                //        + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out =
                        new DataOutputStream(outToServer);

                //out.writeUTF("Hello from "
                //        + client.getLocalSocketAddress());
                InputStream inFromServer = client.getInputStream();
                do{
                    DataInputStream in = new DataInputStream(inFromServer);
                    message = in.readUTF();
                    System.out.println("Server says " + message);
                }while (!message.equals("quit"));
                client.close();
        } catch (ConnectException e){
                message = "quit";
        } catch(IOException e)
        {
            e.printStackTrace();
        }


    }
}