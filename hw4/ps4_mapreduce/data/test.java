

import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class test {
    public static void main(String[] args) {
        String userInput = "41,Vasquez,John,1978-04-10,john.vasquez@gmail.com,12,8,10,6,15;54";
        String[] words = userInput.split(",");
        String new_word = "";
        int idxOfEmailStr = 0;
        for (String word : words) {
            if (word.contains("@")) {
                new_word = word;
                break;
            }
            idxOfEmailStr += 1; // Increment inside loop, before finding the email to correct the index
        }


        // Now, let's handle file reading with try-catch to catch IOException and FileNotFoundException
        String filePath="users20.txt"; 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header line)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Do something with the values
                System.out.println(Arrays.toString(values));
                for (String word: values){
                if (word.contains("@")){
                    System.out.println("-------------HERE--------------");
                    System.out.println(word.split("@")[1].toString());
                }}
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

