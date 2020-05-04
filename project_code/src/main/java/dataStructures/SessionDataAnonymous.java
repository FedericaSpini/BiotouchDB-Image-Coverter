package dataStructures;

import java.io.Serializable;

public class SessionDataAnonymous implements Serializable {
    private Integer age;
    private String gender;
    private String handwriting;
    private Integer id;
    private Integer totalWordNumber;
    private DeviceData deviceData;

    //this is the attribute wich represents the non-anonymous user in the original dataset
    private String user_id;

    public SessionDataAnonymous(SessionData sessionData, String id) {
        this.age = sessionData.getAge();
        this.gender = sessionData.getGender();
        this.handwriting = sessionData.getHandwriting();
        this.id = sessionData.getId();
        this.totalWordNumber = sessionData.getTotalWordNumber();
        this.deviceData = sessionData.getDeviceData();
        this.user_id = id;
    }

    public String getUser_id() {return user_id;}

    public void setUser_id(String user_id) {this.user_id = user_id;}

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public String getHandwriting() {return handwriting;}

    public void setHandwriting(String handwriting) {this.handwriting = handwriting;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public Integer getTotalWordNumber() {return totalWordNumber;}

    public void setTotalWordNumber(Integer totalWordNumber) {this.totalWordNumber = totalWordNumber;}

    public DeviceData getDeviceData() {return deviceData;}

    public void setDeviceData(DeviceData deviceData) {this.deviceData = deviceData;}
}
