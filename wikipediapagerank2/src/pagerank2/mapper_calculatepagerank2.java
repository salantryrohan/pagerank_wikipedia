package pagerank2;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.IOException;

// hadoop imports
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.ArrayWritable;
// mapper class from mapreduce library
import org.apache.hadoop.mapreduce.Mapper;

public class mapper_calculatepagerank2 extends Mapper<LongWritable,Text,Text,Text>
{
	
	private static void print(Object o)
	{
		System.out.println(o);
	}
	
	
	@Override
	public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException
	{
		String row = value.toString();
		
		
		//String row = "PowerBook	[(CONTRIBUTION,1.0f,448)]";
		String[] record = row.split("\t");
		// article
		String articlename = record[0];
		
		// contribution and original record
		
		//String patternString = "\\[\\[([a-zA-Z0-9_\\-\\s]+)(\\|)?([a-zA-Z0-9_\\-\\s]+)?\\]\\]";
		//Pattern pattern = Pattern.compile("\\(\\((.*)\\)\\)");
		Pattern pattern = Pattern.compile("\\(\\((.+?)\\)\\)");
		
		Matcher matcher = pattern.matcher(row);
		
		String match;
		String[] matchresults;
		float pagerank = 0.0f;
		String originallinkarr = "";
		String[] results = null;;
		while(matcher.find())
		{
			 match = matcher.group(1);
			 print(match);
			 
			 results = match.split("\t");
			 
			 if(results[0].equals("CONTRIBUTION"))
			 {
				 //print("contribution");
				pagerank  = pagerank + Float.parseFloat(results[1])/Float.parseFloat(results[2]);
			 }
			 
			 if(results[0].equals("ORIGINAL"))
			 {
				originallinkarr = results[1];
			 }
			 
			 
		}
		
			String pagerank_linkarr = Float.toString(pagerank)+"\t"+originallinkarr;

			context.write(new Text(articlename), new Text(pagerank_linkarr));
			
		
	}
	
	
	
	public static void main(String[] args)
	{
		String test = "Anderson Cooper	((CONTRIBUTION	1.0f	18))";
		String test1 = "Andromeda (mythology)	((ORIGINAL	Wallace Collection, Titian, tragedies, Pierre Corneille, Euripides, Sophocles, Pompeii, Seriphos, Polydectes, fisherman, Dictys, Medusa, Cetus, sea monster, Polaris, ecliptic, Andromeda Galaxy, constellation, Corinth, Pierre Corneille, Euripides, Sophocles, constellation, Athena, Persian people, Heracles, Atreus, Eurystheus, Electryon, Gorgophone, Electryon, Mestor, Heleus, Perses, Perseides, Argos, Tiryns, Ovid, Phineus, Medusa, Gorgon, Jaffa, Oracle, Zeus, Poseidon, Nereus, nymph, Nereids, Greek mythology, Giorgio Vasari, Ovid, Renaissance, St George and the Dragon, etymology, Latin language, Perseus, sea monster, Greek mythology, princess, Edward Poynter, Edith Hamilton, Ovid, Bibliotheke, Apollodorus, Edward Burne-Jones, Domenico Guidi, Pierre Mignard, Joachim Wtewael, Wallace Collection, Titian, Israel, Jaffa, Judi Bowker, Kraken, Medusa, Achilles, Thetis, Gustave Moreau, Ingres, Rubens, Veronese, Louvre, Joachim Wtewael))";
		
		String[] row = test1.split("\t");
		System.out.println(row[0]);
		Pattern pattern = Pattern.compile("\\(\\((.*)\\)\\)");
		Matcher matcher = pattern.matcher(test1);
		String match = "";
		
		while(matcher.find())
		{
			match = matcher.group(1);
			String[] results = match.split("\t");
			for(String x:results){
				System.out.println(x);
			}
		}
		
		
	}

}
