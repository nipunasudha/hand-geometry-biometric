public class BiometricApp {
    private static IOUtils p = new IOUtils();
    private static BiometricCore core = new BiometricCore(0.97f); //Initialize with threshold value

    public static void main(String[] args) {
        p.log("====== HAND GEOMETRY ACCESS SYSTEM ======");
        p.log("Please choose an option to continue...");
        p.log("1) Login using hand measurements");
        p.log("2) Register as a new user");
        p.log("-----------------------------------------");
        String userInput = p.userInputString();
        if (userInput.equals("2")) {
            HandProfile newProfile = core.collectHandData(true);
            p.writeLineToFile(core.prepareToSave(newProfile));
        } else if (userInput.equals("1")) {
            HandProfile existingProfile = core.collectHandData(false);
            System.out.println(core.searchProfile(existingProfile));
        } else {
            p.log("Invalid Input, Exiting.");
        }
    }
}
