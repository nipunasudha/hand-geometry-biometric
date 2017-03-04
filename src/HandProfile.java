/**
 * Created by NIPUNA on 3/4/2017.
 */
public class HandProfile {
    private String username;
    private float[] fingerLength; //Array in order from thumb to pinky
    private float[] fingerWidth; //Array in order from thumb to pinky
    private float[] fingerTipToDivision; //Array in order from thumb to pinky

    public HandProfile(String username, float[] fingerLength, float[] fingerWidth, float[] fingerTipToDivision) {
        setUsername(username);
        setFingerLength(fingerLength);
        setFingerWidth(fingerWidth);
        setFingerTipToDivision(fingerTipToDivision);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float[] getFingerLength() {
        return fingerLength;
    }

    public void setFingerLength(float[] fingerLength) {
        this.fingerLength = fingerLength;
    }

    public float[] getFingerWidth() {
        return fingerWidth;
    }

    public void setFingerWidth(float[] fingerWidth) {
        this.fingerWidth = fingerWidth;
    }

    public float[] getFingerTipToDivision() {
        return fingerTipToDivision;
    }

    public void setFingerTipToDivision(float[] fingerTipToDivision) {
        this.fingerTipToDivision = fingerTipToDivision;
    }


}
