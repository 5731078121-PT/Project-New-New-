package logic;

public class RandomUtility {

	public RandomUtility() {
		// TODO Auto-generated constructor stub
	}
	
	public static int random(int start, int stop){
		return (int) (start + (Math.random()*(stop-start+1)));
	}

}
