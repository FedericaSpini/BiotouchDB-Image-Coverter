package dataStructures;


import java.io.Serializable;

public class SessionData implements Serializable {
    private Integer age;
    private String gender;
    private String handwriting;
    private Integer id;
    private String name;
    private String surname;
    private Integer totalWordNumber;
    private DeviceData deviceData;

    public Integer getAge() {return age;}

    public void setAge(Integer age) {this.age = age;}

    public String getGender() {return gender;}

    public void setGender(String gender) {this.gender = gender;}

    public String getHandwriting() {return handwriting;}

    public void setHandwriting(String handwriting) {this.handwriting = handwriting;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public Integer getTotalWordNumber() {return totalWordNumber;}

    public void setTotalWordNumber(Integer totalWordNumber) {this.totalWordNumber = totalWordNumber;}

    public DeviceData getDeviceData() {return deviceData;}

    public void setDeviceData(DeviceData deviceData) {this.deviceData = deviceData;}
}
