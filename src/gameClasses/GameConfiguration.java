package gameClasses;

import java.awt.Color;

public class GameConfiguration {

	private static GameConfiguration instance = null;

	protected GameConfiguration() {
		setWallsColor(new Color(255, 0, 0));
		setWallsPattern(' ');
		setBoxColor(new Color(255, 0, 255));
		setBoxPattern('\u2588');
		setGoalsColor(new Color(0, 0, 255));
		setGoalsPattern('X');
		setBoxOnGoalColor(new Color(0,153, 0));
		setBoxOnGoalPattern('X');
		setPlayerColor(new Color(251, 255, 110));
		setPlayerPattern('\u263a');
		setMenuTextForegroundColor(new Color(255, 0, 255));
		setMenuTextBackgroundColor(new Color(0, 0, 0));
		setSelectedMenuTextForegroundColor(new Color(85, 0, 255));
		setSelectedMenuTextBackgroundColor(new Color(0, 102, 0));
	}
	
	public Color getMenuTextForegroundColor() {
		return menuTextForegroundColor;
	}

	public void setMenuTextForegroundColor(Color menuTextForegroundColor) {
		this.menuTextForegroundColor = menuTextForegroundColor;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public Color getSelectedMenuTextForegroundColor() {
		return selectedMenuTextForegroundColor;
	}

	public void setSelectedMenuTextForegroundColor(
			Color selectedMenuTextForegroundColor) {
		this.selectedMenuTextForegroundColor = selectedMenuTextForegroundColor;
	}

	public Color getMenuTextBackgroundColor() {
		return menuTextBackgroundColor;
	}

	public void setMenuTextBackgroundColor(Color menuTextBackgroundColor) {
		this.menuTextBackgroundColor = menuTextBackgroundColor;
	}

	public Color getSelectedMenuTextBackgroundColor() {
		return selectedMenuTextBackgroundColor;
	}

	public void setSelectedMenuTextBackgroundColor(
			Color selectedMenuTextBackgroundColor) {
		this.selectedMenuTextBackgroundColor = selectedMenuTextBackgroundColor;
	}

	public char getPlayerPattern() {
		return playerPattern;
	}

	public void setPlayerPattern(char playerPattern) {
		this.playerPattern = playerPattern;
	}

	public char getBoxOnGoalPattern() {
		return boxOnGoalPattern;
	}

	public void setBoxOnGoalPattern(char boxOnGoalPattern) {
		this.boxOnGoalPattern = boxOnGoalPattern;
	}

	public Color getBoxOnGoalColor() {
		return boxOnGoalColor;
	}

	public void setBoxOnGoalColor(Color boxOnGoalColor) {
		this.boxOnGoalColor = boxOnGoalColor;
	}

	public char getGoalsPattern() {
		return goalsPattern;
	}

	public void setGoalsPattern(char goalsPattern) {
		this.goalsPattern = goalsPattern;
	}

	public Color getGoalsColor() {
		return goalsColor;
	}

	public void setGoalsColor(Color goalsColor) {
		this.goalsColor = goalsColor;
	}

	public char getBoxPattern() {
		return boxPattern;
	}

	public void setBoxPattern(char boxPattern) {
		this.boxPattern = boxPattern;
	}

	public Color getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(Color boxColor) {
		this.boxColor = boxColor;
	}

	public char getWallsPattern() {
		return wallsPattern;
	}

	public void setWallsPattern(char wallsPattern) {
		this.wallsPattern = wallsPattern;
	}

	public Color getWallsColor() {
		return wallsColor;
	}

	public void setWallsColor(Color wallsColor) {
		this.wallsColor = wallsColor;
	}

	public static GameConfiguration getInstance() {
		if (instance == null) {
			instance = new GameConfiguration();
		}
		return instance;
	}
	
	private Color menuTextForegroundColor;
	private Color playerColor;
	private Color selectedMenuTextForegroundColor;
	private Color menuTextBackgroundColor;
	private Color selectedMenuTextBackgroundColor;
	private char playerPattern;
	private char boxOnGoalPattern;
	private Color boxOnGoalColor;
	private char goalsPattern;
	private Color goalsColor;
	private char boxPattern;
	private Color boxColor;
	private char wallsPattern;
	private Color wallsColor;
}
