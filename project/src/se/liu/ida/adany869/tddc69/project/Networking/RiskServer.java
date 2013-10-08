package se.liu.ida.adany869.tddc69.project.Networking;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class RiskServer {
    private ServerSocket serverSocket;

    public RiskServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                String message;
                Socket server = serverSocket.accept();

                do{
                    DataInputStream in = new DataInputStream(server.getInputStream());
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    Scanner IOScanner = new Scanner(System.in);
                    message = in.readUTF();
                    System.out.println("Send message: ");
                    String input = IOScanner.nextLine();
                }while(!message.equals("quit"));
                /*
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());
                DataInputStream in =
                        new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out =
                        new DataOutputStream(server.getOutputStream());

                Scanner scanner = new Scanner( System.in );
                System.out.print( "Type some data for the program: " );
                String input = scanner.nextLine();
                while (!input.equals("quit")){
                    out.writeUTF(input);
                //System.out.println( "input = " + input );
                    out.writeUTF("Thank you for connecting to "
                        + server.getLocalSocketAddress() + "\nGoodbye!");

                }*/
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

    class ReadMessageThread implements Runnable{
        Socket server;
        ReadMessageThread(Socket server) {
            this.server = server;
        }

        @Override

        public void run() {
            String message;

            do{
                try{
                    DataInputStream in = new DataInputStream(server.getInputStream());
                    message = in.readUTF();
                    System.out.println("Other says: " + message);
                }
                catch (IOException e){
                    e.printStackTrace();
                    break;
                }
            }while(!message.equals("quit"));
        }
    }

    class WriteMessageThread implements Runnable{
        Socket server;
        WriteMessageThread(Socket server) {
            this.server = server;
        }

        @Override

        public void run() {
            String input;

            do{
                try{
                    DataOutputStream out = new DataOutputStream(server.getOutputStream());
                    Scanner IOScanner = new Scanner(System.in);
                    System.out.println("Send message: ");
                    input = IOScanner.nextLine();
                    out.writeUTF(input);
                }
                catch (IOException e){
                    e.printStackTrace();
                    break;
                }
            }while(!input.equals("quit"));
        }
    }
}
