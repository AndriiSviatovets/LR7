import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface  extends Remote {
    <T> T executeTask(TaskInterface<T> task) throws RemoteException;
}

