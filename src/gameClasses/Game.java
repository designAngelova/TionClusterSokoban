package gameClasses;

import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class Game {
	private Terminal terminal;
	private StatusLine statusLine;
	private LanternaDrawer drawer;
	private GameField field;
	private String gameMenu;
	private String nextMenu;
	private String levelMenu;
	private String scores;
	private GameController controller;

	public Game() {
		this.terminal = GameTerminal.getInstance().getTerminal();
		this.drawer = new LanternaDrawer();
		this.field = new GameField();
		this.controller = new KeyboardController();
		this.statusLine = StatusLine.getInstance();
	}

	public void runGame() {
		this.field.loadLevel(1);
		this.terminal.enterPrivateMode();
		this.terminal.setCursorVisible(false);
		Controls key = controller.getAction();
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
			key = controller.getAction();
			field.move(key);
			this.drawer.drawGameField(field);
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
