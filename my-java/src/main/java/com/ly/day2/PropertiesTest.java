package com.ly.day2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * ClassName: Properties
 * Package: com.ly.day2
 * Description:
 *
 * @Author ly
 * @Create 2023/10/31 14:16
 * @Version 1.0
 */
public class PropertiesTest {
    public static void main(String[] args) {
        Properties prop = new Properties();

        try {
            File file = new File("info.properties");
            File file1 = new File("D:\\app\\server\\IDEA\\idea-project\\my-project", "info.properties");
            FileInputStream fileInputStream = new FileInputStream(file1);
            FileReader fileReader = new FileReader(file1);
            System.out.println(file.getAbsoluteFile());
            prop.load(fileReader);
            System.out.println(prop.getProperty("name"));


            /*long l = file.lastModified();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(l);
            String format = simpleDateFormat.format(date);
            System.out.println(format);*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
