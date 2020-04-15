package atari_breakout;

public class scoreboard {

	int score = 0;
	int boardY = -100;
	int boardSpeed = 2;
	
	public scoreboard(){
		
	score = 0;	
	boardY = 0;	
	boardSpeed = 2;
	boardY = -100;
	
	}
	
	public void scoreboardmove() {	

		boardY+=boardSpeed;

		if(boardY > 20) {
			
			boardSpeed = 0;
			
			}
		}
	}	
