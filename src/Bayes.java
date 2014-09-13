import Jama.Matrix;


public class Bayes extends Classifier {

	private double winRate;
	private double lossRate;
	
	
	public Bayes(MatchCenter center){
		super(center);
		type = "Bayes";
	}
	
	protected void processData(){
		super.processData();
		winRate = (double)numWin/(double)num;
		lossRate = (double)numLoss/(double)num;
	}
	
	@Override
	public Outcome classify() {
		print("Classifying with Bayes Classifier......");
		
		Matrix d = testingData.diff;
		Matrix m1 = d.minus(meanWin);
		Matrix m2 = d.minus(meanLoss);
		decisionStatistics = 0.5*(2*Math.log(winRate)-(m1.times(covWin.inverse()).times(m1.transpose())).det()-Math.log(covWin.det()))
							-0.5*(2*Math.log(lossRate)-(m2.times(covLoss.inverse()).times(m2.transpose())).det()-Math.log(covLoss.det()));
		
		return (decisionStatistics >= 0.5)? Outcome.WIN : Outcome.LOSS;
	}
	
	
	

}
