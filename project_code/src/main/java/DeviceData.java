import java.io.Serializable;

public class DeviceData implements Serializable {

    private String deviceFingerPrint;
    private String deviceModel;
    private Integer heigthPixels;
    private Integer widthPixels;
    private Float xdpi;
    private Float ydpi;


    public String getDeviceFingerPrint() {return deviceFingerPrint;}

    public void setDeviceFingerPrint(String deviceFingerPrint) {this.deviceFingerPrint = deviceFingerPrint;}

    public String getDeviceModel() {return deviceModel;}

    public void setDeviceModel(String deviceModel) {this.deviceModel = deviceModel;}

    public Integer getHeigthPixels() {return heigthPixels;}

    public void setHeigthPixels(Integer heigthPixels) {this.heigthPixels = heigthPixels;}

    public Integer getWidthPixels() {return widthPixels;}

    public void setWidthPixels(Integer widthPixels) {this.widthPixels = widthPixels;}

    public Float getXdpi() {return xdpi;}

    public void setXdpi(Float xdpi) {this.xdpi = xdpi;}

    public Float getYdpi() {return ydpi;}

    public void setYdpi(Float ydpi) {this.ydpi = ydpi;}
}
