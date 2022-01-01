package Socket;

import Utils.StatusCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread /*implements Runnable*/ {
//    private BufferedReader in;
//    private boolean isExist = true;
//
//    public ReceiveThread(Socket socket) throws IOException {
//        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true) {
//                String message = receive();
//                System.out.println("Receive: " + message);
//                if(message==null||StatusCode.isExitCode(message)){
//                    break;
//                }
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void stop() {
//        isExist = false;
//    }
//
//    public String receive() throws IOException {
//        return in.readLine();
//    }

    public static String receive(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }
}
