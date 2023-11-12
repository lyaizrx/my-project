package com.ly.day2;

import java.io.*;

/**
 * ClassName: FileIOTest
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/11/1 14:51
 * @Version 1.0
 */
public class FileIOTest {
    public static void main(String[] args) {
        File file = null;
        FileInputStream inputStream = null;
        try {
            file = new File("hello.txt");

            inputStream = new FileInputStream(file);
            int read = inputStream.read();
            while (read != -1) {
                System.out.print((char) read);
                read = inputStream.read();
            }

//         FileReader fileReader = new FileReader(file);
//         int read1 = fileReader.read();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
