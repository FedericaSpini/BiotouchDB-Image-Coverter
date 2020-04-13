import java.io.Serializable;

public class MovementPoint implements Serializable
{
    private Integer time;
    private Integer component;
    private Float x;
    private Float y;

    public SampledPoint getSampledPoint(){
        return new SampledPoint(x, y);
    }

    public Integer getTime() {return time;}

    public void setTime(Integer time) {this.time = time;}

    public Integer getComponent() {return component;}

    public void setComponent(Integer component) {this.component = component;}

    public Float getX() {return x;}

    public void setX(Float x) {this.x = x;}

    public Float getY() {return y;}

    public void setY(Float y) {this.y = y;}


}
