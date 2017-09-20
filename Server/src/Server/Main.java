package Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main extends HelloSayer{

    public static void main(String args[]) {

        try {
            HelloSayer obj = new HelloSayer();

            HelloSayerInterface stub = (HelloSayerInterface) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("HelloSayerInterface", stub);
            System.err.println("Server ready");

        } catch (RemoteException|AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}
