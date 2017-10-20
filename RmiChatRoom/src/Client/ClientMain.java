package Client;

import RemoteInterfaces.ServerSide.ServerIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {
    private static class Pulse implements Runnable{

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000);
                    server.checkIfAlive(client);
                } catch (InterruptedException|RemoteException e) {
                    System.out.println("Server dead exiting");
                    System.exit(0);
                }
            }
        }
    }
    public static ServerIF server;
    public static Client client;
    public static void main(String[] args) {
        try {
            server = (ServerIF) Naming.lookup("rmi://localhost/Chat");
            client = new Client(server);
            Thread t = new Thread(new Pulse());
            t.start();
            client.runClient(server);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException|RemoteException e) {
            e.printStackTrace();
        }
    }


}

