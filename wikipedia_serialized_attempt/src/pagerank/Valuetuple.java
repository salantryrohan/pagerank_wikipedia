package pagerank;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.FloatWritable;
// custom Datatype for Value 
// (title, pageRank, outlinks)

public class Valuetuple implements Writable
{
	
	float pagerank;
	List<String> outlinks;
	
	public Valuetuple()
	{
		this.pagerank = 1.0f;
		this.outlinks = null;
	}
	
	public Valuetuple(float pr,List<String> outlinks)
	{
		this.pagerank = pr;
		this.outlinks = outlinks;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException 
	{
		// TODO Auto-generated method stub
	    // custom serializing code for ValueTuple class
		this.pagerank = in.readFloat();
		String outlinksstr = in.readUTF();
		
		String[] temparr = outlinksstr.substring(1,outlinksstr.length()-1).split(",");
		
		this.outlinks = Arrays.asList(temparr);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeFloat(this.pagerank);
		out.writeUTF(outlinks.toString());
		
	}
	
	public void set(float pr,List<String> outlinks)
	{
		this.pagerank = pr;
		this.outlinks = outlinks;
	}
	
	
	public String toString() {
		
		return "["+Float.toString(pagerank)+","+this.outlinks.toString()+"]";
	   
	  }

	

}
