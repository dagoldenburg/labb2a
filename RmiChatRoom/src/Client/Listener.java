package Client;

import RemoteInterfaces.ClientSide.ListenerIF;
import RemoteInterfaces.ServerSide.SenderIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Listener extends UnicastRemoteObject implements ListenerIF {

    protected Listener(SenderIF server,String name) throws RemoteException {
        server.clientConnect(this,name);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String input = scanner.nextLine();
            server.broadCast(input);
        }
    }

    @Override
    public void displayMsg(String msg) throws RemoteException {
        System.out.println(msg);
    }
}
