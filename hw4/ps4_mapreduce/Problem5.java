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
      extends Mapper<Object, Object, Object, Object> 
    {

    }

    public static class MyReducer
      extends Reducer<Object, Object, Object, Object> 
    {

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
        job.setOutputKeyClass(Object.class);
        job.setOutputValueClass(Object.class);

        // Sets the types for the keys and values output by the mapper.
        /* CHANGE THE CLASS NAMES AS NEEDED IN THESE TWO METHOD CALLS */
        job.setMapOutputKeyClass(Object.class);
        job.setMapOutputValueClass(Object.class);

        // Configure the type and location of the data being processed.
        job.setInputFormatClass(TextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // Specify where the results should be stored.
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}