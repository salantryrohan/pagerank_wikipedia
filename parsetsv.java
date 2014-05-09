package pagerank_mapreduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;



public class parsetsv {

	private static void print(Object o)
	{
		System.out.println(o);
	}
	
	public static void main(String[] args)
	{
		try 
		{
			//CSVReader reader = new CSVReader(new FileReader("/Users/rohansalantry/Desktop/bigdatauniv/wikidump/pagerank_mapreduce/src/pagerank_mapreduce/wikipedia.tsv"),'\t');
			FileReader filereader = new FileReader("/Users/rohansalantry/Desktop/bigdatauniv/wikidump/pagerank_mapreduce/src/pagerank_mapreduce/wikipedia.tsv");
			BufferedReader br = new BufferedReader(filereader);
			//String[] nextLine;
			String nextLine;
			
			int count = 0;
			
			String[] fields;
			//while ((nextLine = reader.readNext()) != null)
			while((nextLine = br.readLine()) != null)
			{
				 fields = nextLine.split("\t");
				 
				if(fields[2].equals("article"))
				{
					linkgraph.extract(fields[1],fields[3]);
				}
				
				//print(Arrays.asList(nextLine));
				count++;
				if(count == 4)
					break;
				
			}
		
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
}
