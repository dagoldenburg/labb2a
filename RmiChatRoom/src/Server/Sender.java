package Server;

import RemoteInterfaces.ClientSide.ListenerIF;
import RemoteInterfaces.ServerSide.SenderIF;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Sender extends UnicastRemoteObject implements SenderIF {

    protected Sender() throws RemoteException {

    }

    @Override
    public void broadCast(String msg) throws RemoteException {
        for(ListenerIF lif : Server.getConnectionList()){
            lif.displayMsg(msg);
        }
    }


    @Override
    public void clientConnect(ListenerIF listener,String name) throws RemoteException {
        Server.getConnectionList().add(listener);
    }
}
