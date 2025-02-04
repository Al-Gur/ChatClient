package telran.chat.client.task;

import telran.chat.model.Message;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessageSender implements Runnable {
    private final Socket socket;

    public MessageSender(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Socket socket = this.socket) {
//            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your name");
            String name = br.readLine();
            System.out.println("Enter your message, or type exit for quit");
            String message = br.readLine();
            while (!"exit".equalsIgnoreCase(message)) {
                Message messageObject = new Message(name, LocalTime.now(), message);
                outputStream.writeObject(messageObject);
                outputStream.flush();
//                socketWriter.println(name + " [" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "] " + message);
                message = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
