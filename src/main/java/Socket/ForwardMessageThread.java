package Socket;

import Utils.StatusCode;

import java.io.*;

public class ForwardMessageThread extends Thread {
    private Client fromClient, toClient;
    private BufferedReader in;
    private BufferedWriter out;

//    public ForwardMessageThread(Socket fromSocket, Socket toSocket) throws IOException {
//        in = new BufferedReader(new InputStreamReader(fromSocket.getInputStream()));
//        out = new BufferedWriter(new OutputStreamWriter(toSocket.getOutputStream()));
//    }

    public ForwardMessageThread(Client fromClient, Client toClient) throws IOException {
        this.fromClient = fromClient;
        this.toClient = toClient;
        in = new BufferedReader(new InputStreamReader(fromClient.getSocket().getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(toClient.getSocket().getOutputStream()));
    }

    public void run() {
        while (true) {
            try {
                String message = receive();
                send(message);
                if (StatusCode.isExitCode(message)) {
                    int fromClientIndex=ServerThread.getIndexClientByNickname(fromClient.getNickName());
                    if(fromClientIndex!=-1){
                        ServerThread.clients.remove(fromClientIndex);
                    }
                    int toClientIndex=ServerThread.getIndexClientByNickname(toClient.getNickName());
                    if(toClientIndex!=-1){
                        ServerThread.clients.remove(toClientIndex);
                    }
//                    ServerThread.clients
//                            .get(ServerThread.getIndexClientByNickname(fromClient.getNickName()))
//                            .setAvailable(true);//delete roi xiu dung o vi tri toClient laij kh thay
//
//                    ServerThread.clients
//                            .get(ServerThread.getIndexClientByNickname(toClient.getNickName()))
//                            .setAvailable(true);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();
    }

    public String receive() throws IOException {
        return in.readLine();
    }
}
