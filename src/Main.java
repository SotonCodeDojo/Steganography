import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

            // Read text
            System.out.print("String to encode: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            input = null;
            try {
                input = br.readLine();
            } catch (IOException e) {
                System.err.println("There was an input error");
            }

            // Convert to binary string and add stop sequence
            String binaryStr = "";
            for (int i = 0; i < input.getBytes().length; i++) {
                binaryStr += Integer.toBinaryString(input.getBytes()[0]);
            }
            binaryStr += "10101010101010101010101010101010"; // 32bit long stop sequence
            char[] bin = binaryStr.toCharArray();

            // Read image
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File("image.jpg"));
            } catch (IOException e) {
                System.err.println("Error reading image");
            }

            if (img != null) {
                // Set LSB of pixels to binary string
                int count = 0;
                for (int y = 0; y < img.getHeight(); y++) {
                    for (int x = 0; x < img.getWidth(); x++) {
                        Color color = new Color(img.getRGB(x, y));
                        int r = color.getRed();
                        int g = color.getGreen();
                        int b = color.getBlue();
                        String binVal = Integer.toBinaryString(r);
                        if (binVal.length() < 8) {
                            String add = "";
                            for (int k = 0; k < (8 - binVal.length()); k++) {
                                add += "0";
                            }
                            binVal += add;
                        }
                        //System.out.println(binVal);
                        String outStr = binVal.substring(0, binVal.length() - 1) + bin[count];
                        count++;
                        r = Integer.parseInt(outStr, 2);
                        img.setRGB(x, y, (new Color(r, g, b)).getRGB());
                        if (count >= bin.length) {
                            break;
                        }
                    }
                    if (count >= bin.length) {
                        break;
                    }
                }

                // Save image
                try {
                    File outputfile = new File("output.jpg");
                    ImageIO.write(img, "jpg", outputfile);
                } catch (IOException e) {
                    System.err.println("Error writing image to file.");
                }
            }
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
