package gameClasses;

import java.io.*;
import java.util.*;

/**
 *
 * @author team Cluster
 *
 */

public class Scores {

	private List<StatusLine> scores;

	public Scores() {
		this.scores = new ArrayList<StatusLine>();
	}

	public List<StatusLine> getList() {
		return this.scores;
	}

	public void loadScores(int level) throws Exception {
		this.scores.clear();
		File scoreFile = new File("scores" + level + ".txt");

		try {
		    scoreFile.createNewFile();
		} catch (IOException ioe) {
		     System.out.println("Error while Creating File in Java" + ioe);
		}
		FileReader fr = new FileReader("scores" + level + ".txt");
		BufferedReader br = new BufferedReader(fr);
		int numberOfLines;
		numberOfLines = countLines(level);
		for (int i = 0; i < numberOfLines; i++) {

			String line = br.readLine();
			StatusLine temp = new StatusLine();
			String[] parts = line.split(" ");

			temp.setLevel(level);
			temp.setPlayerName(parts[0]);
			temp.setMoves(Integer.parseInt(parts[2]));
			temp.setTime(Long.parseLong(parts[1]));
			this.scores.add(temp);
		}
		
		br.close();
	}

	public int countLines(int level) throws IOException {
		FileReader fr = new FileReader("scores" + level + ".txt");
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

	public void addScore() throws Exception {
		StatusLine status = StatusLine.getInstance();
		this.scores.clear();
	    loadScores(status.getLevel());
		this.scores.add(status);
		sortScores();
		if (this.scores.size() > 10) {
			this.scores.remove(10);
		}
		
		System.out.println(StatusLine.getInstance().getLevel());
		System.out.println(status.getLevel());

		saveScores();
	}

	public void saveScores() {

		try {
			int level = StatusLine.getInstance().getLevel();
			File scoreFile = new File("scores" + level + ".txt");

			try {
			    scoreFile.createNewFile();
			} catch (IOException ioe) {
			     System.out.println("Error while Creating File in Java" + ioe);
			}

			BufferedWriter out = new BufferedWriter(new FileWriter("scores"
					+ level + ".txt"));
			for (int i = 0; i < this.scores.size(); i++) {
				if (this.scores.get(i).getLevel() == level) {
					out.write(this.scores.get(i).toScoreString());
					out.newLine();
				}

			}

			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sortScores() {
		Collections.sort(this.scores);
	}
}
