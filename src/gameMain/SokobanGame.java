package gameMain;

import java.nio.charset.Charset;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class SokobanGame {

	public static void main(String[] args) {
		 System.out.println(" | 1  2  3  4  5  6  7  8  9");
		 System.out.println(" | 1  2  3  4  5  6  7  8  9");
		 System.out.println(" | 1  2  3  4  5  6  7  8  9");
		 Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		 terminal.enterPrivateMode();
		 terminal.moveCursor(10, 5);
		    terminal.putCharacter('H');
		    sleep(200);
		    terminal.putCharacter('e');
		    sleep(200);
		    terminal.putCharacter('l');
		    sleep(200);
		    terminal.putCharacter('l');
		    sleep(200);
		    terminal.putCharacter('o');
		    sleep(200);
		    terminal.putCharacter('!');
		    sleep(200);
		    terminal.moveCursor(0, 0);
		    sleep(2000);
		    
		 terminal.exitPrivateMode();
	}
	
	public static void sleep(int msec){
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		 System.out.println(" Test Comit");
	}

}
