package gameClasses;

import java.text.MessageFormat;

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

	public void drawMenu(Menu menu, String nameOfMenu) {
		this.terminal.clearScreen();
		int colFieldPosModifier = (this.scrColumns - 15) / 2;
		int rowFieldPosModifier = (this.scrRows - menu.getItems().size()) / 2;
		this.terminal.applyForegroundColor(Terminal.Color.CYAN);
		this.terminal.moveCursor((this.scrColumns - nameOfMenu.length()) / 2, rowFieldPosModifier - 4);
		for (int col = 0; col < nameOfMenu.length(); col++) {
			this.terminal.putCharacter(nameOfMenu.charAt(col));
		}
		
		this.terminal.applyForegroundColor(Terminal.Color.RED);
		for (int row = 0; row < menu.getItems().size(); row++) {
			this.terminal.moveCursor(colFieldPosModifier, row + rowFieldPosModifier);
			this.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			if (menu.getItems().get(row).getIsSelected()) {
				this.terminal.applyBackgroundColor(Terminal.Color.YELLOW);
			}
			
			String item = menu.getItems().get(row).getText();
			if (item.length() == 1) {
				item = padRight(padLeft(item, 8), 15);
			} else if(item.length() == 2){
				item = padRight(padLeft(item, 8), 15);
			} else {
				item = padRight(item, 15);
			}
			for (int col = 0; col < item.length(); col++) {
				this.terminal.putCharacter(item.charAt(col));
			}				
		}
		
		this.terminal.applyForegroundColor(Terminal.Color.DEFAULT);
		this.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
	}

	public void drawScores(Scores scores, int level) {
		this.terminal.clearScreen();
		int colFieldPosModifier = (this.scrColumns - 36) / 2;
		int rowFieldPosModifier = (this.scrRows - scores.getList().size()) / 2;
		this.terminal.applyForegroundColor(Terminal.Color.YELLOW);
		
		String levelRow = "SCORES FOR LEVEL " + level;
		this.terminal.moveCursor((this.scrColumns - levelRow.length()) / 2, rowFieldPosModifier - 4);
		for (int col = 0; col < levelRow.length(); col++) {
			this.terminal.putCharacter(levelRow.charAt(col));
		}
		
		String columnRow = "POS  PLAYER        MOVES    TIME   ";
		this.terminal.moveCursor((this.scrColumns - columnRow.length()) / 2, rowFieldPosModifier - 2);
		for (int col = 0; col < columnRow.length(); col++) {
			this.terminal.putCharacter(columnRow.charAt(col));
		}
		
		int infoRow = 0;
		for (int row = 0; row < scores.getList().size(); row++) {
			this.terminal.moveCursor(colFieldPosModifier, row + rowFieldPosModifier);
			String score = MessageFormat.format("{0} - {1}   {2, number, #00000}   {3, time, mm:ss.SSS}",
					padLeft((row + 1) + "", 2), padRight(scores.getList().get(row).getPlayerName(), 10), scores.getList().get(row).getMoves(), scores.getList().get(row).getTime());
			for (int col = 0; col < score.length(); col++) {
				this.terminal.putCharacter(score.charAt(col));
			}	
			
			infoRow = row;
		}
		
		String infoText = "Press Enter to continue...";
		this.terminal.moveCursor((this.scrColumns - infoText.length()) / 2, rowFieldPosModifier + infoRow + 2);
		for (int col = 0; col < infoText.length(); col++) {
			this.terminal.putCharacter(infoText.charAt(col));
		}
		
		this.terminal.applyForegroundColor(Terminal.Color.DEFAULT);
		this.terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
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
	
	private static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	
	private static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}

}
