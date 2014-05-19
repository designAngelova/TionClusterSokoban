package gameClasses;

import java.text.MessageFormat;
import java.util.Date;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

public class KeyboardController extends GameController {
	
	private Terminal terminal;

	public KeyboardController() {
		this.terminal = GameTerminal.getInstance().getTerminal();
	}

	@Override
	public Controls getAction(boolean inProgress) {
		StatusLine line = StatusLine.getInstance();
		int timeCol = line.getTimeCol();
		int timeRow = line.getTimeRow();
		long startTime = line.getTime();
		Key key = this.terminal.readInput();
		Controls control = null;
		String keyValue;
		String time;
		int count = 0;
		while (key == null) {			
			key = terminal.readInput();
			if (inProgress) {
				if (count == 500000) {
					time = MessageFormat.format("{0, time, mm:ss.SSS}", new Date().getTime() - startTime);
					this.terminal.moveCursor(timeCol, timeRow);
					for (int i = 0; i < time.length(); i++) {
						this.terminal.putCharacter(time.charAt(i));
					}
					count = 0;
				}
				
				count++;
			}
		}

		keyValue = key.getKind().name();

		switch (keyValue) {
		case "Enter":
			return Controls.SELECT;
		case "NormalKey": {
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

