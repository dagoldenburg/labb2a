package Client;

import RemoteInterfaces.ClientSide.ListenerIF;
import RemoteInterfaces.ServerSide.SenderIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client   {

    public static void main(String[] args) {
        if(args[0]==null){
            System.out.println("Example run: java Client <name>");
            return;
        }
        try {
            SenderIF server = (SenderIF) Naming.lookup("rmi://localhost/Chat");
            Listener listener = new Listener(server,args[0]);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}

