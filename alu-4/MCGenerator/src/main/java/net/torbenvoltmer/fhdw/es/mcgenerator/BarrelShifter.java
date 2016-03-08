package net.torbenvoltmer.fhdw.es.mcgenerator;

import java.io.Serializable;

/**
 * Created by torben on 04.03.16.
 */
public class BarrelShifter implements Serializable{


    private Integer shift;

    private Boolean shiftNotRotate;
    private Boolean leftNotRight;


    public BarrelShifter(Integer shift, Boolean shiftNotRotate, Boolean leftNotRight) {
        this.shift = shift;
        this.shiftNotRotate = shiftNotRotate;
        this.leftNotRight = leftNotRight;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }

    public Boolean getShiftNotRotate() {
        return shiftNotRotate;
    }

    public void setShiftNotRotate(Boolean shiftNotRotate) {
        this.shiftNotRotate = shiftNotRotate;
    }

    public Boolean getLeftNotRight() {
        return leftNotRight;
    }

    public void setLeftNotRight(Boolean leftNotRight) {
        this.leftNotRight = leftNotRight;
    }
}
