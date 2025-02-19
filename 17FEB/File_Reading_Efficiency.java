import java.io.*;

public class File_Reading_Efficiency {
    public static void main(String[] args) {
        String filePath = "input.txt";

        long startTime = System.nanoTime();
        try (FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.read() != -1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("FileReader Time: " + (endTime - startTime) + " nanoseconds");

        startTime = System.nanoTime();
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (bufferedReader.read() != -1) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.nanoTime();
        System.out.println("InputStreamReader Time: " + (endTime - startTime) + " nanoseconds");
    }
}