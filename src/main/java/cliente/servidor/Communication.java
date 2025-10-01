package cliente.servidor;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public record Communication(Socket socket, PrintWriter writer, BufferedReader reader) {
}
