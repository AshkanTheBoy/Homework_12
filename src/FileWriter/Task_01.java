package FileWriter;

import java.io.*;

public class Task_01 {
    public static void main(String[] args) {
        String[] text = {"Hello", "people", "of Earth"};

        try {
            FileWriter writer = new FileWriter("wow.txt");
            for (String string : text) {
                writer.write(string + "\n");
            }
            writer.append("123");
            writer.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileReader reader = new FileReader("wow.txt");
            int data = reader.read();
            while (data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
