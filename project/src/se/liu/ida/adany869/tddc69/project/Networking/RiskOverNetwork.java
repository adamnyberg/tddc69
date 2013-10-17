package se.liu.ida.adany869.tddc69.project.Networking;

import se.liu.ida.adany869.tddc69.project.*;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RiskOverNetwork{
    private static final int PORT = 6606;
    protected static ObjectOutputStream out;
    protected static RiskFrame frame;
    private static HashMap<String, Socket> playerToSocket = new HashMap<>();
    /*private static ObjectOutputStream out;
    private static RiskFrame frame;*/
    public static void main(String[] args) {
        Socket socket = null;
        boolean isServer = false;
        ArrayList<Socket> clients;
        ArrayList<String> names = new ArrayList<>();
        String myName;
        int numberOfPlayers;

        myName = StartMenu.enterYourNamePane();

        try{
            //Try connected to a server
            socket = new Socket(args[0], PORT);
            System.out.println("Successfully connected to server");
            DataOutputStream nameOut = new DataOutputStream(socket.getOutputStream());
            nameOut.writeUTF(myName);
        }catch (UnknownHostException e){
            System.out.println("Unknown host");
            System.exit(0);
        }catch (IOException e){
            //Server not setup. Setup server
            System.out.println("Setup server");
            clients = new ArrayList<>();
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                serverSocket.setSoTimeout(100000);
                isServer = true;

                names.add(myName);
                numberOfPlayers = StartMenu.numberOfPlayersOptionPane();
                for (int i = 0; i < numberOfPlayers-1; i++) {
                    System.out.println("Looking for client");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected");
                    clients.add(clientSocket);
                    DataInputStream nameIn = new DataInputStream(clientSocket.getInputStream());
                    String name = nameIn.readUTF();
                    names.add(name);
                    playerToSocket.put(name, clientSocket);
                }
            } catch (Exception e1){
                e1.printStackTrace();
                System.exit(0);
            }
        }
        try{
            ObjectInputStream in;

            RiskWorld risk;
            if (isServer){
                risk = new RiskWorld(new Map(names));
                frame = new RiskFrame(risk);
                while (risk.getActivePlayer().getName().equals(myName)){
                }
                if (!risk.getActivePlayer().getName().equals(myName)){
                    frame.setEnabled(false);
                }
                System.out.println(risk.getActivePlayer().getName());
                Socket currentSocket = playerToSocket.get(risk.getActivePlayer().getName());
                out = new ObjectOutputStream(currentSocket.getOutputStream());
                in = new ObjectInputStream(currentSocket.getInputStream());
                out.writeObject(risk);

                while ((risk = (RiskWorld)in.readObject()) != null){
                    System.out.println("Server received risk");
                    if (!risk.getActivePlayer().getName().equals(myName)){
                        frame.setEnabled(false);
                        currentSocket = playerToSocket.get(risk.getActivePlayer().getName());
                        out = new ObjectOutputStream(currentSocket.getOutputStream());
                        in = new ObjectInputStream(currentSocket.getInputStream());
                        out.writeObject(risk);
                    }
                    else {
                        frame.closeFrame();
                        frame = new RiskFrame(risk);
                        while (risk.getActivePlayer().getName().equals(myName)){
                        }
                    }
                }
            }
            else{
                out = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("out was set");
                in = new ObjectInputStream(socket.getInputStream());
                out.flush();
                risk = (RiskWorld)in.readObject();
                frame = new RiskFrame(risk);
                while ((risk = (RiskWorld)in.readObject()) != null) {
                    System.out.println("Client received risk");
                    frame.closeFrame();
                    frame = new RiskFrame(risk);
                }
            }

            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void sendRisk(RiskWorld risk){
        //if (out != null){
            try{
                if (out == null){
                    System.out.println("out == null. setting out");
                    out = new ObjectOutputStream(playerToSocket.get(
                        risk.getActivePlayer().getName()).getOutputStream());
                }
                out.writeObject(risk);
                frame.setEnabled(false);
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host ");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to ");
                System.exit(1);
            } //catch (){
                //e.printStackTrace();
            //}
        //}
    }
}
