package telran.chat.client.task;

import telran.chat.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class MessageReceiver implements Runnable {
    private final Socket socket;

    public MessageReceiver(Socket socket) {
        this.socket = socket;
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        try {
//            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Message message = (Message) inputStream.readObject(); //socketReader.readLine();
                System.out.println(message);
            }
        } catch (SocketException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
