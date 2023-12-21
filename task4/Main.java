import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        LinkedList<Integer> nums = new LinkedList<>();

        Scanner scannerFile = new Scanner(new File(args[0]));

        while (scannerFile.hasNextLine()) {
            nums.add(scannerFile.nextInt());
            if(scannerFile.hasNextLine()){
                scannerFile.nextLine();
            }
        }

        Collections.sort(nums);

        int middle = nums.size()/ 2;
        int medianValue = Integer.parseInt(String.valueOf(nums.get(middle)));
        if (nums.size() % 2 == 0) {
            medianValue = (medianValue + Integer.parseInt(String.valueOf(nums.get(middle - 1)))) / 2;
        }

        int sumOfActions = 0;

        for (Integer num : nums) {
            sumOfActions += Math.abs(num - medianValue);
        }

        System.out.println(sumOfActions);

    }
}