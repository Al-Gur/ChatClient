package telran.chat.client;

import telran.chat.client.task.MessageReceiver;
import telran.chat.client.task.MessageSender;

import java.io.IOException;
import java.net.Socket;

public class ChatClientAppl {
    public static void main(String[] args) {
        String serverHost = args.length >= 1 ? args[0] : "127.0.0.1"; //"0.tcp.eu.ngrok.io";
        int port = args.length >= 2 ? Integer.parseInt(args[1]) : 10199;
        try {
            Socket socket = new Socket(serverHost, port);
            new MessageReceiver(socket);
            new Thread(new MessageSender(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
