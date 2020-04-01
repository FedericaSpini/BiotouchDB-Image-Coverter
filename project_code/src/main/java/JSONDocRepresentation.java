import java.io.Serializable;

public class JSONDocRepresentation implements Serializable {
    private String date;
    private MovementPoint[] movementPoints;
    private MovementPoint[][] sampledPoints;
    private SessionData sessionData;
    private MovementPoint[] touchDownPoints;
    private MovementPoint[] touchUpPoints;
    private Integer wordNumber;

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public MovementPoint[] getMovementPoints() {return movementPoints;}

    public void setMovementPoints(MovementPoint[] movementPoints) {this.movementPoints = movementPoints;}

    public MovementPoint[][] getSampledPoints() {return sampledPoints;}

    public void setSampledPoints(MovementPoint[][] sampledPoints) {this.sampledPoints = sampledPoints;}

    public SessionData getSessionData() {return sessionData;}

    public void setSessionData(SessionData sessionData) {this.sessionData = sessionData;}

    public MovementPoint[] getTouchDownPoints() { return touchDownPoints;}

    public void setTouchDownPoints(MovementPoint[] touchDownPoints) {this.touchDownPoints = touchDownPoints;}

    public MovementPoint[] getTouchUpPoints() {return touchUpPoints;}

    public void setTouchUpPoints(MovementPoint[] touchUpPoints) {this.touchUpPoints = touchUpPoints;}

    public Integer getWordNumber() {return wordNumber;}

    public void setWordNumber(Integer wordNumber) {this.wordNumber = wordNumber;}
}
