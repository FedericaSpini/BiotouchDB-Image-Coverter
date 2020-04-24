package dataStructures;

import java.io.Serializable;


public class SampledPoint implements Serializable {
    private Float x;
    private Float y;

    public SampledPoint(Float x, Float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SampledPoint point = (SampledPoint) o;
        return this.y == point.y &&
                this.x == point.x;
    }

    public Float getX() {return x;}

    public void setX(Float x) {this.x = x;}

    public Float getY() {return y;}

    public void setY(Float y) {this.y = y;}
}