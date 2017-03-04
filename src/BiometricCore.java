import java.util.ArrayList;
import java.util.List;

public class BiometricCore {
    private static IOUtils p = new IOUtils();
    public String[] fingerNames = {"Thumb", "Index Finger", "Middle Finger", "Ring Finger", "Pinkie Finger",};
    private String delimiter = "%#%";
    private float threshold = 0.95f;

    public BiometricCore(float threshold) {
        this.threshold = threshold;
    }

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

    public HandProfile searchProfile(HandProfile slaveProfile) {
        float maxConfidence = 0;
        HandProfile maxConfidenceProfile = null;
        List listFromFile = p.readAllFromFile();
        for (int i = 0; i < listFromFile.size(); i++) {
            HandProfile masterProfile = parseFromFile(listFromFile.get(i).toString());
            float confidence = compareProfile(slaveProfile, masterProfile);
            if (maxConfidence < confidence) {
                maxConfidence = confidence;
                maxConfidenceProfile = masterProfile;
            }

        }


        if (maxConfidence > threshold && maxConfidence > 0) {
            p.log("Confidence: " + Float.toString(maxConfidence));
            p.log("Username: " + maxConfidenceProfile.getUsername());
            return maxConfidenceProfile;
        } else {
            p.log("No Match Found!");
            return null;
        }

    }

    public float compareProfile(HandProfile master, HandProfile slave) {
        float confidance = 0;
        float ratio;
        List<Float> ratioList = new ArrayList<Float>();
        for (int j = 0; j < 5; j++) {
            ratio = master.getFingerLength()[j] / slave.getFingerLength()[j];
            ratioList.add(ratio <= 1 ? ratio : (1 / ratio));
            ratio = master.getFingerWidth()[j] / slave.getFingerWidth()[j];
            ratioList.add(ratio <= 1 ? ratio : (1 / ratio));
            ratio = master.getFingerTipToDivision()[j] / slave.getFingerTipToDivision()[j];
            ratioList.add(ratio <= 1 ? ratio : (1 / ratio));

        }
        confidance = calculateAverage(ratioList);
        return confidance;
    }

    public float calculateAverage(List<Float> list) {
        Double sum = 0d;
        for (Float vals : list) {
            sum += (vals);
        }
        sum = sum / list.size();
        return sum.floatValue();
    }
}
