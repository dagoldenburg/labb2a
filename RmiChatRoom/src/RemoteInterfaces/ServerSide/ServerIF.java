package RemoteInterfaces.ServerSide;

import RemoteInterfaces.ClientSide.ClientIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {
    void unicast(ClientIF listener, String msg) throws RemoteException;
    void broadcast(ClientIF listener, String msg) throws RemoteException;
    void clientConnect(ClientIF listener) throws RemoteException;
    void recieveMessage(ClientIF listener, String msg) throws RemoteException;
    void checkIfAlive(ClientIF listener) throws RemoteException;
}
