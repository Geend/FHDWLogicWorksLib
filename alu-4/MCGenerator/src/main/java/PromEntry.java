import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;

import java.io.*;

/**
 * Created by torben on 20.02.16.
 */
public class PromEntry implements Externalizable{

    private SimpleObjectProperty<Address> address;

    private SimpleObjectProperty<Address> nextAddress;
    private SimpleObjectProperty<AluCode> aluCode;
    private SimpleObjectProperty<AluMux> aluMux;
    private SimpleIntegerProperty constant;

    private SimpleBooleanProperty aFromAccu;
    private SimpleBooleanProperty bFromAccu;

    private SimpleBooleanProperty writeAccuA;
    private SimpleBooleanProperty writeAccuB;

    private SimpleBooleanProperty writeRegAInt;
    private SimpleBooleanProperty writeRegBInt;

    private SimpleBooleanProperty busy;

    public PromEntry(){
    }

    public PromEntry(Address address, Address nextAddress){

        this.address = new SimpleObjectProperty(address);
        this.nextAddress = new SimpleObjectProperty(nextAddress);


        aluCode = new SimpleObjectProperty<>(new AluCode(false, false,false,false,false));
        aluMux = new SimpleObjectProperty<>(AluMux.ALU181);
        constant = new SimpleIntegerProperty(0);

        aFromAccu = new SimpleBooleanProperty(false);
        bFromAccu = new SimpleBooleanProperty(false);

        writeAccuA = new SimpleBooleanProperty(false);
        writeAccuB = new SimpleBooleanProperty(false);

        writeRegAInt = new SimpleBooleanProperty(false);
        writeRegBInt = new SimpleBooleanProperty(false);

        busy = new SimpleBooleanProperty(true);
    }


    public AluCode getAluCode() {
        return aluCode.get();
    }

    public SimpleObjectProperty<AluCode> aluCodeProperty() {
        return aluCode;
    }

    public void setAluCode(AluCode aluCode) {
        this.aluCode.set(aluCode);
    }


    public AluMux getAluMux() {
        return aluMux.get();
    }

    public SimpleObjectProperty<AluMux> aluMuxProperty() {
        return aluMux;
    }

    public void setAluMux(AluMux aluMux) {
        this.aluMux.set(aluMux);
    }

    public int getConstant() {
        return constant.get();
    }

    public SimpleIntegerProperty constantProperty() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant.set(constant);
    }

    public boolean getaFromAccu() {
        return aFromAccu.get();
    }

    public SimpleBooleanProperty aFromAccuProperty() {
        return aFromAccu;
    }

    public void setaFromAccu(boolean aFromAccu) {
        this.aFromAccu.set(aFromAccu);
    }

    public boolean getbFromAccu() {
        return bFromAccu.get();
    }

    public SimpleBooleanProperty bFromAccuProperty() {
        return bFromAccu;
    }

    public void setbFromAccu(boolean bFromAccu) {
        this.bFromAccu.set(bFromAccu);
    }

    public boolean getWriteAccuA() {
        return writeAccuA.get();
    }

    public SimpleBooleanProperty writeAccuAProperty() {
        return writeAccuA;
    }

    public void setWriteAccuA(boolean writeAccuA) {
        this.writeAccuA.set(writeAccuA);
    }

    public boolean getWriteAccuB() {
        return writeAccuB.get();
    }

    public SimpleBooleanProperty writeAccuBProperty() {
        return writeAccuB;
    }

    public void setWriteAccuB(boolean writeAccuB) {
        this.writeAccuB.set(writeAccuB);
    }

    public boolean getWriteRegAInt() {
        return writeRegAInt.get();
    }

    public SimpleBooleanProperty writeRegAIntProperty() {
        return writeRegAInt;
    }

    public void setWriteRegAInt(boolean writeRegAInt) {
        this.writeRegAInt.set(writeRegAInt);
    }

    public boolean getWriteRegBInt() {
        return writeRegBInt.get();
    }

    public SimpleBooleanProperty writeRegBIntProperty() {
        return writeRegBInt;
    }

    public void setWriteRegBInt(boolean writeRegBInt) {
        this.writeRegBInt.set(writeRegBInt);
    }

    public boolean getBusy() {
        return busy.get();
    }

    public SimpleBooleanProperty busyProperty() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy.set(busy);
    }


    public Address getAddress() {
        return address.get();
    }



    public void setAddress(Address address) {
        this.address.set(address);
    }

    public Address getNextAddress() {
        return nextAddress.get();
    }

    public void setNextAddress(Address nextAddress) {
        this.nextAddress.set(nextAddress);
    }

    public SimpleObjectProperty<Address> nextAddressProperty(){ return nextAddress;}

    public SimpleObjectProperty<Address> addressProperty() {
        return address;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {

        objectOutput.writeObject(this.address.get());
        objectOutput.writeObject(this.nextAddress.get());

        objectOutput.writeObject(this.aluCode.get());

        objectOutput.writeObject(this.aluMux.getValue());
        objectOutput.writeInt(this.constant.getValue());


        objectOutput.writeBoolean(this.aFromAccu.getValue());
        objectOutput.writeBoolean(this.bFromAccu.getValue());

        objectOutput.writeBoolean(this.writeAccuA.getValue());
        objectOutput.writeBoolean(this.writeAccuB.getValue());

        objectOutput.writeBoolean(this.writeRegAInt.getValue());
        objectOutput.writeBoolean(this.writeRegBInt.getValue());

        objectOutput.writeBoolean(this.busy.getValue());

    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {

        this.address = new SimpleObjectProperty(objectInput.readObject());
        this.nextAddress =  new SimpleObjectProperty(objectInput.readObject());

        aluCode =  new SimpleObjectProperty(objectInput.readObject());
        aluMux = new SimpleObjectProperty(objectInput.readObject());
        constant =  new SimpleIntegerProperty(objectInput.readInt());

        aFromAccu = new SimpleBooleanProperty(objectInput.readBoolean());
        bFromAccu = new SimpleBooleanProperty(objectInput.readBoolean());

        writeAccuA = new SimpleBooleanProperty(objectInput.readBoolean());
        writeAccuB = new SimpleBooleanProperty(objectInput.readBoolean());

        writeRegAInt = new SimpleBooleanProperty(objectInput.readBoolean());
        writeRegBInt = new SimpleBooleanProperty(objectInput.readBoolean());

        busy = new SimpleBooleanProperty(objectInput.readBoolean());
    }
}
