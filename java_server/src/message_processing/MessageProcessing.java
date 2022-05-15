package message_processing;

import java.io.IOException;
import java.io.Reader;

import listener.Listener;
import server.Server;

public class MessageProcessing implements Listener {
    private char[] array = new char[10000];


    public void readEvent(Server server) throws IOException, InterruptedException {
        processMessage(convertMessagesFromNodeJSToString(server.getReader()), server);
    }

    private String convertMessagesFromNodeJSToString(Reader in) throws IOException {
        in.read(array);
        return String.valueOf(array);
    }

    private void processMessage(String jsonMessage, Server server) throws IOException, InterruptedException {
        System.out.println(jsonMessage);
        // process the message and make a reply

        // send a reply
        server.sendMessageToNodeJS("{\"data\": {\"event\": \"post\"} }");
    }
}
