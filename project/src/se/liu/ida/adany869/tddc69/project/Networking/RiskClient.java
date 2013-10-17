package se.liu.ida.adany869.tddc69.project.Networking;

import se.liu.ida.adany869.tddc69.project.Map;
import se.liu.ida.adany869.tddc69.project.RiskContainer;
import se.liu.ida.adany869.tddc69.project.RiskFrame;
import se.liu.ida.adany869.tddc69.project.RiskWorld;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RiskClient extends RiskNetwork{
    private static final int PORT = 6606;
    /*private static ObjectOutputStream out;
    private static RiskFrame frame;*/
    public static void main(String[] args) {
        Socket socket = null;
        try{
            socket = new Socket("localHost", PORT);
        }catch (UnknownHostException e){
            System.out.println("Unknown host");
            System.exit(0);
        }catch (IOException e){
            //Server not setup. Setup server
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                serverSocket.setSoTimeout(100000);
                socket = serverSocket.accept();
            } catch (Exception e1){
                e.printStackTrace();
            }
        }
        try{
            System.out.println("asd");
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(2);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.flush();
            System.out.println(2.5);
            //ObjectInputStream in =
            //        new ObjectInputStream(is);
            System.out.println(3);
            Scanner stdIn =
                    new Scanner(
                            new InputStreamReader(System.in));
            System.out.println(4);
            String userInput;

            RiskWorld risk = (RiskWorld)in.readObject();
            frame = new RiskFrame(risk);
            while ((risk = (RiskWorld)in.readObject()) != null) {
                frame.closeFrame();
                frame = new RiskFrame(risk);
            }

            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    /*public static void sendRisk(RiskWorld risk){
        try{
            out.writeObject(risk);
            frame.closeFrame();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to ");
            System.exit(1);
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
