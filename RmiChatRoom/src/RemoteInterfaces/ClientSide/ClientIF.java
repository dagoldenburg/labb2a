package RemoteInterfaces.ClientSide;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {

    void displayMsg(String msg) throws RemoteException;
    String getName() throws RemoteException;
    void setName(String newName) throws RemoteException;

}
