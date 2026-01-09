import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CountdownClient_Iterative {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8000;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(
                        new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to Iterative countdown server at " + host + ":" + port);
            System.out.print("Enter a number to start countdown: ");
            String n = userInput.readLine();

            // send the number
            out.println(n);

            // receive results
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Server: " + response);
            }

            System.out.println("Countdown complete. Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
