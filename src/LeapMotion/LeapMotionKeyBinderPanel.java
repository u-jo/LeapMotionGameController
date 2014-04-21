package LeapMotion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class LeapMotionKeyBinderPanel extends JFrame {
	int myWidth;
	int myHeight;
	InfoPanel infoPanel;
	private static final String[] KEYS_TO_BIND_TO = {"Up", "Down", "Left", "Right", "A", "B"};
	private static final String[] OPTIONS = 
		{"Keyboard Up",
		"Keyboard Down",
		"Keyboard Left",
		"Keyboard Right",
		"A",
		"B",
		"C"};
	private static final int[] KEY_BINDINGS = 
		{
		KeyEvent.VK_UP,
		KeyEvent.VK_DOWN,
		KeyEvent.VK_LEFT,
		KeyEvent.VK_RIGHT,
		KeyEvent.VK_A,
		KeyEvent.VK_B,
		KeyEvent.VK_C,
		};
	private Map<String, Integer> mapOfKeyBindings = new HashMap<String, Integer>();
	private KeyBinderListener myListener;
	public LeapMotionKeyBinderPanel(int width, int height, KeyBinderListener listener) {
		super();
		setSize(width, height);
		infoPanel = new InfoPanel(width, height);
		myListener = listener;
		for (int i = 0; i < OPTIONS.length; i++) {
			mapOfKeyBindings.put(OPTIONS[i], KEY_BINDINGS[i]);
		}
		add(infoPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	private class InfoPanel extends JPanel {
		@SuppressWarnings("rawtypes")
		private Map<String, JComboBox> keyToBox = new HashMap<String, JComboBox>();
		public InfoPanel(int width, int height){
			super();
			setSize(width, height);
			for (String keys : KEYS_TO_BIND_TO) {
				createPanels(keys);
			}
			JButton save = new JButton("Save");
			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					myListener.resetMap(getKeyBindings());
				}
				
			});
			add(save);
		}
		
		private void createPanels(String key) {
			JLabel label = new JLabel(key + ": ");
			@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox comboBox = new JComboBox(OPTIONS);
			comboBox.setSelectedIndex(-1);
			add(label);
			add(comboBox);
			keyToBox.put(key, comboBox);
		}
		
		private Map<String, Integer> getKeyBindings() {
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (String key : KEYS_TO_BIND_TO) {
				Integer value = mapOfKeyBindings.get((String) keyToBox.get(key).getSelectedItem());
				map.put(key, value);
			}
			return map;
		}
		
		

 	}
}
