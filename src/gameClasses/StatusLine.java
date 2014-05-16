package game;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author team Cluster
 */

public class StatusLine implements Comparable<StatusLine> {

	//start Singleton pattern
	private static StatusLine instance = null;

	protected StatusLine() {
		setTimeRow(0);
		setTimeCol(0);
		setPlayerName("Guest");
		setLevel(1);
		setMoves(0);
		setTime(0);

	   }

	public static StatusLine getInstance() {
	      if(instance == null) {
	         instance = new StatusLine();
	      }
	      return instance;
	   }
	//end Singleton pattern

	private int timeRow;
	private int timeCol;
	private String playerName;
	private int level;
	private int moves;
	private long time;
	

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

	public long getTime() {

		return this.time;

	}

	public void setTime(long time) {
			this.time = time;
	}

	public String toString() {

		String playerInfo = MessageFormat.format("-== Player: {0}    Level: {1, number, #00}    Moves: {2, number, #00000}    Time: {3, time, mm:ss.SSS} ==-",
				 this.getPlayerName(), this.getLevel(), this.getMoves(), new Date().getTime() - this.getTime());
		return playerInfo;

	}
	
	public String toScoreString() {
		
			String playerInfo = String.format("%s %d %d",
					 this.getPlayerName(), new Date().getTime() - this.getTime(),
					this.getMoves());
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

	public int getTimeRow() {
		return timeRow;
	}

	public void setTimeRow(int timeRow) {
		this.timeRow = timeRow;
	}

	public int getTimeCol() {
		return timeCol;
	}

	public void setTimeCol(int timeCol) {
		this.timeCol = timeCol;
	}
}
