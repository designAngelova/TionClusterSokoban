package gameClasses;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author team Cluster
 */

public class StatusLine implements Comparable<StatusLine> {

	//start Singleton pattern
	private static StatusLine instance = null;
	   
	protected StatusLine() {
		setPlayerName("Guest");
		setLevel(1);
		setMoves(0);
		setTime(0);
		getSaveObjects().add(this);
	   }
	  
	public static StatusLine getInstance() {
	      if(instance == null) {
	         instance = new StatusLine();
	      }
	      return instance;
	   }
	//end Singleton pattern
	   
	private String playerName;
	private int level;
	private int moves;
	private int time;
	private static List<StatusLine> saveObjects = new ArrayList<StatusLine>();

	public String getPlayerName() {

		return this.playerName;

	}

	public void setPlayerName(String playerName) {
		if (playerName.equals(" ")) {
			this.playerName = "Guest";
		} else {
			this.playerName = playerName;
		}
	}

	public int getLevel() {

		return this.level;

	}

	public void setLevel(int level) {
		if (level < 1) {
			this.level = 1;
		} else {
			this.level = level;
		}
	}

	public int getMoves() {

		return this.moves;

	}

	public void setMoves(int moves) {
		if (moves < 0) {
			this.moves = 0;
		} else {
			this.moves = moves;
		}
	}

	public int getTime() {

		return this.time;

	}

	public void setTime(int time) {
		if (time < 0) {
			this.time = 0;
		} else {
			this.time = time;
		}
	}

	public static List<StatusLine> getSaveObjects() {
		return saveObjects;
	}

	public String toString() {

		String playerInfo = MessageFormat.format("-== Player: {0}    Level: {1}    Moves: {2}    Time: {3} ==-",
				 this.getPlayerName(), this.getLevel(), this.getMoves(), this.getTime());
		return playerInfo;

	}

	@Override
	public int compareTo(StatusLine sl) {

		if (this.getMoves() < sl.getMoves()) {
			return -1;
		} else if (this.getMoves() > sl.getMoves()) {
			return 1;
		} else {
			if (this.getTime() < sl.getTime()) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public static void sortList(List<StatusLine> sl) {
		Collections.sort(sl);
	}
}
