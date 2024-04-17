package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import java.util.*;
public class test2 {
        public static void main(String[] args) {
            String filePath="users100.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                // Skip the first line (header line)
                br.readLine();

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    System.out.println("NEW");
                    String birthyear = values[3].split("-")[0];
                    String userId = values[0];
                    System.out.println("values is" + Arrays.toString(values) + " --- " + birthyear + "--- id" + userId + "---values length is" + values.length);
                    ArrayList<String> ids = new ArrayList<String>();
                    int idx_to_start = 4;
                    if (values[idx_to_start].contains("@")) {
                        if (values[idx_to_start].contains(";")) {
                            return;
                        }
                        idx_to_start += 1;
                    }
                    if (idx_to_start >= values.length) {
                        continue;
                    }


                    System.out.println("values of 4 is "+ values[4] + "---- & the idx is ---" + values[idx_to_start]);
                    for (int i = idx_to_start; i < values.length; i++) {
                        System.out.println("HERE---------------" + values[i].toString());
                        if (values[i].contains(";")) {
                            System.out.println("------------FINDING---------------");
                            String[] idsToAdd = values[i].split(";")[0].split(",");
                            System.out.println("idsToAdd is " + Arrays.toString(idsToAdd));
                            for (String id : idsToAdd) {
                                ids.add(id);
                                System.out.println(id.toString());
                            }
                            break;
                        }
                        ids.add(values[i]);
                    }
                    System.out.println("user is " + userId + "--" + ids);
                    System.out.println("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
