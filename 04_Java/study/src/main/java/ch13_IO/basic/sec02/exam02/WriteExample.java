package main.java.ch13_IO.basic.sec02.exam02;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

public class WriteExample {
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("src/main/java/ch13_IO/basic/test/write.txt", true)) {
            byte[] array = { 10, 20, 30 };
            os.write(array);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
