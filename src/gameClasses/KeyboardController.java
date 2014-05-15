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
	Controls getAction() {

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
			System.out.println(key.getKind().name());
			return Controls.UP;
		case "ArrowRight":
			System.out.println(key.getKind().name());
			return Controls.RIGHT;
		case "ArrowDown":
			System.out.println(key.getKind().name());
			return Controls.DOWN;
		case "ArrowLeft":
			System.out.println(key.getKind().name());
			return Controls.LEFT;
		}

		return control;
	}

	public static void main(String[] arg) {
		Terminal terminal = TerminalFacade.createTerminal(System.in,
				System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		KeyboardController kontrola = new KeyboardController(terminal);

		System.out.println(kontrola.getAction().name());

	}
}

