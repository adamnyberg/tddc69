package se.liu.ida.adany869.tddc69.project.Networking;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GreetingServer extends Thread
{
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                //System.out.println(in.readUTF());
                //DataOutputStream out =
                //    new DataOutputStream(server.getOutputStream());
                String message;




                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());
                String input = "";
                do{
                    try{
                        Scanner scanner = new Scanner( System.in );
                        System.out.print("Type some data for the program: ");
                        input = scanner.nextLine();
                        sendMessage(input, out);
                        //message = (String)in.readUTF();
                        //System.out.println("client>" + message);
                        //if (message.equals("bye")){
                          //  sendMessage("bye", out);
                        //}
                    }
                    finally {

                    }
                }while(!input.equals("bye"));

                    //System.out.println( "input = " + input );
                //out.writeUTF("Thank you for connecting to "
                            //+ server.getLocalSocketAddress() + "\nGoodbye!");

                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }

    private void sendMessage(String msg, DataOutputStream out)
    {
        try{
            out.writeUTF(msg);
            out.flush();
            System.out.println("server>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    public static void main(String [] args)
    {
        int port = Integer.parseInt(args[0]);
        try
        {
            Thread t = new GreetingServer(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}