/*
 * Author - Rohan Salantry
 * Mapper class to extact links out of a wikipedia article
 */
//import org.apache.hadoop.io.

package pagerank;
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


public class mapper_extractlinks extends Mapper<LongWritable,Text,Text,Valuetuple>

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
		String recordtype = fields[2]; 
		String articlename = fields[1];
		String articletext = "";
		
		
		
		
		if(recordtype.equals("article"))
		{
			articletext = fields[3];
			
			
			List<String> matches = new ArrayList<String>();
			String patternString = "\\[\\[([a-zA-Z0-9_\\-\\s]+)(\\|)?([a-zA-Z0-9_\\-\\s]+)?\\]\\]";
			
			Pattern pattern = Pattern.compile(patternString);
			Matcher matcher = pattern.matcher(articletext);
				
			String outlink = "";
			String synonym = "";
			String delim = "";
			
			while(matcher.find())
			{
				outlink = matcher.group(1);
				delim = matcher.group(2);
				synonym = matcher.group(3);
				
				if(outlink.contains("File") || outlink.contains("Image"))
				{
					// do nothing
					
				}
				
				else
				{
				   if(synonym != "")
				   {
					   print(outlink+"\t"+delim+"\t"+synonym);
					   
				   }
				   matches.add(outlink);
				   
				   // emit <articlename,link>
				   // initialize pagerank to 1
				  
				  // context.write(new Text(articlename), new Text("1"));
				   
				   
				}
				
							
				
			}
			
			 context.write(new Text(articlename), new Valuetuple(1.0f,matches));
			 
		}
		
		
	
		
	}

}
