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

public class RiskClient{
    private static final int PORT = 6606;
    protected static ObjectOutputStream out;
    protected static RiskFrame frame;
    private static HashMap<String, Socket> playerToSocket = new HashMap<>();
    /*private static ObjectOutputStream out;
    private static RiskFrame frame;*/
    public static void main(String[] args) {
        Socket socket = null;
        String myName;

        myName = StartMenu.enterYourNamePane();

        try{
            //Try connected to a server
            socket = new Socket(args[0], PORT);
            System.out.println("Successfully connected to server");
            DataOutputStream nameOut = new DataOutputStream(socket.getOutputStream());
            nameOut.writeUTF(myName);
            ObjectInputStream in;

            RiskWorld risk;
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
            System.out.println("Closed");
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
