package main.java.ch13_IO.basic.sec03.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("src/main/java/ch13_IO/basic/test/a.txt")) {
            while(true){
                int data = is.read();
                if(data == -1) break;
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
