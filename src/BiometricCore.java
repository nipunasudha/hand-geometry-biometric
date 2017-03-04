/**
 * Created by NIPUNA on 3/4/2017.
 */
public class BiometricCore {
    private static IO p = new IO();
    public String[] fingerNames = {"Thumb", "Index Finger", "Middle Finger", "Ring Finger", "Pinkie Finger",};
    private String delimiter = "%#%";

    public HandProfile collectHandData(boolean isCreate) {
        String username = "";
        float[] fingerLength = new float[5]; //Array in order from thumb to pinky
        float[] fingerWidth = new float[5]; //Array in order from thumb to pinky
        float[] fingerTipToDivision = new float[5]; //Array in order from thumb to pinky
        if (isCreate) {
            p.log("======= Create New Profile =======");
            p.log("Enter Your Name >");
            username = p.userInputString();
        } else {
            p.log("======= Enter Your Hand Measurements =======");
        }

        for (int i = 0; i < 5; i++) {
            String fingerName = fingerNames[i];
            p.log("Enter " + fingerName + " Length (cm) >");
            fingerLength[i] = p.userInputFloatValidate();
        }

        for (int i = 0; i < 5; i++) {
            String fingerName = fingerNames[i];
            p.log("Enter " + fingerName + " Width (cm) >");
            fingerWidth[i] = p.userInputFloatValidate();
        }

        for (int i = 0; i < 5; i++) {
            String fingerName = fingerNames[i];
            p.log("Enter " + fingerName + " Tip To 1st Division Distance (cm) >");
            fingerTipToDivision[i] = p.userInputFloatValidate();
        }
        HandProfile newProfile = new HandProfile(username, fingerLength, fingerWidth, fingerTipToDivision);
        return newProfile;
    }

    public String prepareToSave(HandProfile profileToSave) {
        String prepairedStr = "";
        float[][] measurements = {profileToSave.getFingerLength(), profileToSave.getFingerWidth(), profileToSave.getFingerTipToDivision()};
        prepairedStr = profileToSave.getUsername();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                prepairedStr = insertInto(prepairedStr, Float.toString(measurements[i][j]));
            }
        }
        return prepairedStr;
    }

    public HandProfile parseFromFile(String rawLineFromFile) {
        String[] parsed = rawLineFromFile.split(delimiter);
        String username;
        float[] fingerLength = new float[5]; //Array in order from thumb to pinky
        float[] fingerWidth = new float[5]; //Array in order from thumb to pinky
        float[] fingerTipToDivision = new float[5]; //Array in order from thumb to pinky
        username = parsed[0];
        fingerLength[0] = Float.parseFloat(parsed[1]);
        fingerLength[1] = Float.parseFloat(parsed[2]);
        fingerLength[2] = Float.parseFloat(parsed[3]);
        fingerLength[3] = Float.parseFloat(parsed[4]);
        fingerLength[4] = Float.parseFloat(parsed[5]);
//--------------
        fingerWidth[0] = Float.parseFloat(parsed[6]);
        fingerWidth[1] = Float.parseFloat(parsed[7]);
        fingerWidth[2] = Float.parseFloat(parsed[8]);
        fingerWidth[3] = Float.parseFloat(parsed[9]);
        fingerWidth[4] = Float.parseFloat(parsed[10]);
//--------------
        fingerTipToDivision[0] = Float.parseFloat(parsed[11]);
        fingerTipToDivision[1] = Float.parseFloat(parsed[12]);
        fingerTipToDivision[2] = Float.parseFloat(parsed[13]);
        fingerTipToDivision[3] = Float.parseFloat(parsed[14]);
        fingerTipToDivision[4] = Float.parseFloat(parsed[15]);
        HandProfile newProfile = new HandProfile(username, fingerLength, fingerWidth, fingerTipToDivision);
        return newProfile;
    }

    public String insertInto(String oriStr, String newStr) {
        return oriStr + this.delimiter + newStr;
    }

    public float searchProfile(HandProfile handProfile) {
        return 0;
    }

    public float compareProfile(HandProfile handProfile) {
        return 0;
    }
}
