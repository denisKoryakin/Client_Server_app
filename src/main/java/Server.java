import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        System.out.println("Server started");
//        создаем новое сетевое соединение
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
//             Выходной поток
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//             Буферизированный входной поток
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
//                    Читаем строку и выводим её на экран вместе с номером порта клиента, с которого пришло соединение
                    out.println(String.format("New connection accepted. Port: %d. Write your name", clientSocket.getPort()));
                    final String name = in.readLine();
                    out.println(String.format("Hi %s! Are you child? (yes/no)", name));
                    String childConfirm = in.readLine();
                    if (childConfirm.equals("yes")) {
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    } else if (childConfirm.equals("no")) {
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    } else {
                        out.println("invalid response!");
                    }
                }
            }
        }
    }
}
