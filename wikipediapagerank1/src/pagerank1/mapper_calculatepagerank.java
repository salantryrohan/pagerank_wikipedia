/*
 * Author - Rohan Salantry
 * Mapper class to extact links out of a wikipedia article
 */
//import org.apache.hadoop.io.

package pagerank1;
// Exception classes required
import java.io.IOException;

// data types required from hadoop
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.ArrayWritable;

// mapper class from mapreduce library
import org.apache.hadoop.mapreduce.Mapper;

// regular expression classes
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

// List interface instead of string array
import java.util.List;
import java.util.ArrayList;


// set data type to long,text,text,text instead of long,text,text,Intwritable


public class mapper_calculatepagerank extends Mapper<LongWritable,Text,Text,Text>

{
	private static void print(Object o)
	{
	System.out.println(o);
	}
	
	
	@Override
	public void map(LongWritable key,Text value,Context context)throws IOException,InterruptedException
	{
		String line = value.toString();
		String[] fields;
		fields = line.split("\t");
				
		String original = "((ORIGINAL"+"\t"+fields[2]+"))";
		String outlinks = fields[2];
		String pagerank = fields[1];
		
		// emit articlename, [[ORIGINAL, <linkarray>]]
		
		context.write(new Text(fields[0]), new Text(original));
		
		
		String[] outlinksarr = outlinks.split(",");
		
		String numoutlinks = Integer.toString(outlinksarr.length);
		
		String temp = "";
		
		// emit outlink,pagerank,numoutlinks
		for(String x:outlinksarr)
		{
			context.write(new Text(x), new Text("((CONTRIBUTION"+"\t"+pagerank+"\t"+numoutlinks+"))"));
		}
		
						
		}
	
		
}


