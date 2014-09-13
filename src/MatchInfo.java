import Jama.Matrix;


public class MatchInfo {
	
	public String homeTeam = "";
	public String awayTeam = "";
	
//	public double recent20Diff;
//	public double recent5Diff;
//	public double winRateDiff;
//	public double rankingDiff;
	
	public Matrix diff;
	public int numStats = 4;
	
	public Outcome gameResult;
	private boolean assigned = false;
	
	public MatchInfo(String home, String away){
		this.homeTeam = home;
		this.awayTeam = away;
		diff = new Matrix(1, numStats, 0);
	}
	
	public MatchInfo(String home, String away, Matrix m, Outcome res){
		this.homeTeam = home;
		this.awayTeam = away;
		diff = m.copy();
		this.gameResult = res;
		assigned = true;
	}
	
	public boolean resultAssigned(){
		return assigned;
	}
	
	public String toString(){
		return homeTeam+" vs "+awayTeam;
	}
}
