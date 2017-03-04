import java.util.*;
import java.io.*;

public class IO {
    String saveFileLocation = "biometricProfiles.dat";
    String lineSeperator = System.getProperty("line.separator");
    Scanner reader = new Scanner(System.in);

    //READ FILE
    public List readAllFromFile() {
        List readerOutput = new ArrayList();
        String line = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(saveFileLocation);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                readerOutput.add(line);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + saveFileLocation + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + saveFileLocation + "'");
        }
        return readerOutput;
    }

    //WRITE FILE
    public void writeLineToFile(String line) {
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(saveFileLocation, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(line);
            bufferedWriter.write(lineSeperator);

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + saveFileLocation + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    //LOG
    public void log(int data) {
        System.out.println(data);
    }

    public void log(float data) {
        System.out.println(data);
    }

    public void log(String data) {
        System.out.println(data);
    }

    //INPUT
    public int userInputInt() {
        return reader.nextInt();
    }

    public float userInputFloat() {
        return reader.nextFloat();
    }

    public float userInputFloatValidate() {
        Scanner localReader = new Scanner(System.in);
        float input = 0;
        try {
            input = localReader.nextFloat();
        } catch (Exception e) {
            log("Invalid Measurement. Please re-enter.");
        }
        if (input == 0) {
            input = userInputFloatValidate();
        }
        return input;
    }

    public String userInputString() {
        return reader.nextLine();
    }


}
