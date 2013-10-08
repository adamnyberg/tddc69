package se.liu.ida.adany869.tddc69.project.Networking;


import java.io.IOException;
import java.net.ServerSocket;

public class RiskServer {
    private ServerSocket serverSocket;

    public RiskServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }
}
