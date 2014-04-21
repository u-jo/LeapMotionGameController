package LeapMotion;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;

class KeyBinderListener extends Listener {
	
	
	private Robot myRobot;
	private boolean swiped = false;
	float timer = 0;
	Map<Controls, Integer> keyBindingMap;
	public void onInit(Controller controller) {
		System.out.println("Initialized");
		keyBindingMap = new HashMap<Controls, Integer>();
		keyBindingMap.put(Controls.UP, KeyEvent.VK_UP);
		keyBindingMap.put(Controls.DOWN, KeyEvent.VK_DOWN);
		keyBindingMap.put(Controls.LEFT, KeyEvent.VK_LEFT);
		keyBindingMap.put(Controls.RIGHT, KeyEvent.VK_RIGHT);
		keyBindingMap.put(Controls.TAP, KeyEvent.VK_A);
		try {
			myRobot = new Robot();
			myRobot.setAutoDelay(50);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	protected void resetMap(Map<String, Integer> map) {
		keyBindingMap.clear();
		keyBindingMap.put(Controls.UP, map.get("Up"));
		keyBindingMap.put(Controls.DOWN, map.get("Down"));
		keyBindingMap.put(Controls.LEFT, map.get("Left"));
		keyBindingMap.put(Controls.RIGHT,map.get("Right"));
		keyBindingMap.put(Controls.TAP,map.get("A"));
	}

	public void onConnect(Controller controller) {
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}

	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}

	public void onFrame(Controller controller) {
		// Get the most recent frame and report some basic information
		Frame frame = controller.frame();
		HandList hands = frame.hands();
		if (!hands.isEmpty()) {
			Hand hand = hands.get(0);
			HandArea area = new HandArea(hand);
			pressKey(area.directionToMove);
		} 
		timer += 0.05;
		checkForSwipe();
		GestureList gestures = frame.gestures();

		for (int i = 0; i < gestures.count(); i++) {
			Gesture gesture = gestures.get(i);
			switch (gesture.type()) {
			case TYPE_SWIPE:
				if (!swiped) {
					LeapSwipeGesture swipe = new LeapSwipeGesture(gesture);
					LeapSwipeGesture previousSwipe = new LeapSwipeGesture(controller.frame(1).gesture(swipe.id()));
					System.out.println(swipe.direction);
					if (!swipe.isOppositeDirection(previousSwipe)) {
						pressKey(swipe.direction);
					}
				}
				
				swiped = true;
				break;
			case TYPE_SCREEN_TAP:
				pressKey(Controls.TAP);
				break;
			default:
				
				break;
			}
		}
	}
	

	
	private void pressKey(Controls direction) {
		if (keyBindingMap.containsKey(direction)) {
			pressKey(keyBindingMap.get(direction));
		}
	}
	private void pressKey(int key) {
		myRobot.keyPress(key);
		myRobot.keyRelease(key);

	}
	
	private void checkForSwipe() {
		if (timer > 1 ) {
			timer = 0;
			swiped = false;
		}
	}

  }