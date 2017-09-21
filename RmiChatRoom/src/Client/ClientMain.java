package Client;

import RemoteInterfaces.ServerSide.ServerIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {

    public static void main(String[] args) {

        try {
            ServerIF server = (ServerIF) Naming.lookup("rmi://localhost/Chat");
            Client client = new Client(server);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException|RemoteException e) {
            e.printStackTrace();
        }
    }


}

