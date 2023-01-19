import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int port = 8080;
        try (Socket clientSocket = new Socket(InetAddress.getByName("netology.homework"), port);
//             Выходной поток
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
//             Буферизированный входной поток
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String resp = in.readLine();
                System.out.println(resp);
                out.println(scanner.nextLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
