package LeapMotion;

import java.io.IOException;

import com.leapmotion.leap.*;


public class LeapMotionController {
  public static void main(String[] args) {
	 
    KeyBinderListener listener = new KeyBinderListener();
    @SuppressWarnings("unused")
	LeapMotionKeyBinderPanel panel = new LeapMotionKeyBinderPanel(400, 400, listener); 
    @SuppressWarnings("unused")
	Controller controller = new Controller(listener);
    // Keep this process running until Enter is pressed
    System.out.println("Press Enter to quit...");
    try {
		System.in.read();
	} catch (IOException e) {

	}

    //The controller must be disposed of before the listener
    controller = null;
  }
}