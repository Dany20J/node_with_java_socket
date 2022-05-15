
package server;

import java.net.*;

import listener.Listener;

import java.io.*;

public class Server {
    public Server(int port, Listener listener) {
        this.port = port;
        this.listner = listener;
    }

    private int port = 4050;
    private Writer out;
    private Reader in;
    private Listener listner;

    public void initiateServer() throws InterruptedException {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {

                try (Socket connection = server.accept()) {
                    out = new OutputStreamWriter(connection.getOutputStream());
                    in = new InputStreamReader(connection.getInputStream());
                    this.listner.readEvent(this);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
                out = null;
                in = null;
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void sendMessageToNodeJS(String jsonMessage) throws IOException, InterruptedException {
        while (true) {
            if (out != null) {
                out.write(jsonMessage);
                out.flush();
                break;
            }
            Thread.sleep(50);
        }
    }

    public Reader getReader() {
        return in;
    }
}
