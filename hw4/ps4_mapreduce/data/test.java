import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class test {
    public static void main(String[] args) { 
        String filePath="users1.txt"; 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header line)
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String birthyear = values[3].split("-")[0];
                String userId = values[0]; 
                System.out.println("values is" +Arrays.toString(values) + " --- " + birthyear+"--- id" + userId + "---values length is" + values.length);
                ArrayList<String> ids = new ArrayList<String>();
                int idx_to_start = 4;
                if (values[idx_to_start].contains("@")){
                    idx_to_start+=1;
                }
                for (int i = idx_to_start; i <values.length; i++){
                    System.out.println(values[i].toString());
                    if (values[i].contains(";")){
                        String[] idsToAdd = values[i].split(";")[0].split(",");
                        for (String id: idsToAdd){
                            ids.add(id); 
                            System.out.println(id.toString());
                        }
                        break;
                    }
                    ids.add(values[i]); 
                }
                System.out.println("user is " + userId + "--" + ids );
                // System.out.println(values[idx_to_start].toString());
                // while (!values[idx_to_start].contains(";") ){
                //     ids.add(values[idx_to_start]);
                //     idx_to_start+=1;
                //     if (idx_to_start == values.length){
                //         break;
                //     }
                // }
                
                // System.out.println("user is " + values[1].toString() + "--" + ids ); 
            }

            // TEST: Seperate by email
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
            // // TEST: Friends, groups
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                // if (values.length == 1){
                //     System.out.println("no friends");
                // } else{
                    /* System.out.println(Arrays.toString(values)); */
                    //System.out.println(values[0].toString());
                    String[] userInfo = values[0].split(",");
                    //System.out.println(userInfo[0].toString() + " num friends " + values[1].split(",").length + "---"+values[1].toString());
                    System.out.println(Arrays.toString(userInfo) +"-----------" );
                    int group_idxes = 0;
                    for (int i=0; i < userInfo.length;i++){
                        if (userInfo[i].contains("@")){
                            if (i == userInfo.length){
                                System.out.println("_____NO USER GROUP______");
                                continue;
                            }
                            group_idxes = i+1;
                            break;
                        }
                    }
                    for (int i=group_idxes; i<userInfo.length;i++){
                        System.out.println(userInfo[i].toString());
                    } 
                    String[] age = line.split("-");
                    String[] year = age[age.length-3].split(",");
                    System.out.println(Arrays.toString(year));    
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println(Arrays.toString(values));
                int group_idxes=0; 
                for (int i=0; i <values.length;i++){
                    if (values[i].contains("@")){
                            if (i == values.length){
                                System.out.println("_____NO USER GROUP______");
                                continue;
                            }
                            group_idxes = i+1;
                            break;
                    }
                }
                for (int i=group_idxes; i< values.length;i++){
                        System.out.println(values[i].toString());
                    }
                String[] age = line.split("-");
                System.out.println("age is " +Arrays.toString(age) + " -- year -- " + age[age.length-1].toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

