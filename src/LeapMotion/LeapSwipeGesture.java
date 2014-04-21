package LeapMotion;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.SwipeGesture;


public class LeapSwipeGesture extends SwipeGesture {
	Controls direction;
	/**
	 * Class that analyzes a swipe and gives it a direction
	 * @param gesture
	 */
	LeapSwipeGesture(Gesture gesture) {
		super(gesture);
		direction = calculateDirection();
	}
	
	
	protected Controls calculateDirection() {
		float x = Math.abs(direction().get(0));
		float y = Math.abs(direction().get(1));
		if (x > y) {
			//means it is translational
			if (x > 0) return Controls.RIGHT;
			return Controls.LEFT;
		} else {
			//means its up and down
			if (y > 0) return Controls.UP;
			return Controls.DOWN;
		}
	}
	
	/**
	 * 
	 * Returns the direction of this swipe
	 */
	public Controls getDirection() {
		return direction;
	}
	
	/**
	 * Compares it to another swipe and returns whether it is in the opposite direction
	 * @param gesture
	 * @return {boolean}
	 */
	
	public boolean isOppositeDirection(LeapSwipeGesture gesture) {
		if (direction == Controls.UP && gesture.direction == Controls.DOWN) return true;
		if (direction == Controls.DOWN && gesture.direction == Controls.UP) return true;
		if (direction == Controls.LEFT && gesture.direction == Controls.RIGHT) return true;
		if (gesture.direction == Controls.RIGHT && gesture.direction == Controls.LEFT) return true;
		return false;
	}
	
	
	
	
	
}
