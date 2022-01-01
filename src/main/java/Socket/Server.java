package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    public static void start(int port) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started ....");
            while (true) {
                Socket clientSocket = serverSocket.accept();  //server accept the client connection request
                ServerThread sct = new ServerThread(clientSocket); //send  the request to a separate thread
                sct.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Server.start(6000);
    }
}
