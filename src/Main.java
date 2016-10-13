import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Ask user to encode or decode
        System.out.println("Magical Steganography");
        System.out.println("Would you like to encode or decode?");
        System.out.println(" 1. Encode");
        System.out.println(" 2. Decode");
        System.out.print("> ");
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();

        if (n == 1) {
            /*
             * ENCODE
             */

            // Read text and add stop sequence

            // Convert to binary string

            // Read image

            // Set LSB of pixels to binary string

            // Save image
        } else if (n == 2) {
            /*
             * DECODE
             */

            // Read image

            // Strip binary values out and trim bits after stop sequence

            // Convert binary to text

            // Output text
        } else {
            System.out.println("Invalid input. Exiting.");
        }

    }
}
