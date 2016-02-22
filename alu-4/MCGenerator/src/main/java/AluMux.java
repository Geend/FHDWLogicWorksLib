import java.io.Serializable;

/**
 * Created by torben on 22.02.16.
 */
public enum AluMux implements Serializable{
    ALU181(0),
    MUL(1),
    CONST(2);

    private int value;

    AluMux(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
