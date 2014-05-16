package gameClasses;
import java.nio.charset.Charset;



import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class KeyboardController extends GameController {
	private Terminal terminal;

	public KeyboardController(Terminal terminal) {
		this.terminal = terminal;
	}

	@Override
	public Controls getAction() {
		StatusLine line = StatusLine.getInstance();
		Key key = this.terminal.readInput();
		Controls control = null;
		String keyValue;
		while (key == null) {
			key = terminal.readInput();
		}

		keyValue = key.getKind().name();

		switch (keyValue) {
		case "Enter":
			System.out.println(key.getKind().name());
			return Controls.SELECT;
		case "NormalKey": {
			System.out.println(key.getCharacter());
			if (key.getCharacter() == 'q' || key.getCharacter() == 'Q') {
				return Controls.EXIT;
			}
			if (key.getCharacter() == 'u' || key.getCharacter() == 'U') {

			}
			return Controls.UNDO;
		}
		
		case "ArrowUp":
			line.setMoves(line.getMoves() + 1);
			return Controls.UP;
		case "ArrowRight":
			line.setMoves(line.getMoves() + 1);
			return Controls.RIGHT;
		case "ArrowDown":
			line.setMoves(line.getMoves() + 1);
			return Controls.DOWN;
		case "ArrowLeft":
			line.setMoves(line.getMoves() + 1);
			return Controls.LEFT;
		}

		return control;
	}

	
}

