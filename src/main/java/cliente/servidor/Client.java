package cliente.servidor;

import java.io.IOException;
import java.net.Socket;

import static cliente.servidor.ComponentUtil.toInstanceReader;
import static cliente.servidor.ComponentUtil.toInstanceWritter;

public class Client {

    private Communication communication;

    public void initCommunication(String host, int port) throws IOException {
        var socket = toInstanceSocket(host, port);
        communication = new Communication(socket, toInstanceWritter(socket), toInstanceReader(socket));
        System.out.println("Conectado ao servidor.");

    }

    public void sendMessageWithServer(String message) throws IOException {
        communication.writer().println(message);

        String responseServer = communication.reader().readLine();
        System.out.println("Resposta do servidor: " + responseServer);

    }

    private Socket toInstanceSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }

    public void closeCommunication() throws IOException {
        communication.socket().close();
        communication.reader().close();
        communication.writer().close();

    }
}
