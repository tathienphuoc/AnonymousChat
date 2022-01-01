package Socket;

//import Utils.Console;
import Utils.StatusCode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SendThread/* implements Runnable */{
//    private BufferedWriter out;
//    private Console console;
//    private boolean isExist = true;
//
//    public SendThread(Socket socket) throws IOException {
//        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        console = new Console();
//    }
//
//    @Override
//    public void run() {
//        try {
//            while(true){
//                String message = console.getInput("Input your message ");
//                send(message);
//                if(StatusCode.isExitCode(message)){
//                    break;
//                }
//            }
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void stop() {
//        isExist = false;
//    }
//
//    public void send(String message) throws IOException {
//        out.write(message);
//        out.newLine();
//        out.flush();
//    }

    public static void send(Socket socket, String message) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write(message);
        out.newLine();
        out.flush();
    }
}
