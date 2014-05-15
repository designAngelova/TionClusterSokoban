package gameMain;

import gameClasses.Controls;
import gameClasses.GameController;
import gameClasses.GameField;
import gameClasses.KeyboardController;

import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class SokobanGame {

	public static void main(String[] args) {
		GameField field = new GameField();
		field.loadLevel(1);
		 Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		 terminal.enterPrivateMode();
		 terminal.setCursorVisible(false);
		 GameController gameController = new KeyboardController(terminal);
		 Controls key = gameController.getAction();
		 while (key != Controls.EXIT) {
			 key = gameController.getAction();
			 field.move(key); 
			 drawGameField(field, terminal);
			 
		}		    
		 terminal.exitPrivateMode();
	}
	
	public static void sleep(int msec){
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		 
	}
	
	public static void drawGameField(GameField field, Terminal terminal){
		terminal.clearScreen();
		char[][] matrix = field.getFieldMatrix();
		 for (int row = 0; row < matrix.length; row++) {
			 terminal.moveCursor(10, row + 5);
			for (int col = 0; col < matrix[0].length; col++) {
				char pattern = '#';
				switch (field.getFieldMatrix()[row][col]) {
				case 'W': pattern = ' '; terminal.applyBackgroundColor(Terminal.Color.RED);					
					break;
					case 'B': pattern = '\u2588'; terminal.applyForegroundColor(Terminal.Color.GREEN);
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
					case 'P': pattern = '\u263a'; 
					break;
					case 'D': pattern = '\u263a'; terminal.applyForegroundColor(Terminal.Color.YELLOW);
					break;
				default: pattern = ' '; terminal.applyBackgroundColor(Terminal.Color.BLACK);
					break;
				}
				terminal.putCharacter(pattern);
				terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
				terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			}
		}
	}
}
