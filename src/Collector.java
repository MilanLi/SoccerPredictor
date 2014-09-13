import java.util.ArrayList;
import java.util.List;


public class Collector {
	
	private List<String> rankings;
	
	
	public MatchInfo collectMatchInfo(String home, String away, String URL){
		print("Collecting match information from "+URL+" ......");
		MatchInfo mi = new MatchInfo(home, away);
		print("Match information collected: "+ mi);
		return mi;
	}
	
	private void collectRankings(String URL){
		print("Collecting rankings from "+URL+" ......");
		rankings = new ArrayList<String>();
		print("Rankings collected!");
	}
	
	private void print(String s){
		System.out.println("(---Collector---) "+s);
	}
}
