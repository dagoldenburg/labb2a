package Server;

import RemoteInterfaces.ClientSide.ClientIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerMain {

    private static ArrayList<ClientIF> connectionList = new ArrayList<>();

    public static ArrayList<ClientIF> getConnectionList(){
        return connectionList;
    }

    public static void main(String args[]) {
        try {
            Naming.rebind("Chat",new Server());
        } catch (RemoteException|MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static String getClientNames(){
        StringBuilder result = new StringBuilder();
        for(ClientIF lif:connectionList){
            try {
                result.append(lif.getName()+'\n');
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isNameAvailable(String name){
        for(ClientIF lif:connectionList){
            try {
                if(name.equals(lif.getName())){
                    return false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
