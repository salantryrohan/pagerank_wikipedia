package pagerank;
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


public class reducer_extractlinks extends Reducer<Text,Valuetuple,Text,Valuetuple>
{
	
	public void reduce(Text key,Iterable<Valuetuple> values,Context context) throws IOException,InterruptedException
	{
		List<String> links = new ArrayList<String>();
		
		Valuetuple art_pr_links = null;
		
		for(Valuetuple value:values)
		{
			art_pr_links = value;
			break;
		}
		
		String outlinks = links.toString();
		outlinks = outlinks.substring(1, outlinks.length()-1);
		
		// key = articlename, value = [link1,link2,.....]
		context.write(key,art_pr_links);
			
	}
	
	
}
