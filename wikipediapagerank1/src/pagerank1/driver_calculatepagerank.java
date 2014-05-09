package pagerank1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
//import org.apache.hadoop.mapred.jobcontrol.Job;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser; 


public class driver_calculatepagerank 
{
	public static void main(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
		String[] programArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (programArgs.length != 2) 
		{
		   System.err.println("Usage: MaxTemp <in> <out>");
		   System.exit(2);
		}
		
		JobControl jobcontrol = new JobControl("mainjob");
		
		Job job1 = new Job(conf, "Link_Extraction");
		
		job1.setJarByClass(driver_calculatepagerank .class);
		job1.setMapperClass(mapper_calculatepagerank.class);
		job1.setReducerClass(reducer_calculatepagerank.class);
		
		// setout key,value data type
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job1, new Path(programArgs[0]));
		FileOutputFormat.setOutputPath(job1, new Path(programArgs[1]));
		
		
		// Submit the job and wait for it to finish.
		System.exit(job1.waitForCompletion(true) ? 0 : 1);
		
	}
	
		
}
