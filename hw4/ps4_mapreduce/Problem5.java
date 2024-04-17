/*
 * Problem5.java
 * 
 * CS 460: Problem Set 4
 */

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

public class Problem5 {
    public static class MyMapper
      extends Mapper<Object, Text, Text, Text> 
    {
        public void map(Object key, Text value, Context context) 
      throws IOException, InterruptedException 
        {
            String line = value.toString();
            String[] lines = line.split(";");
            
            String[] userInfo = lines[0].split(",");
            String userId = userInfo[0];
            if (lines.length > 1){
                String id_numfriends = userId+" "+ lines[1].split(",").length;
                context.write(new Text("friends"), new Text(id_numfriends));
            } else{
                String id_numfriends = userId+" "+ 0; 
                context.write(new Text("friends"), new Text(id_numfriends));
            } 
        }
    }

    public static class MyReducer
      extends Reducer<Text, Text,Text,IntWritable> 
    {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
            String maxID = "";
            int numFriends = 0;
            for (Text val: values){
                String[] user_and_friends = val.toString().split(" ");
                if (Integer.parseInt(user_and_friends[1]) > numFriends){
                    numFriends =  Integer.parseInt(user_and_friends[1]);
                    maxID = user_and_friends[0];
                }
            }
            context.write(new Text(maxID), new IntWritable(numFriends));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "problem 5");
        job.setJarByClass(Problem5.class);

        // Specifies the names of the mapper and reducer classes.
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        // Sets the types for the keys and values output by the reducer.
        /* CHANGE THE CLASS NAMES AS NEEDED IN THESE TWO METHOD CALLS */
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Sets the types for the keys and values output by the mapper.
        /* CHANGE THE CLASS NAMES AS NEEDED IN THESE TWO METHOD CALLS */
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // Configure the type and location of the data being processed.
        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // Specify where the results should be stored.
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
