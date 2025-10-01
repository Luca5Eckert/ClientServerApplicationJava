package cliente.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ComponentUtil {

    static PrintWriter toInstanceWritter(Socket socketCommunication) throws IOException {
        return new PrintWriter(socketCommunication.getOutputStream(), true);
    }

    static BufferedReader toInstanceReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
