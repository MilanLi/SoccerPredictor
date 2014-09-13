import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;


public abstract class Classifier {
	
	protected String type;
	
	protected MatchCenter center;
	protected List<MatchInfo> features;
	protected MatchInfo testingData;
	
	protected List<MatchInfo> winGames;
	protected List<MatchInfo> lossGames;
	
	protected Matrix meanWin;
	protected Matrix meanLoss;
	protected Matrix covWin;
	protected Matrix covLoss;
	
	protected int numStats;
	protected int numWin;
	protected int numLoss;
	protected int num;
	
	protected double decisionStatistics;
	protected Outcome classification;
	
	
	
	public Classifier(MatchCenter center){
		this.center = center;
		features = center.getListOfMatches();
		testingData = center.getTestingData();
		this.numStats = testingData.numStats;
		
		winGames = new ArrayList<MatchInfo>();
		lossGames = new ArrayList<MatchInfo>();
		meanWin = new Matrix(1, numStats, 0);
		meanLoss = new Matrix(1, numStats, 0);
		covWin = new Matrix(numStats, numStats, 0);
		covLoss = new Matrix(numStats, numStats, 0);
		
		
		separateAndCalculateMean();
		
		processData();
	}

	
	protected void separateAndCalculateMean(){
		print("Separate and calculate means......");
		
		for(int i = 0; i < features.size(); i++){
			MatchInfo m = features.get(i);
			if(m.gameResult == Outcome.WIN){
				winGames.add(m);
				meanWin.plusEquals(features.get(i).diff);
			}
			else{
				lossGames.add(m);
				meanWin.plusEquals(features.get(i).diff);
			}
		}
		numWin = winGames.size();
		numLoss = lossGames.size();
		num = numWin + numLoss;
		
		meanWin.times(1/(double)numWin);
		meanLoss.times(1/(double)numLoss);
	}


	protected void processData(){
		print("Calculating covariance for win and loss games......");
		
		for(int i = 0; i < numStats; i++){
			for(int j = 0; j < numStats; j++){
				double cov = 0;
				for(int m = 0; m < numWin; m++){
					Matrix temp = winGames.get(m).diff.minus(meanWin);
					cov += temp.get(1, i) * temp.get(1, j);
				}
				cov /= (double)(numWin-1);
				covWin.set(i, j, cov);
				
				
				double cov2 = 0;
				for(int m = 0; m < numLoss; m++){
					Matrix temp = lossGames.get(m).diff.minus(meanLoss);
					cov2 += temp.get(1, i) * temp.get(1, j);
				}
				cov2 /= (double)(numLoss-1);
				covLoss.set(i, j, cov2);
			}
		}
	}
	
	
	protected void print(String s){
		System.out.println("(---"+type+"---) "+s);
	}
	
	public abstract Outcome classify();
	
	
	
}
