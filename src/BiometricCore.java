/**
 * Created by NIPUNA on 3/4/2017.
 */
public class BiometricCore {
    private static IO p = new IO();
    public String[] fingerNames = {"Thumb", "Index Finger", "Middle Finger", "Ring Finger", "Pinkie Finger",};
    private String delimiter = "%#%";

    public HandProfile createProfile() {
        String username;
        float[] fingerLength = new float[5]; //Array in order from thumb to pinky
        float[] fingerWidth = new float[5]; //Array in order from thumb to pinky
        float[] fingerTipToDivision = new float[5]; //Array in order from thumb to pinky
        p.log("======= Create New Profile =======");

        p.log("Enter Your Name >");
        username = p.userInputString();
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
