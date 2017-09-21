package Server;

import RemoteInterfaces.HelloSayerInterface;

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
            registry.rebind("HelloSayerInterface", stub);
            System.out.println("Server running");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
