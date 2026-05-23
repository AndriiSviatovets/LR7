import java.rmi.RemoteException; 
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server implements ServerInterface {
    public Server() {
        super();
    }

    @Override
    public <T> T executeTask(TaskInterface<T> task) throws RemoteException {
        System.out.println("Executing task...");
        return task.execute();
    }

    public static void main(String[] args) {
    
        try{
            Server engine = new Server();
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(engine, 0);

            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind("ServerInterface", stub);
        }catch(Exception e){
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }
}
