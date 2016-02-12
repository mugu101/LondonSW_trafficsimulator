package londonsw.model.simulation.components;

import com.sun.tools.corba.se.idl.constExpr.Not;
import londonsw.model.simulation.components.vehicles.Vehicle;

import java.util.ArrayList;

/**
 * This class is where the vehicles actually move
 * This is based on the cell automaton model of simulation
 * Each lane is an "queue" and has a direction
 * Number slots in the lane will be based on the number of "cells" in the view the road/lane takes up
 */
public class Lane {

    private Vehicle[] lane;
    private int length;
    private Coordinate entry;
    private Coordinate exit;

    public Lane(Coordinate entry, Coordinate exit) throws NotALaneException {
        this.entry = entry;
        this.exit = exit;
        length = this.getLaneLength();
        lane = new Vehicle[length];
    }

    private int getLaneLength() throws NotALaneException {
        int aX = entry.getX();
        int aY = entry.getY();
        int bX = exit.getX();
        int bY = exit.getY();
        int length;

        if(aX == bX) {
            length = Math.abs(aY-bY) + 1;
            return length;
        }
        else if(aY == bY) {
            length = Math.abs(aX-bX) + 1;
            return length;
        }
        else
            throw new NotALaneException("Not a lane. Coordinate x or y must match for both");
    }

    public int getLength() {
        return length;
    }

    public boolean isCellEmpty(int cell) {
        if(cell < 0 || cell > this.length)
            return false;

        if(lane[cell] == null)
            return true;
        return false;
    }

    public Coordinate getEntry() {
        return entry;
    }

    public Coordinate getExit() {
        return exit;
    }

    public boolean setCell(Vehicle v, int cell) {
        if(cell < 0 || cell >= length)
            return false;

        lane[cell] = v;
        return true;
    }

    public boolean moveCar(int currCell) {

        Vehicle v = lane[currCell];
        if(v == null) {
            return false;
        }
        else {
            lane[currCell+1] = v;
            lane[currCell] = null;
            return true;
        }

    }


}

class NotALaneException extends Exception {
    public NotALaneException() { super(); }
    public NotALaneException(String msg) { super(msg); }
    public NotALaneException(String msg, Throwable t) { super(msg,t); }
    public NotALaneException(Throwable t) { super(t); }
}