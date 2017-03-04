/**
 * Created by NIPUNA on 3/4/2017.
 */
public class Tester {
    private static IO p = new IO();
    private static BiometricCore core = new BiometricCore();

    public static void main(String[] args) {
//        HandProfile testProfile = new HandProfile("Nipuna", new float[]{1, 2, 3, 4, 5}, new float[]{11, 22, 33, 44, 55}, new float[]{111, 222, 333, 444, 555});
        HandProfile newProfile = core.createProfile();
        p.writeLineToFile(core.prepareToSave(newProfile));
        System.out.println(p.readAllFromFile());
    }


}
