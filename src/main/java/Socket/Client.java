package Socket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.Socket;

@Data
@AllArgsConstructor
public class Client {
    private Socket socket;
    private String nickName;
    private boolean isAvailable;
}
