package se.liu.ida.adany869.tddc69.project.Networking;

import se.liu.ida.adany869.tddc69.project.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class RiskServer{
    private static final RiskServer riskServer = new RiskServer();
    private int port = 6606;
    protected ObjectOutputStream out;
    protected ServerSocket serverSocket;
    protected RiskFrame frame;
    private HashMap<String, Socket> playerToSocket = new HashMap<>();
    private HashMap<String, ClientSocket> getClientSocketByName = new HashMap<>();
    private ArrayList<ClientSocket> clients = new ArrayList<>();
    /*private static ObjectOutputStream out;
    private static RiskFrame frame;*/

    public static RiskServer getInstance(){
        return riskServer;
    }

    public void setPort(int port){
        this.port = port;
    }

    public void run(){
        int numberOfPlayers;

        System.out.println("Setup server");
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(100000);

            ArrayList<String> names = new ArrayList<>();
            numberOfPlayers = StartMenu.numberOfPlayersOptionPane();
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Looking for client");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                DataInputStream nameIn = new DataInputStream(socket.getInputStream());
                String name = nameIn.readUTF();
                System.out.println(name + " connected");
                ClientSocket clientSocket = new ClientSocket(socket, name);
                System.out.println(4);
                Thread thread = new Thread(clientSocket);
                System.out.println(5);
                thread.start();
                System.out.println("Running thread");
                getClientSocketByName.put(name, clientSocket);
                names.add(name);
                playerToSocket.put(name, socket);
                clients.add(clientSocket);
            }
            RiskWorld risk = new RiskWorld(new Map(names));

            System.out.println(risk.getActivePlayer().getName());
            ClientSocket currentSocket = getClientSocketByName.get(risk.getActivePlayer().getName());
            currentSocket.sendTo(risk);
            while(!getClientSocketByName.isEmpty()){
                //Do nothing
            }

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


    public void sendRisk(RiskWorld risk){
        ClientSocket clientSocket = getClientSocketByName.get(risk.getActivePlayer().getName());
        clientSocket.sendTo(risk);
        /*try{
                System.out.println("out == null. setting out");
                out = new ObjectOutputStream(playerToSocket.get(
                        risk.getActivePlayer().getName()).getOutputStream());

            out.writeObject(risk);
            frame.setEnabled(false);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        }*/ //catch (){
        //e.printStackTrace();
        //}
        //}
    }
    private class ClientSocket implements Runnable{
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private String name;
        private ClientSocket(Socket socket, String name) {
            this.name = name;
            try {
                this.in = new ObjectInputStream(socket.getInputStream());
                this.out = new ObjectOutputStream(socket.getOutputStream());
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        private void sendTo(RiskWorld risk){
            try {
            out.writeObject(risk);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            RiskWorld risk;
            try {
                while ((risk = (RiskWorld)in.readObject()) != null){
                    ClientSocket nextSocket = getClientSocketByName.get(risk.getActivePlayer().getName());
                    new RiskFrame(risk);
                    while(risk.getActivePlayer().getName().equals(this.name)){
                        //Do nothing
                    }
                    nextSocket.sendTo(risk);
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
