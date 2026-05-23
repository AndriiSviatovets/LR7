import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {

        try {
            // Сonnect to the RMI registry and look up the server interface
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);
            ServerInterface comp = (ServerInterface) registry.lookup("ServerInterface");

            int digits = 50;
            System.out.println("Setting precision to " + digits + " decimal places.");

            // Create and send the task for calculating pi
            Task piTask = new Task(digits);
            BigDecimal pi = comp.executeTask(piTask);
            System.out.println("Result pi: " + pi);

            // Create and send the task for calculating e
            ETask eTask = new ETask(digits);
            BigDecimal e = comp.executeTask(eTask);
            System.out.println("Result e:  " + e);

        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}