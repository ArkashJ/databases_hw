import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class test {
    public static void main(String[] args) { 
        String filePath="users20.txt"; 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header line)
            br.readLine();

            String line;
            // while ((line = br.readLine()) != null) {
            //     String[] values = line.split(",");
            //     // Do something with the values
            //     System.out.println(Arrays.toString(values));
            //     for (String word: values){
            //     if (word.contains("@")){
            //         System.out.println("-------------HERE--------------");
            //         System.out.println(word.split("@")[1].toString());
            //     }}
            // } 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length == 1){
                    System.out.println("no friends");
                } else{
                    /* System.out.println(Arrays.toString(values)); */
                    System.out.println(values[0].toString());
                    String[] userInfo = values[0].split(",");
                    System.out.println(userInfo[0].toString() + " num friends " + values[1].split(",").length + "---"+values[1].toString());
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

