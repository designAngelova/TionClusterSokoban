package gameClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.terminal.Terminal;

public class Game {
	private Terminal terminal;
	private StatusLine statusLine;
	private Drawer drawer;
	private GameField field;
	private Menu gameMenu;
	private Menu nextMenu;
	private Menu levelMenu;
	private Scores scores;
	private GameController controller;

	public Game() {
		this.terminal = GameTerminal.getInstance().getTerminal();
		this.drawer = new LanternaDrawer();
		this.field = new GameField();
		this.controller = new KeyboardController();
		this.statusLine = StatusLine.getInstance();
		this.scores = new Scores();
		this.gameMenu = new Menu();
		this.gameMenu.addItem(new MenuItem(1, "PLAY GAME", true));
		this.gameMenu.addItem(new MenuItem(2, "SCORES", false));
		this.gameMenu.addItem(new MenuItem(3, "CREDITS", false));
		this.gameMenu.addItem(new MenuItem(4, "EXIT", false));
		this.nextMenu = new Menu();
		this.nextMenu.addItem(new MenuItem(1, "EXIT TO MENU", false));
		this.nextMenu.addItem(new MenuItem(2, "PLAY NEXT LEVEL", true));
		this.levelMenu = new Menu();
		loadLevelMenu();
	}

	public void startGame() {
		this.terminal.enterPrivateMode();
		this.terminal.setCursorVisible(false);
		while (this.statusLine.getGameScreen() != "exit") {
			switch (this.statusLine.getGameScreen()) {
			case "intro": {
				playIntro();
				this.statusLine.setGameScreen("player name");
			}
				break;
			case "player name": {
				this.statusLine.setPlayerName(getPlayerName());
				this.statusLine.setGameScreen("menu");
			}
				break;
			case "menu": {
				this.drawer.drawMenu(this.gameMenu, "TION CLUSTER SOKOBAN");
				Controls key = controller.getAction(false);
				while (key == null) {
					key = controller.getAction(false);
				}

				switch (key) {
				case UP: {
					this.gameMenu.selectPrev();
				}
					break;
				case DOWN: {
					this.gameMenu.selectNext();
				}
					break;
				case SELECT: {
					switch (this.gameMenu.getSelectedId()) {
					case 1: {
						this.statusLine.setGameScreen("select play level");
					}
						break;
					case 2: {
						this.statusLine.setGameScreen("select scores level");
					}
						break;
					case 3: {
						this.statusLine.setGameScreen("credits");
					}
						break;
					case 4: {
						this.statusLine.setGameScreen("exit");
					}
						break;
					}
				}
					break;
				default:
					break;
				}
			}
				break;
			case "play level": {
				try {
					playLevel(this.statusLine.getLevel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				break;
			case "scores": {
				try {
					this.scores.loadScores(this.statusLine.getLevel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.drawer.drawScores(this.scores);
				this.statusLine.setGameScreen("menu");
			}
				break;
			case "select next level": {
				showNextMenu();
			}
				break;
			case "select play level": {
				this.drawer.drawMenu(this.levelMenu, "SELECT LEVEL");
				Controls key = controller.getAction(false);
				while (key == null) {
					key = controller.getAction(false);
				}

				switch (key) {
				case UP: {
					this.levelMenu.selectPrev();
				}
					break;
				case DOWN: {
					this.levelMenu.selectNext();
				}
					break;
				case SELECT: {
					this.statusLine.setLevel(this.levelMenu.getSelectedId());
					this.statusLine.setGameScreen("play level");
				}
					break;
				default:
					break;
				}
			}
				break;
			case "select scores level": {
				Controls key = null;
				while (key == null) {
					this.drawer.drawMenu(this.levelMenu, "SELECT LEVEL");
					key = controller.getAction(false);

					switch (key) {
					case UP: {
						this.levelMenu.selectPrev();
						key = null;
					}
						break;
					case DOWN: {
						this.levelMenu.selectNext();
						key = null;
					}
						break;
					case SELECT: {
						this.statusLine
								.setLevel(this.levelMenu.getSelectedId());
					}
						break;
					default:
						key = null;
						break;
					}
				}

				showScores();
			}
				break;
			case "credits": {
				this.terminal.clearScreen();
				int rowMod = this.terminal.getTerminalSize().getRows() / 3;
				String text = "Georgi Stoyanov";
		        this.terminal.moveCursor((this.terminal.getTerminalSize().getColumns()/2) - 10, rowMod);
		        this.drawer.drawInfoPause(text, 100);
		        text = "Ventsislav Yordanov";
		        this.terminal.moveCursor((this.terminal.getTerminalSize().getColumns()/2) - 10, rowMod + 1);
		        this.drawer.drawInfoPause(text, 100);
		        text = "Aneliya Angelova";
		        this.terminal.moveCursor((this.terminal.getTerminalSize().getColumns()/2) - 10, rowMod + 2);
		        this.drawer.drawInfoPause(text, 100);
		        text = "Alexandra Chariyska";
		        this.terminal.moveCursor((this.terminal.getTerminalSize().getColumns()/2) - 10, rowMod + 3);
		        this.drawer.drawInfoPause(text, 100);
		        text = "Blagovesta Stanoeva";
		        this.terminal.moveCursor((this.terminal.getTerminalSize().getColumns()/2) - 10, rowMod + 4);
		        this.drawer.drawInfoPause(text, 100);
		        text = "Emilia Kolarova";
		        this.terminal.moveCursor((this.terminal.getTerminalSize().getColumns()/2) - 10, rowMod + 5);
		        this.drawer.drawInfoPause(text, 100);
		        try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        this.statusLine.setGameScreen("menu");

			}
				break;
			}
		}
		playEnd();
		this.terminal.exitPrivateMode();
	}

	private void playIntro() {
		this.drawer.drawIntro();
	}

	private String getPlayerName() {
		this.terminal.clearScreen();
		String playerName = "";
		List<Character> name = new ArrayList<Character>();
		String text = "ENTER PLAYER NAME: ";
		int rowMod = this.terminal.getTerminalSize().getRows() / 3;
        this.terminal.moveCursor(((this.terminal.getTerminalSize().getColumns() - text.length()) / 2) - 5, rowMod + 2);
        this.drawer.drawInfoPause(text, 0);
		String key = this.controller.getKey();
		while (!key.equals("Enter")) {
			switch (key) {
			case "Backspace":
				name.remove(name.size() - 1);
				break;

			default:
				name.add(key.charAt(0));
				break;
			}
			
			this.terminal.clearScreen();
			playerName = getStringRepresentation(name);
			text = "ENTER PLAYER NAME: ";
	        this.terminal.moveCursor(((this.terminal.getTerminalSize().getColumns() - text.length()) / 2) - 5, rowMod + 2);
	        this.drawer.drawInfoPause(text, 0);
	        this.drawer.drawInfoPause(playerName, 0);
	        key = this.controller.getKey();
			
		}
		

		return playerName;
	}

	private void playLevel(int level) throws Exception {
		this.field.clearHistory();
		this.statusLine.setMoves(0);
		this.field.loadLevel(level);
		this.drawer.drawGameField(this.field);
		Controls key = controller.getAction(true);
		while (key != Controls.EXIT) {
			if (key == Controls.UNDO) {
				this.field.undo();
			} else {
				this.field.move(key);
			}

			this.drawer.drawGameField(this.field);
			if (this.field.checkIsSolved()) {
				this.scores.addScore();
				this.statusLine.setGameScreen("select next level");
				return;
			}

			key = this.controller.getAction(true);
		}

		this.statusLine.setGameScreen("menu");
	}

	private void showNextMenu() {
		this.drawer.drawScores(this.scores);
		Controls key = this.controller.getAction(false);
		while (key != Controls.SELECT) {
			key = this.controller.getAction(false);
		}

		key = null;
		while (key == null) {
			this.drawer.drawMenu(this.nextMenu, "SELECT ACTION");
			key = this.controller.getAction(false);
			switch (key) {
			case UP: {
				this.nextMenu.selectPrev();
				key = null;
			}
				break;
			case DOWN: {
				this.nextMenu.selectNext();
				key = null;
			}
				break;
			case SELECT: {

			}
				break;

			default: {
				key = null;
			}
				break;
			}
		}

		switch (this.nextMenu.getSelectedId()) {
		case 1: {
			this.statusLine.setGameScreen("menu");
		}
			break;
		case 2: {
			if (this.statusLine.getLevel() + 1 <= this.levelMenu.getItems()
					.size()) {
				this.statusLine.setLevel(this.statusLine.getLevel() + 1);
				this.statusLine.setGameScreen("play level");
			} else {
				this.statusLine.setGameScreen("menu");
			}

		}
			break;

		default:
			break;
		}
	}

	private void showScores() {
		try {
			this.scores.loadScores(this.statusLine.getLevel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.drawer.drawScores(this.scores);
		Controls key = this.controller.getAction(false);
		while (key != Controls.SELECT) {
			key = this.controller.getAction(false);
		}

		this.statusLine.setGameScreen("menu");
	}

	private void playEnd() {
		this.terminal.clearScreen();
		int rowMod = this.terminal.getTerminalSize().getRows() / 3;
		String text = "GOOD BYE!!!................";
        this.terminal.moveCursor(((this.terminal.getTerminalSize().getColumns() - text.length()) / 2), rowMod + 2);
        this.drawer.drawInfoPause(text, 90);
	}

	private void loadLevelMenu() {
		try (BufferedReader br = new BufferedReader(new FileReader("levels"))) {
			String line = br.readLine();
			int level = 0;
			boolean selected = true;
			while (line != null) {
				if (line.contains("lvl")) {
					level++;
					this.levelMenu.addItem(new MenuItem(level, level + "",
							selected));
					selected = false;
				}

				line = br.readLine();
			}
		} catch (Exception e) {
		}

	}
	
	private String getStringRepresentation(List<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
}
