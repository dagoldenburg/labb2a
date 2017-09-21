package RemoteInterfaces.ClientSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ListenerIF extends Remote {

    public void displayMsg(String msg) throws RemoteException;

}
