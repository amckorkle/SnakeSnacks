import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class KeyListenerManager implements KeyListener {
	private HashMap<String, Runnable> keyCommands = new HashMap<String, Runnable>();

	public void keyPressed(KeyEvent e) {
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if (keyCommands.containsKey(key)) {
			keyCommands.get(key).run();
		}
	}

	public void addKeyCommand(String key, Runnable func) {
		keyCommands.put(key, func);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}