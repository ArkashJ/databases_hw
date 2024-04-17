/*
 * Problem6.java
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

public class Problem6 {
    private static final int BIRTH_YEAR = 3;
    private static final int GROUPS = 4;
    public static class MyMapper
      extends Mapper<Object, Text, Text, LongWritable> 
    {
      public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
          String line = value.toString();
          String[] lines = line.split(",");
        int birthyear = Integer.parseInt(lines[3].split("-")[0]);
        if (lines[3].contains(";") ){
            return;
        }

        int idx_to_start = 4;

        if (idx_to_start < lines.length){
          if (lines[idx_to_start].contains("@")){
              if (lines[idx_to_start].contains(";")) {
                  return;
              }
              idx_to_start+=1;
          }
          if (idx_to_start >= lines.length) {
                return;
          }
          for (int i = idx_to_start; i <lines.length; i++){
              if (lines[i].contains(";")){
                  String idsToAdd =lines[i].split(";")[0];
                      if (birthyear <= 1963) {
                          context.write(new Text(idsToAdd), new LongWritable(1));
                      } else{
                            context.write(new Text(idsToAdd), new LongWritable(0));
                      }
                  break;
              }
              if (birthyear <= 1963) {
                  context.write(new Text(lines[i]), new LongWritable(1));
              } else {
                  context.write(new Text(lines[i]), new LongWritable(0));
              }
          }
        }
      }
    }

    public static class MyReducer
      extends Reducer<Text, LongWritable, Text, LongWritable> 
    {
        public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException{
            long count = 0;
            for (LongWritable val: values){
              long year = val.get();
              count += year;
            }
            context.write(key, new LongWritable(count));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "problem 6");
        job.setJarByClass(Problem6.class);

        // Specifies the names of the mapper and reducer classes.
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        // Sets the types for the keys and values output by the reducer.
        /* CHANGE THE CLASS NAMES AS NEEDED IN THESE TWO METHOD CALLS */
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // Sets the types for the keys and values output by the mapper.
        /* CHANGE THE CLASS NAMES AS NEEDED IN THESE TWO METHOD CALLS */
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // Configure the type and location of the data being processed.
        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // Specify where the results should be stored.
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
