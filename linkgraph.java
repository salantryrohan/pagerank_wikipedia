package pagerank_mapreduce;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// outputs list the link graph of an wikipedia article
// regular expression matcher used for matching pattern [[some text]]


public class linkgraph 

{
	
	private static void print(Object o)
	{
		System.out.println(o);
	}
	
	// extracts <title, pagerank,list of links> from the article text
	
	public static void extract(String articlename, String articletext)
	{
	//	print(articlename);
		String patternString = "\\[\\[(.+?)\\]\\]";
		List<String> matches = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(articletext);
		
		String outlink = "";
		while(matcher.find())
		{
			outlink = matcher.group(1);
			
			matches.add(outlink);
			
			//print(outlink);
		//	System.out.println();
			
		}
		
		print(matches);
		
	}

}
