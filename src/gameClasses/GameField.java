package gameClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameField {
	private char[][] fieldMatrix;
	private Stack<char[][]> history;

	public void undo() {
		this.fieldMatrix = this.history.pop();
	}

	public boolean checkIsSolved() {
		for (int row = 0; row < this.fieldMatrix.length; row++) {
			for (int col = 0; col < this.fieldMatrix[0].length; col++) {
				if (this.fieldMatrix[row][col] == 'B') {
					return false;
				}
			}
		}

		return true;
	}

	public boolean loadLevel(int level) {
		try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
			List<String> fileData = new ArrayList<String>();
			String line = br.readLine();
			while (line != "lvl" + level) {
				line = br.readLine();
			}
			while (line != null || line != "lvl" + (level + 1)) {
				fileData.add(line);
				line = br.readLine();
			}

			if (fileData.isEmpty()) {
				return false;
			}

			this.fieldMatrix = new char[fileData.size()][fileData.get(0)
					.length()];

			for (int row = 1; row < this.fieldMatrix.length; row++) {
				for (int col = 0; col < this.fieldMatrix[0].length; col++) {
					this.fieldMatrix[row - 1][col] = fileData.get(row).charAt(
							col);
				}
			}

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public void move(Controls direction) {
		int playerRow = 0;
		int playerCol = 0;
		int rowMod = 0;
		int colMod = 0;
		boolean playerOnGoal = false;
		switch (direction) {
		case UP:
			rowMod = -1;
			break;
		case RIGHT:
			colMod = 1;
			break;
		case DOWN:
			rowMod = 1;
			break;
		case LEFT:
			colMod = -1;
			break;
		default:
			break;
		}

		try {

			for (int row = 0; row < this.fieldMatrix.length; row++) {
				for (int col = 0; col < this.fieldMatrix[0].length; col++) {
					if (this.fieldMatrix[row][col] == 'P') {
						playerRow = row;
						playerCol = col;
					} else if (this.fieldMatrix[row][col] == 'D') {
						playerRow = row;
						playerCol = col;
						playerOnGoal = true;
					}
				}
			}

			if (this.fieldMatrix[playerRow + rowMod][playerCol + colMod] == '0') {
				moveObject(playerRow, playerCol, playerRow + rowMod, playerCol
						+ colMod, 'P', playerOnGoal);
			} else if (this.fieldMatrix[playerRow + rowMod][playerCol + colMod] == 'G') {
				moveObject(playerRow, playerCol, playerRow + rowMod, playerCol
						+ colMod, 'D', playerOnGoal);
			} else if (this.fieldMatrix[playerRow + rowMod][playerCol + colMod] == 'B') {
				if (this.fieldMatrix[playerRow + (2 * rowMod)][playerCol
						+ (2 * colMod)] == 'G') {
					moveObject(playerRow + rowMod, playerCol + colMod,
							playerRow + 2 * rowMod, playerCol + 2 * colMod,
							'X', false);
					moveObject(playerRow, playerCol, playerRow + rowMod,
							playerCol + colMod, 'P', playerOnGoal);
				} else if (this.fieldMatrix[playerRow + (2 * rowMod)][playerCol
						+ (2 * colMod)] == '0') {
					moveObject(playerRow + rowMod, playerCol + colMod,
							playerRow + 2 * rowMod, playerCol + 2 * colMod,
							'B', false);
					moveObject(playerRow, playerCol, playerRow + rowMod,
							playerCol + colMod, 'P', playerOnGoal);
				}
			} else if (this.fieldMatrix[playerRow + rowMod][playerCol + colMod] == 'X') {
				if (this.fieldMatrix[playerRow + (2 * rowMod)][playerCol
						+ (2 * colMod)] == 'G') {
					moveObject(playerRow + rowMod, playerCol + colMod,
							playerRow + 2 * rowMod, playerCol + 2 * colMod,
							'X', true);
					moveObject(playerRow, playerCol, playerRow + rowMod,
							playerCol + colMod, 'D', playerOnGoal);
				} else if (this.fieldMatrix[playerRow + (2 * rowMod)][playerCol
						+ (2 * colMod)] == '0') {
					moveObject(playerRow + rowMod, playerCol + colMod,
							playerRow + 2 * rowMod, playerCol + 2 * colMod,
							'B', true);
					moveObject(playerRow, playerCol, playerRow + rowMod,
							playerCol + colMod, 'D', playerOnGoal);
				}
			}

		} catch (Exception e) {
		}
	}

	private void moveObject(int oldRow, int oldCol, int newRow, int newCol,
			char newChar, boolean onGoal) {
		if (onGoal) {
			this.fieldMatrix[oldRow][oldCol] = 'G';
		} else {
			this.fieldMatrix[oldRow][oldCol] = '0';
		}

		this.fieldMatrix[newRow][newCol] = newChar;
	}
}
