package game;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author team Cluster
 *
 */

public class Scores {

	private List<StatusLine> scores;

	public Scores() {
		setList();
	}

	public void setList() {
		scores = new ArrayList<StatusLine>();
		
	}
	public List<StatusLine> getList() {
		return this.scores;
	}

	public void loadScores(int level) throws IOException {
		String[] statusLines = new String[10];
		statusLines = loadStatusLines(level);
		for(int i = 0; i < statusLines.length; i++) {
	
			StatusLine temp = new StatusLine();
			String[] parts = statusLines[i].split(" ");
			
			temp.setLevel(level);
			temp.setPlayerName(parts[0]);
			temp.setMoves(Integer.parseInt(parts[2]));
			temp.setTime(Long.parseLong(parts[1]));
			
			  try
	        {
	            Date time = simpleDateFormat.parse(parts[1]);
	          }
	        catch (Exception ex)
	        {
	            System.out.println("Exception "+ex);
	        }
	        // temp.setTime(...); help!
			addScore(temp);
		}
	}
	
	public String[] loadStatusLines(int level) throws IOException {
		FileReader fr = new FileReader("scores"+ level + ".txt");
		BufferedReader br = new BufferedReader(fr);

		int numberOfLines = countLines(level);
		String[] playerInfoRead = new String[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			playerInfoRead[i] = br.readLine();
		}
		br.close();
		return playerInfoRead;
	}

	public int countLines(int level) throws IOException {
		FileReader fr = new FileReader("scores"+ level + ".txt");
		BufferedReader br = new BufferedReader(fr);

		String aLine = br.readLine();
		int numberOfLines = 0;

		while ((aLine) != null) {
			numberOfLines++;
			aLine = br.readLine();
		}
		br.close();
		return numberOfLines;
	}
	

	public void addScore(StatusLine status) {

		scores.add(status);
        StatusLine.sortList(scores);
        if (scores.size() > 10) {
        	scores.remove(10);
        }
	}

	public void saveScores(int level) {

		try {
			
			BufferedWriter out = new BufferedWriter(new FileWriter("scores"+ level + ".txt"));
			for (int i = 0; i < scores.size(); i++) {
				if (scores.get(i).getLevel() == level) {
					out.write(scores.get(i).toScoreString());
					out.newLine();
				}
				
			}
			
			out.flush();
			out.close();
			loadScores(level);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
