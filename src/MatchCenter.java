import java.util.List;

public class MatchCenter {
	private List<MatchInfo> listOfMatches;
	private SQL mySQL;
	private Classifier classifier;
	private MatchInfo matchToPredict;
	private boolean newMatch = false;
	
	public MatchCenter(){
		
		mySQL = new SQL();
		print("Loading data from SQL......");
		listOfMatches = mySQL.getMatches();
		
		
	}
	
	
	public void loadCurrentGame(MatchInfo mi){
		matchToPredict = mi;
		newMatch = true;
		print("Current game loaded: "+matchToPredict);
	}
	
	public Outcome estimateResult(){
		print("Setting up classifier......");
		classifier = new Bayes(this);
		print("Estimating result of "+matchToPredict+" ......");
		return classifier.classify();
	}
	
	
	public void storeMatchResult(){
		print("Connecting MySQL......");
		if(newMatch){
			mySQL.addMatch(matchToPredict);
			print("Stored in MySQL already: "+matchToPredict);
		}
		else{
			print("Please load a new match before store it twice");
		}
		
	}
	
	public List<MatchInfo> getListOfMatches(){
		return listOfMatches;
	}
	
	public MatchInfo getTestingData(){
		return matchToPredict;
	}
	
	private void print(String s){
		System.out.println("(---MatchCenter---) "+s);
	}
}
