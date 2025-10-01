package cliente.servidor;


import java.io.IOException;
import java.util.Scanner;

public class ApplicationClientServer {
    public static void main(String[] args) throws IOException, InterruptedException {

        String host = "localhost";
        int port = 12345;

        Server server = new Server(port, -1);
        Client client = new Client();

        new Thread(server::initCommunication).start();
        Thread.sleep(1000);

        boolean running = true;
        Scanner scanner = new Scanner(System.in);


        try{
            client.initCommunication(host, port);

            System.out.println("-----------------------------------");
            System.out.println("Conectado ao servidor. ");
            System.out.println("-----------------------------------");

            while(running){

                Thread.sleep(1000);
                System.out.println("-----------------------------------");
                System.out.println("Mande uma mensagem ( 0 para sair ):");
                String message = scanner.nextLine();
                System.out.println("-----------------------------------");

                if(message == "0") {
                    client.closeCommunication();
                    return;
                }

                client.sendMessageWithServer(message);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}