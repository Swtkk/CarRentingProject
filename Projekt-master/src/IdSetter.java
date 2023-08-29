import java.io.Serializable;

public class IdSetter implements Serializable {
    int id = 0;

    public int setNextId(){
        id++;
        return id;
    }
}
