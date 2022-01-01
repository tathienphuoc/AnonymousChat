package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread {

    public static String receive(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    }
}
