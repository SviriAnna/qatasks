import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scannerFile1 = new Scanner(new File(args[0]));
        Scanner scannerFile2 = new Scanner(new File(args[1]));
        float centerX = scannerFile1.nextFloat();
        float centerY = scannerFile1.nextFloat();
        int radius = scannerFile1.nextInt();
        while (scannerFile2.hasNextLine()){
            float x = scannerFile2.nextFloat();
            float y = scannerFile2.nextFloat();
            float result = (float) Math.sqrt(Math.pow(x-centerX, 2) + Math.pow(y-centerY, 2));
            if(result == radius) {
                System.out.println(0);
            } else if (result < radius) {
                System.out.println(1);
            } else if (result > radius) {
                System.out.println(2);
            }
            if (scannerFile2.hasNextLine()) {
                scannerFile2.nextLine();
            }
        }
    }
}