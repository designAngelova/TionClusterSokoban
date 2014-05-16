package gameClasses;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

public class LanternaDrawer {
	private Terminal terminal = GameTerminal.getInstance().getTerminal();
	TerminalSize screenSize = terminal.getTerminalSize();

	public void drawGameField(GameField field) {
		terminal.clearScreen();
		char[][] matrix = field.getFieldMatrix();
		int colFieldPosModifier = (screenSize.getColumns() - matrix[0].length) / 2;
		int rowFieldPosModifier = (screenSize.getRows() - matrix.length) / 2;
		for (int row = 0; row < matrix.length; row++) {
			terminal.moveCursor(colFieldPosModifier, row + rowFieldPosModifier);
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

		int colLinePosModifier = (screenSize.getColumns() - StatusLine
				.getInstance().toString().length()) / 2;

		terminal.moveCursor(colLinePosModifier, matrix.length
				+ rowFieldPosModifier + 1);
		terminal.putCharacter('\u2554');
		for (int i = 0; i < StatusLine.getInstance().toString().length(); i++) {
			terminal.putCharacter('\u2550');
		}
		terminal.putCharacter('\u2557');
		terminal.moveCursor(colLinePosModifier, matrix.length
				+ rowFieldPosModifier + 2);
		terminal.putCharacter('\u2551');
		drawSatusLine();
		terminal.putCharacter('\u2551');
		terminal.moveCursor(colLinePosModifier, matrix.length
				+ rowFieldPosModifier + 3);
		terminal.putCharacter('\u255a');
		for (int i = 0; i < StatusLine.getInstance().toString().length(); i++) {
			terminal.putCharacter('\u2550');
		}
		terminal.putCharacter('\u255d');
		
		String info = "Arrows - control player,  Q - quit to menu,  U - undo";
		int colInfoPosModifier = (screenSize.getColumns() - info.length()) / 2;

		terminal.moveCursor(colInfoPosModifier, matrix.length
				+ rowFieldPosModifier + 4);
		drawInfo(info);
	}

	public void drawMenu(Scores menu) {

	}

	public void drawScores(Scores scores) {

	}

	private void drawSatusLine() {
		String statusLine = StatusLine.getInstance().toString();
		for (int i = 0; i < statusLine.length(); i++) {
			terminal.putCharacter(statusLine.charAt(i));
		}
	}

	private void drawInfo(String info) {
		for (int i = 0; i < info.length(); i++) {
			terminal.putCharacter(info.charAt(i));
		}
	}
}
