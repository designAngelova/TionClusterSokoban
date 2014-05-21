package gameClasses;


public abstract class GameController {
	abstract public Controls getAction(boolean inProgress) ;

	abstract public String getKey();
}
