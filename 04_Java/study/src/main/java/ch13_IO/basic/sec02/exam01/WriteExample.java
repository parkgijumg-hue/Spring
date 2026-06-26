package main.java.ch13_IO.basic.sec02.exam01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {
    public static void main(String[] args) {
        try (OutputStream os = new FileOutputStream("src/main/java/ch13_IO/basic/test/a.txt")) {
            byte a = 10;
            byte b = 20;
            byte c = 30;
            os.write(a);
            os.write(b);
            os.write(c);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}