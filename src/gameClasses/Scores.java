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
		setList();
	}

	public void setList() {
		scores = new ArrayList<StatusLine>();
		scores = StatusLine.getSaveObjects();
	}

	public String[] loadScores(String currentPath) throws IOException {
		FileReader fr = new FileReader(currentPath);
		BufferedReader br = new BufferedReader(fr);

		int numberOfLines = countLines(currentPath);
		String[] playerInfoRead = new String[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			playerInfoRead[i] = br.readLine();
		}
		br.close();
		return playerInfoRead;
	}

	public int countLines(String path) throws IOException {
		FileReader fr = new FileReader(path);
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

	}

	public void saveScores(int level) {

		try {
			StatusLine.sortList(scores);
			BufferedWriter out = new BufferedWriter(new FileWriter("scores"
					+ level + ".txt"));
			for (int i = 0, j = 0; i < scores.size(); i++, j++) {
				if (scores.get(i).getLevel() == level) {
					out.write(scores.get(i).toString());
					out.newLine();
				}
				if (j == 9) {
					break;
				}
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
