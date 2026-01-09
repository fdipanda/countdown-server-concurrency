import java.io.*;
import java.net.*;

public class ConcurrentCountdownServer {

    public static void main(String[] args) {
        int port = 8000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Concurrent Countdown Server running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // create a new thread for client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// one client connection
class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line = in.readLine();
            try {
                int n = Integer.parseInt(line.trim());
                System.out.println("[" + Thread.currentThread().getName() +
                        "] Starting countdown for n = " + n);

                for (int i = n; i >= 1; i--) {
                    out.println(i);
                    System.out.println("[" + Thread.currentThread().getName() + "] Sent: " + i);
                    Thread.sleep(500);
                }

                System.out.println("[" + Thread.currentThread().getName() +
                        "] Countdown complete for client.");
            } catch (NumberFormatException e) {
                out.println("Error: please send an integer.");
            }

            clientSocket.close();
            System.out.println("[" + Thread.currentThread().getName() + "] Client disconnected.\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
