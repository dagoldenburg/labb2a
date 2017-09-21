package RemoteInterfaces.ServerSide;

import RemoteInterfaces.ClientSide.ListenerIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SenderIF extends Remote {
    void broadCast(String msg) throws RemoteException;
    void clientConnect(ListenerIF listener,String name) throws RemoteException;
}
