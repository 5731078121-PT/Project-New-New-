package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logic.StateParsingException;

public class GameSaveUtility {

	public static class GameSaveRecord implements Comparable<GameSaveRecord>{
	
		private String name = "";
		private int state = 0;
		private int star = 0;
		
		public GameSaveRecord(String name, int state, int star) {
			// TODO Auto-generated constructor stub
			this.name = name;
			this.state = state;
			this.star = star;
		}
		
		/*
		 * format name:state;star 
		 */
		
		public GameSaveRecord(String record) throws StateParsingException{
			// TODO Auto-generated constructor stub
			int index1 = record.indexOf(":");
			int index2 = record.indexOf(";");
			if(index1<0) throw new StateParsingException(1);
			try{
				this.name = record.substring(0, index1);
				this.state = Integer.parseInt(record.substring(index1+1, index2));
				this.star = Integer.parseInt(record.substring(index2+1, record.length()));
			}catch(NumberFormatException e){
				throw new StateParsingException(0);
			}
			
		}
		
		private String getRecord(){
			return name.trim() + ":" + state + ";" + star;
		}
		
		private static String[] defaultRecord(){
			return new String[]{"BEE:10;100","FAAI:10;100","GOI:4;33","DA:5;25","GIZ:6;15"};
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
	
	public static void recordData(){
//		already has PLAYER update STATE and STAR;
//		if(!loadPlayerData() || gameSaveRecord == null){
//			JOptionPane.showMessageDialog(new JFrame(), "Error loading statesave record *recordPlayer","Error",JOptionPane.ERROR_MESSAGE);
//			return;
//		}
		
		
		try {
	
			BufferedWriter out = new BufferedWriter(new FileWriter("statesave"));
			//Fill code
			String data = "";
			for(int i = 0; i<gameSaveRecord.length; i++){
				data += gameSaveRecord[i].getRecord();
				System.out.println(gameSaveRecord[i].getRecord());
				data += "\n";
			}
			out.write(data);
			System.out.println("write");
			out.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error saving high score record", 
					"Error", JOptionPane.ERROR_MESSAGE);
			gameSaveRecord = null;
			return;
		}
	}
	
	public static void addPlayer(String name){
		name = name.toUpperCase();
		for(int i = 0; i<gameSaveRecord.length; i++){
			if(gameSaveRecord[i].name.equals("")){
				gameSaveRecord[i].name = name;
				gameSaveRecord[i].star = 10;
				gameSaveRecord[i].state = 1;
				System.out.println("add   "+gameSaveRecord[i].name);
				recordData();
				return;
			}
		}
	}
	
	public static void removePlayer(int i){
		System.out.println("remove");
		gameSaveRecord[i].name = ""; 
	}
	
	public static void updatePlayer(String name, int state, int star){
		for(int i = 0; i<gameSaveRecord.length; i++){
			if(gameSaveRecord[i].name.equals(name)){
				gameSaveRecord[i].state = state;
				gameSaveRecord[i].star = star;
				break;
			}
		} 
	}
	
	public static String updateData(){
		String msg = "";
		
		for(GameSaveRecord record : gameSaveRecord){
			msg += record.getRecord()+ System.getProperty("line.separator");
		}
		return msg;
	}
	
	public static String displayPlayer(){
		if(!loadPlayerData() || gameSaveRecord == null){
	
			JOptionPane.showMessageDialog(new JFrame(), "Error loading statesave record","Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		String msg = "";
		
		for(GameSaveRecord record : gameSaveRecord){
			msg += record.getRecord()+ System.getProperty("line.separator");
		}
		return msg;
		
	}
	
	public static boolean loadPlayerData(){
		File f = new File(readFileName);
		if(!f.exists()){
			if(!createDefaultDataFile()) return false;
		}
		if(!readAndParsePlayerFile(f)){
			
			f.delete();
			if(!createDefaultDataFile()) return false;
			return readAndParsePlayerFile(f);
		}
		return true;
	}
	
	private static boolean readAndParsePlayerFile(File f){
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			gameSaveRecord = new GameSaveRecord[5];
			String str = "";
			int c;
			while((c = in.read()) != -1){
				str += (char)c;
			}
			in.close();
			String[] records = str.split("\n");
//			System.out.println("    " + gameSaveRecord.length);
			for(int i = 0; i<gameSaveRecord.length; i++){
				try{
					gameSaveRecord[i] = new GameSaveRecord(records[i]);
//					System.out.println(gameSaveRecord[i]);
				}catch(StateParsingException e){
//					System.out.println("err");
					System.err.println("Error parsing line " + (i+1) + ", " + e.getMessage());
					gameSaveRecord[i] = new GameSaveRecord("ERROR_RECORD", 0, 0);
				}
			}
//			System.out.println(gameSaveRecord+ " BEE ");
			Arrays.sort(gameSaveRecord);
			return true;
		}catch(Exception e){
			gameSaveRecord = null;
		}
		return false;
	}
	
	private static boolean createDefaultDataFile(){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter("statesave"));
			String str = "";
			for(String s : GameSaveRecord.defaultRecord()){
				str += s+"\n";
			}
			str = str.trim();
			out.write(str);
			out.close();
			return true;
		}catch (IOException e){
			gameSaveRecord = null;
			return false;
		}
	}
	
	public static void setReadFileName(String name){
		readFileName = name;
	}
	
}
