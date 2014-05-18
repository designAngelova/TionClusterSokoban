package gameClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.terminal.Terminal;

public class Game {
	private Terminal terminal;
	private StatusLine statusLine;
	private LanternaDrawer drawer;
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
		this.statusLine = new StatusLine();
		this.gameMenu = new Menu();
		this.gameMenu.addItem(new MenuItem(1, "PLAY GAME", true));
		this.gameMenu.addItem(new MenuItem(2, "SCORES", false));
		this.gameMenu.addItem(new MenuItem(3, "CREDITS", false));
		this.gameMenu.addItem(new MenuItem(4, "EXIT", false));
		this.nextMenu = new Menu();
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
				this.drawer.drawMenu(this.gameMenu);
				Controls key = controller.getAction();
				while (key == null) {
					key = controller.getAction();
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

			}
				break;
			case "select play level": {
				this.drawer.drawMenu(this.levelMenu);
				Controls key = controller.getAction();
				while (key == null) {
					key = controller.getAction();
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
			case "select score level": {

			}
				break;
			case "credits": {

			}
				break;
			}
		}
		playEnd();
		this.terminal.exitPrivateMode();
	}

	private void playIntro() {

	}

	private String getPlayerName() {
		String playerName = "";

		return playerName;
	}

	private void playLevel(int level) throws Exception {
		this.field.loadLevel(level);
		this.drawer.drawGameField(this.field);
		Controls key = controller.getAction();
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

			key = this.controller.getAction();
		}

		this.statusLine.setGameScreen("menu");
	}

	private void playEnd() {

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
}
