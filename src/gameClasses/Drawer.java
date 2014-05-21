package gameClasses;



public abstract class Drawer {

	 abstract void drawMenu(Menu menu, String nameOfMenu) ;
	 abstract void drawScores(Scores scores);
	 abstract void drawGameField(GameField field);
	 abstract void drawIntro();
	 abstract void drawEnd();
	 abstract void drawInfoPause(String text, int i);
	}