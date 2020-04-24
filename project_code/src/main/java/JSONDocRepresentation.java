import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import dataStructures.DeviceData;
import dataStructures.SampledPoint;
import dataStructures.MovementPoint;
import dataStructures.SessionData;

public class JSONDocRepresentation implements Serializable {
    private String date;
    private MovementPoint[] movementPoints;
    private SampledPoint[][] sampledPoints;
    private SessionData sessionData;
    private MovementPoint[] touchDownPoints;
    private MovementPoint[] touchUpPoints;
    private Integer wordNumber;

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public MovementPoint[] getMovementPoints() {return movementPoints;}

    public void setMovementPoints(MovementPoint[] movementPoints) {this.movementPoints = movementPoints;}

    public SampledPoint[][] getSampledPoints() {return sampledPoints;}

    public void setSampledPoints(SampledPoint[][] sampledPoints) {this.sampledPoints = sampledPoints;}

    public SessionData getSessionData() {return sessionData;}

    public void setSessionData(SessionData sessionData) {this.sessionData = sessionData;}

    public MovementPoint[] getTouchDownPoints() { return touchDownPoints;}

    public void setTouchDownPoints(MovementPoint[] touchDownPoints) {this.touchDownPoints = touchDownPoints;}

    public MovementPoint[] getTouchUpPoints() {return touchUpPoints;}

    public void setTouchUpPoints(MovementPoint[] touchUpPoints) {this.touchUpPoints = touchUpPoints;}

    public Integer getWordNumber() {return wordNumber;}

    public void setWordNumber(Integer wordNumber) {this.wordNumber = wordNumber;}

    @Override
    public String toString(){
        String res = "\n\n\n";
        res+= date + " \n";
        res += "I movement points di questa parola sono: " + movementPoints.length + "\n";
        res += "I sample points di questa parola sono: " + sampledPoints.length + "\n";
        res += "I touch_down points di questa parola sono: " + touchDownPoints.length + "\n";
        res += "I touch_up points di questa parola sono: " + touchUpPoints.length + "\n";
        res += "Parola # "+wordNumber;
        return res;
    }

    public ArrayList<SampledPoint> getAllPoints(){
        ArrayList<SampledPoint> res = new ArrayList<SampledPoint>();

        for(SampledPoint[] sampledPointsList: this.sampledPoints){
            Collections.addAll(res, sampledPointsList);
        }

        for(MovementPoint movementPoint: this.movementPoints){
            res.add(movementPoint.getSampledPoint());
        }
        return res;
    }
}
