package gameClasses;

import com.googlecode.lanterna.terminal.Terminal;

public class LanternaDrawer {
	public void drawGameField(GameField field) {
		Terminal terminal = GameTerminal.getInstance().getTerminal();
		terminal.clearScreen();
		char[][] matrix = field.getFieldMatrix();
		for (int row = 0; row < matrix.length; row++) {
			terminal.moveCursor(10, row + 5);
			for (int col = 0; col < matrix[0].length; col++) {
				char pattern = '#';
				switch (field.getFieldMatrix()[row][col]) {
				case 'W':
					pattern = ' ';
					terminal.applyBackgroundColor(Terminal.Color.RED);
					break;
				case 'B':
					pattern = '\u2588';
					terminal.applyForegroundColor(Terminal.Color.GREEN);
					break;
				case 'G': {
					pattern = 'X';
					terminal.applyForegroundColor(Terminal.Color.YELLOW);
				}
					break;
				case 'X': {
					pattern = 'X';
					terminal.applyBackgroundColor(Terminal.Color.GREEN);
					terminal.applyForegroundColor(Terminal.Color.RED);
				}
					break;
				case 'P':
					pattern = '\u263a';
					break;
				case 'D':
					pattern = '\u263a';
					terminal.applyForegroundColor(Terminal.Color.YELLOW);
					break;
				default:
					pattern = ' ';
					terminal.applyBackgroundColor(Terminal.Color.BLACK);
					break;
				}
				terminal.putCharacter(pattern);
				terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
				terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			}
		}
	}

	public void drawMenu(Scores menu) {

	}

	public void drawScores(Scores scores) {

	}

	public void drawSatusLine() {
		String name = StatusLine.getInstance().getPlayerName();
		int level = StatusLine.getInstance().getLevel();
		int moves = StatusLine.getInstance().getMoves();
		int time = StatusLine.getInstance().getTime();
		String statusLine = "-== Player: " + name + "  -  Level: " + level
				+ "  -  Moves: " + moves + "  -  Time: " + time + " ==-";
		
	}
}
