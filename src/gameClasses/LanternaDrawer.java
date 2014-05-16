package gameClasses;

import com.googlecode.lanterna.terminal.Terminal;

public class LanternaDrawer {
	private Terminal terminal = GameTerminal.getInstance().getTerminal();
	private int scrColumns = this.terminal.getTerminalSize().getColumns();
	private int scrRows = this.terminal.getTerminalSize().getRows();
	private StatusLine statusLine = StatusLine.getInstance();

	public void drawGameField(GameField field) {
		this.terminal.clearScreen();
		char[][] matrix = field.getFieldMatrix();
		int colFieldPosModifier = (this.scrColumns - matrix[0].length) / 2;
		int rowFieldPosModifier = (this.scrRows - matrix.length) / 2;
		for (int row = 0; row < matrix.length; row++) {
			this.terminal.moveCursor(colFieldPosModifier, row + rowFieldPosModifier);
			for (int col = 0; col < matrix[0].length; col++) {
				char pattern = '#';
				switch (field.getFieldMatrix()[row][col]) {
				case 'W':
					pattern = ' ';
					this.terminal.applyBackgroundColor(Terminal.Color.RED);
					break;
				case 'B':
					pattern = '\u2588';
					this.terminal.applyForegroundColor(Terminal.Color.GREEN);
					break;
				case 'G': {
					pattern = 'X';
					this.terminal.applyForegroundColor(Terminal.Color.YELLOW);
				}
					break;
				case 'X': {
					pattern = 'X';
					this.terminal.applyBackgroundColor(Terminal.Color.GREEN);
					this.terminal.applyForegroundColor(Terminal.Color.RED);
				}
					break;
				case 'P':
					pattern = '\u263a';
					break;
				case 'D':
					pattern = '\u263a';
					this.terminal.applyForegroundColor(Terminal.Color.YELLOW);
					break;
				default:
					pattern = ' ';
					this.terminal.applyBackgroundColor(Terminal.Color.BLACK);
					break;
				}
				this.terminal.putCharacter(pattern);
				this.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
				this.terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			}
		}

		int colLinePosModifier = (this.scrColumns - StatusLine
				.getInstance().toString().length()) / 2;

		this.terminal.moveCursor(colLinePosModifier, matrix.length
				+ rowFieldPosModifier + 1);
		this.terminal.putCharacter('\u2554');
		for (int i = 0; i < this.statusLine.toString().length(); i++) {
			this.terminal.putCharacter('\u2550');
		}
		this.terminal.putCharacter('\u2557');
		this.terminal.moveCursor(colLinePosModifier, matrix.length
				+ rowFieldPosModifier + 2);
		this.terminal.putCharacter('\u2551');
		this.statusLine.setTimeRow(matrix.length + rowFieldPosModifier + 2);
		this.statusLine.setTimeCol(colLinePosModifier + this.statusLine.toString().indexOf("Time: ") + 7);
		drawSatusLine();
		this.terminal.putCharacter('\u2551');
		this.terminal.moveCursor(colLinePosModifier, matrix.length
				+ rowFieldPosModifier + 3);
		this.terminal.putCharacter('\u255a');
		for (int i = 0; i < this.statusLine.toString().length(); i++) {
			this.terminal.putCharacter('\u2550');
		}
		this.terminal.putCharacter('\u255d');
		
		String info = "Arrows - control player,  Q - quit to menu,  U - undo";
		int colInfoPosModifier = (this.scrColumns - info.length()) / 2;

		this.terminal.moveCursor(colInfoPosModifier, matrix.length
				+ rowFieldPosModifier + 4);
		drawInfo(info);
	}

	public void drawMenu(Scores menu) {

	}

	public void drawScores(Scores scores) {

	}

	private void drawSatusLine() {
		String statusLine = this.statusLine.toString();
		for (int i = 0; i < statusLine.length(); i++) {
			this.terminal.putCharacter(statusLine.charAt(i));
		}
	}

	private void drawInfo(String info) {
		for (int i = 0; i < info.length(); i++) {
			this.terminal.putCharacter(info.charAt(i));
		}
	}
}
