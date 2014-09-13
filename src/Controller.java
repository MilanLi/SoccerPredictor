import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Controller {
	
	private Collector collector;
	private MatchCenter center;
	private boolean running;
	
	BufferedReader br;

	
	public Controller(){
		print("Collecting Data from Internet and storing it in SQL......");
		collector = new Collector();
		center = new MatchCenter();
		br = new BufferedReader(new InputStreamReader(System.in));
		
		running = true;
		run();
	}
	
	private void run(){
		while(running){
			String home = promptString("home");
			String away = promptString("away");
			if(home.equals("") || away.equals("")){
				print("Finish predicting......");
				return;
			}
			MatchInfo m = collectMatch(home, away);
			center.loadCurrentGame(m);
			Outcome o = center.estimateResult();
			print("The result for "+m+" is "+o);
		}
	}
	
	private String promptString(String s){
		System.out.print("Please input "+ s+" team:");
		
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
	public MatchInfo collectMatch(String home, String away){
		return collector.collectMatchInfo(home, away, "abc");
	}
	
	
	
	private void print(String s){
		System.out.println("(---Collector---) "+s);
	}
}
