package main.java.ch13_IO.basic.sec03.exam02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("src/main/java/ch13_IO/basic/test/write.txt")) {
            byte[] array = new byte[100];
            while(true){
                int num = is.read();
                if(num == -1) break;
                for(int i = 0; i < num; i++) System.out.print((char) array[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
