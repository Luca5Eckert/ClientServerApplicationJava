package cliente.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static cliente.servidor.ComponentUtil.toInstanceReader;
import static cliente.servidor.ComponentUtil.toInstanceWritter;

public class Server {

    private final int port;
    private final int ip;

    public Server(int port, int ip) {
        this.port = port;
        this.ip = ip;
    }

    public ServerSocket toInstanceServerSocket() throws IOException, IOException {
        return new ServerSocket(port);
    }

    public ServerSocket toInstanceServerSocketIp() throws IOException {
        return new ServerSocket(port, ip);
    }

    public ServerSocket initServerSocket() throws IOException {
        var server = toInstanceServerSocket();
        System.out.println("Servidor iniciado com sucesso");

        return server;
    }

    public void initCommunication(){
        try(var serverSocket = initServerSocket()){

            while(true){
                var socketCommunication = getCommunicationWithClient(serverSocket);

                var reader = toInstanceReader(socketCommunication);

                var message = reader.readLine();
                System.out.println("Mensagem do cliente: " + message);

                var writer = toInstanceWritter(socketCommunication);
                writer.println("Olá, cliente! Sua mensagem foi recebida com sucesso.");

                reader.close();
                writer.close();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Socket getCommunicationWithClient(ServerSocket serverSocket) throws IOException {
        Socket socketClient = serverSocket.accept();
        System.out.println("Cliente conectado: " + socketClient.getInetAddress().getHostAddress());
        return socketClient;
    }

}
