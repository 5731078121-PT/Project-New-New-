package utility;

import logic.StateParsingException;

public class GameSaveUtility {

	public static class GameSaveRecord implements Comparable<GameSaveRecord>{
	
		private String name = "";
		private int state = 0;
		
		public GameSaveRecord(String name, int state) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.state = state;
		}
		
		public GameSaveRecord(String record) throws StateParsingException{
			// TODO Auto-generated constructor stub
			int index = record.indexOf(":");
			if(index<0) throw new StateParsingException(1);
			try{
				this.name = record.substring(0, index);
				this.state = Integer.parseInt(record.substring(index+1, record.length()));
				
			}catch(NumberFormatException e){
				throw new StateParsingException(0);
			}
			
		}
		
		private String getRecord(){
			return name.trim() + ":" + state;
		}
		
		private static String[] defaultRecord(){
			return new String[]{"BEE:10","FAAI:10"};
		}
		
		
		@Override
		public int compareTo(GameSaveRecord o) {
			// TODO Auto-generated method stub
			if(this.state < o.state) return 1;
			else if (this.state > o.state) return -1;
			else return this.name.compareTo(o.name);
		}
	}
	
	private static GameSaveRecord[] gameSaveRecord = null;
	private static String readFileName = "statesave";
	
	
	
}
