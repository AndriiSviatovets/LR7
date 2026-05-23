import java.io.Serializable;

public interface TaskInterface<T> extends Serializable {
    T execute();
}