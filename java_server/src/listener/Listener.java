package listener;

import java.io.IOException;

import server.Server;

public interface Listener {
    void readEvent(Server server) throws IOException, InterruptedException;
}
