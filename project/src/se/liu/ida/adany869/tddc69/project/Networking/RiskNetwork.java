package se.liu.ida.adany869.tddc69.project.Networking;

import se.liu.ida.adany869.tddc69.project.RiskFrame;
import se.liu.ida.adany869.tddc69.project.RiskWorld;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

public class RiskNetwork {
    protected static ObjectOutputStream out;
    protected static RiskFrame frame;
    public static void sendRisk(RiskWorld risk){
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
    }
}
