package Socket;

import Utils.Console;

import java.net.Socket;

public class TCPClient {
    public static Console console = new Console();

    public static void connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            String nickname = console.getInput("Enter your nickname");
            SendThread.send(socket, nickname);
            while (ReceiveThread.receive(socket).contains("is used")) {
                nickname = console.getInput("Reenter your nickname");
                SendThread.send(socket, nickname);
            }
            String message=ReceiveThread.receive(socket);
            while(true){
                System.out.println("You want to chat with " + message);
                SendThread.send(socket, console.getInput("Your answer"));
                message=ReceiveThread.receive(socket);
                if (message.contains("Connecting")) {
                    System.out.println(message);
                    SendThread sendThread = new SendThread(socket);
                    Thread send = new Thread(sendThread);
                    send.start();

                    ReceiveThread receiveThread = new ReceiveThread(socket);
                    Thread receive = new Thread(receiveThread);
                    receive.start();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        TCPClient.connect("localhost", 6000);
    }
}
