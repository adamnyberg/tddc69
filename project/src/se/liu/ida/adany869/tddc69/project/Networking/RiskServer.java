package se.liu.ida.adany869.tddc69.project.Networking;


import se.liu.ida.adany869.tddc69.project.Map;
import se.liu.ida.adany869.tddc69.project.RiskFrame;
import se.liu.ida.adany869.tddc69.project.RiskWorld;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;

public class RiskServer extends RiskNetwork{
    private static ServerSocket serverSocket;
    private static final int PORT = 6606;

    public static void main(String[] args) {
        run();
    }

    public static void run()
    {
        try
        {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(100000);
            Socket server = serverSocket.accept();
            out = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(server.getInputStream());
            Scanner stdIn = new Scanner(new InputStreamReader(System.in));
            System.out.println("asdasd");
            ArrayList<String> names= new ArrayList<>();
            names.add("Harald");
            names.add("Adam");
            RiskWorld risk = new RiskWorld(new Map(names));
            out.writeObject(risk);
            frame = new RiskFrame((RiskWorld)in.readObject());
            while ((risk = (RiskWorld)in.readObject()) != null) {
                frame.closeFrame();
                frame = new RiskFrame(risk);
            }
            server.close();
        }catch(SocketTimeoutException s)
        {
            System.out.println("Socket timed out!");
        }catch(IOException e)
        {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
