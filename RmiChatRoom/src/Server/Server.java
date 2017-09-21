package Server;

import RemoteInterfaces.ClientSide.ListenerIF;
import RemoteInterfaces.ServerSide.SenderIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server{

    static ArrayList<ListenerIF> connectionList = new ArrayList<>();

    public static ArrayList<ListenerIF> getConnectionList(){
        return connectionList;
    }

    public static void main(String args[]) {
        try {
            Naming.rebind("Chat",new Sender());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
