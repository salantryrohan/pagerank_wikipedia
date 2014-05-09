package pagerank1;
import java.io.IOException;

//data types required from hadoop
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

//mapper class from mapreduce library
import org.apache.hadoop.mapreduce.Reducer;

//List interface instead of string array
import java.util.List;
import java.util.ArrayList;


public class reducer_calculatepagerank extends Reducer<Text,Text,Text,Text>
{
	
	public void reduce(Text key,Iterable<Text> values,Context context) throws IOException,InterruptedException
	{
		List<String> list = new ArrayList<String>();
		
		for(Text value:values)
		{
			list.add(value.toString());
		}
		
		String original_and_contrib = list.toString();
		original_and_contrib = original_and_contrib.substring(1, original_and_contrib.length()-1);
		
		context.write(key,new Text(original_and_contrib));
			
	}
	
	
}
