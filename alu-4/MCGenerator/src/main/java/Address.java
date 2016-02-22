import com.sun.org.apache.xalan.internal.xsltc.dom.AdaptiveResultTreeImpl;

import java.io.Serializable;

/**
 * Created by torben on 20.02.16.
 */
public class Address implements Serializable{

    private byte value;

    public Address(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("0x%02X", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return value == address.value;

    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
