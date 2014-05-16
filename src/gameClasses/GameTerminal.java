package gameClasses;

import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class GameTerminal {

	private Terminal terminal;
	private static GameTerminal instance = null;

	protected GameTerminal() {
		this.terminal = TerminalFacade.createTerminal(System.in,
				System.out, Charset.forName("UTF8"));
	}

	public static GameTerminal getInstance() {
		if (instance == null) {
			instance = new GameTerminal();
		}
		return instance;
	}

	public Terminal getTerminal() {
		return terminal;
	}
}
