package Server;

import RemoteInterfaces.ClientSide.ClientIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerMain {

    private static ArrayList<ClientIF> connectionList = new ArrayList<>();

    //private static ArrayList<String> nameList = new ArrayList<>();

    //public synchronized static ArrayList<String> getNameList(){
    //    return nameList;
    //}

    public synchronized static ArrayList<ClientIF> getConnectionList(){
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
        for(int i = 0;i<ServerMain.getConnectionList().size();i++){
            try {
                result.append(ServerMain.getConnectionList().get(i).getName()+"\n");
            } catch (RemoteException e) {
                ServerMain.getConnectionList().remove(ServerMain.getConnectionList().get(i));
                //System.out.println(ServerMain.getNameList().get(i));
                //ServerMain.getNameList().remove(ServerMain.getNameList().get(i));
                i--;
            }
        }
        return result.toString();
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
