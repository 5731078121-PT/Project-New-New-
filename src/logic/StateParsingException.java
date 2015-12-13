package logic;

public class StateParsingException extends Exception{
	private int errorType;
	
	public StateParsingException(int errorType) {
		// TODO Auto-generated constructor stub
		this.errorType = errorType;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		if(this.errorType == 0) return "No Record data";
		else if(this.errorType == 1) return "Wrong record format";
		else return null;
	}
}
