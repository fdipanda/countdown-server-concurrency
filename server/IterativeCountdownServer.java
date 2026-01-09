import java.io.*;
import java.net.*;

public class IterativeCountdownServer {

    public static void main(String[] args) {
        int port = 8000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Iterative Countdown Server running on port " + port);

            while (true) {
                // accepts one client at a time
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String line = in.readLine();   // reads 'n' from client
                try {
                    int n = Integer.parseInt(line.trim());
                    System.out.println("Starting countdown for n = " + n);

                    for (int i = n; i >= 1; i--) {
                        out.println(i);
                        System.out.println("Sent: " + i);
                        Thread.sleep(500);             // delay to allow me to start multiple clients at once
                    }

                    System.out.println("Countdown complete for client.");
                } catch (NumberFormatException e) {
                    out.println("Error: please send an integer.");
                    System.out.println("Received invalid input from client: " + line);
                }

                clientSocket.close();
                System.out.println("Client disconnected.\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
