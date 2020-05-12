package battleGame;

public class clouds {

	int cloud1start = 50, cloud2start = -800, cloud3start = -1000;
	
	public clouds() {
		
	cloud1start = 50; 
	cloud2start = -800; 
	cloud3start = -1000;	
		
	}
	
	public void move() {
		
		cloud1start+=2;
		cloud2start+=2;
		
		if(cloud1start == 2000) {			
			cloud1start = -90;			
		}
		
		if(cloud2start == 2000) {			
			cloud2start = -100;			
		}

		if(cloud3start == 2000) {			
			cloud3start = -1600;			
		}		
		
	}
	
}
