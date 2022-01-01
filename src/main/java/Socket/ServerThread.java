package Socket;

import Utils.StatusCode;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread {
    public static Vector<Client> clients = new Vector<>() {
        @Override
        public synchronized String toString() {
            String all = "Availble ";
            for (Client c : clients) {
                all += " " + c.getNickName();
            }

            String available = "Availble ";
            for (Client c : clients) {
                if (c.isAvailable())
                    available += " " + c.getNickName();
            }
            return all + available;
        }
    };
    private Socket clientSocket;

    public ServerThread(Socket socket) {
        clientSocket = socket;
    }

    public boolean isExist(String nickname) {
        for (Client client : clients) {
            if (client.getNickName().equalsIgnoreCase(nickname))
                return true;
        }
        return false;
    }

    public static int getIndexClientByNickname(String nickname) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getNickName().equalsIgnoreCase(nickname))
                return i;
        }
        return -1;
    }

    public void run() {
        try {
            String nickname = ReceiveThread.receive(clientSocket);

            if (StatusCode.isExitCode(nickname)) {
                return;
            }

            while (isExist(nickname)) {
                SendThread.send(clientSocket, StatusCode.NAME_IS_USED_CODE);
                nickname = ReceiveThread.receive(clientSocket);
                if (StatusCode.isExitCode(nickname)) {
                    return;
                }
            }
            System.out.println(nickname + " is connecting");
            SendThread.send(clientSocket, StatusCode.CONNECT_CODE);
            Client client = new Client(clientSocket, nickname, false);
            clients.add(client);
            boolean isFoundPartner = false;
            System.out.println(nickname + clients.toString());
            for (Client c : clients) {
                if (!c.getNickName().equalsIgnoreCase(nickname) && c.isAvailable()) {
                    c.setAvailable(false);
                    SendThread.send(c.getSocket(), nickname);
                    if (ReceiveThread.receive(c.getSocket()).contains("y")) {
                        SendThread.send(clientSocket, c.getNickName());
                        String message = ReceiveThread.receive(clientSocket);
                        if (StatusCode.isExitCode(message)) {
                            c.setAvailable(true);
                            SendThread.send(c.getSocket(), StatusCode.REFUSE_CODE);
                            clients.remove(client);
                            return;
                        }
                        if (message.contains("y")) {
                            client.setAvailable(false);
                            isFoundPartner = true;
                            System.out.println("Matching " + c.getNickName() + " - " + nickname);

                            SendThread.send(c.getSocket(), StatusCode.CHAT_CODE);
                            SendThread.send(clientSocket, StatusCode.CHAT_CODE);

                            new ForwardMessageThread(client, c).start();
                            new ForwardMessageThread(c, client).start();

                            break;
                        } else {
                            SendThread.send(c.getSocket(), StatusCode.REFUSE_CODE);
                            c.setAvailable(true);
                        }
                    } else {
                        c.setAvailable(true);
                    }
                }
            }
            if (!isFoundPartner) {
                client.setAvailable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

