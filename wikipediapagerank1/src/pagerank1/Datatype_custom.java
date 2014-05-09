package pagerank1;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
// custom Datatype for Value 
// (title, pageRank, outlinks)

public class Datatype_custom implements Writable{
	
	FloatWritable pagerank;
	Text outlinks;
	
	public Datatype_custom()
	{
		
	}
	
	public Datatype_custom(float pr,String outlinks)
	{
		this.pagerank = new FloatWritable(pr);
		this.outlinks = new Text(outlinks);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		pagerank.readFields(in);
		outlinks.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		pagerank.write(out);
		pagerank.write(out);
	}
	
	public String toString() {
		
		
	    return "("+Float.toString(pagerank.get())+","+outlinks.toString()+")";
	  }

	

}
