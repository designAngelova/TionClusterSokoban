package gameClasses;

import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class Game {
	private Terminal terminal;
	private StatusLine statusLine;
	private String drawer;
	private GameField field;
	private String gameMenu;
	private String nextMenu;
	private String levelMenu;
	private String scores;
	private GameController controller;

	public Game() {
		this.terminal = GameTerminal.getInstance().getTerminal();
	}

	public void runGame() {
		LanternaDrawer drawer = new LanternaDrawer();
		GameField field = new GameField();
		field.loadLevel(1);
		this.terminal.enterPrivateMode();
		this.terminal.setCursorVisible(false);
		GameController gameController = new KeyboardController(this.terminal);
		Controls key = gameController.getAction();
		while (key != Controls.EXIT) {
			if (field.checkIsSolved()) {
				this.terminal.clearScreen();
				this.terminal.putCharacter('B');
				this.terminal.putCharacter('R');
				this.terminal.putCharacter('A');
				this.terminal.putCharacter('V');
				this.terminal.putCharacter('O');
				this.terminal.putCharacter('!');
				this.terminal.putCharacter('!');
				this.terminal.putCharacter('!');
				sleep(50000);
				break;
			}
			key = gameController.getAction();
			field.move(key);
			drawer.drawGameField(field);
		}
		this.terminal.exitPrivateMode();
	}

	public static void sleep(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
}
