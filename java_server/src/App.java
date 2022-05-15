import message_processing.MessageProcessing;
import server.Server;

public class App {
    public static void main(String[] args) throws InterruptedException {
        new Server(4060, new MessageProcessing()).initiateServer();
    }
}
