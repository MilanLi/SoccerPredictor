import java.util.ArrayList;
import java.util.List;


public class SQL {

	List<MatchInfo> matches;
	
	public SQL(){
		matches = new ArrayList<MatchInfo>();
	}
	
	public void addMatch(MatchInfo matchToPredict) {
		matches.add(matchToPredict);
		print("Added match in SQL.");
	}
	
	public List<MatchInfo> getMatches(){
		print("Fetch matches in SQL.");
		return matches;
	}
	
	public void print(String s){
		System.out.println("(---SQL---) "+s);
	}
	
}
