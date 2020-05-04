import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import dataStructures.*;

public class JSONDocRepresentationAnonymous implements Serializable {
    private String date;
    private MovementPoint[] movementPoints;
    private SampledPoint[][] sampledPoints;
    private SessionDataAnonymous sessionData; //this is the field that changes with respect to the non anonymous version
    private MovementPoint[] touchDownPoints;
    private MovementPoint[] touchUpPoints;
    private Integer wordNumber;

    public JSONDocRepresentationAnonymous(JSONDocRepresentation jsonDocRepresentation, String id) {
        this.date = jsonDocRepresentation.getDate();
        this.movementPoints = jsonDocRepresentation.getMovementPoints();
        this.sampledPoints = jsonDocRepresentation.getSampledPoints();
        this.touchDownPoints = jsonDocRepresentation.getTouchDownPoints();
        this.touchUpPoints = jsonDocRepresentation.getTouchUpPoints();
        this.wordNumber = jsonDocRepresentation.getWordNumber();
        this.sessionData = new SessionDataAnonymous(jsonDocRepresentation.getSessionData(), id);
    }
    public SessionDataAnonymous getSessionData() {return sessionData;}

    public void setSessionData(SessionDataAnonymous sessionData) {this.sessionData = sessionData;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public MovementPoint[] getMovementPoints() {return movementPoints;}

    public void setMovementPoints(MovementPoint[] movementPoints) {this.movementPoints = movementPoints;}

    public SampledPoint[][] getSampledPoints() {return sampledPoints;}

    public void setSampledPoints(SampledPoint[][] sampledPoints) {this.sampledPoints = sampledPoints;}

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
