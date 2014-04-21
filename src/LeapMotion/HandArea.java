package LeapMotion;
import com.leapmotion.leap.Hand;


public class HandArea {
	
	public static final float LEFT_MIN = -90;
	public static final float LEFT_MAX = -30;
	public static final float CENTER_MIN = LEFT_MAX;
	public static final float CENTER_MAX = 25;
	public static final float RIGHT_MIN = CENTER_MAX;
	public static final float RIGHT_MAX = 90;
	public static final float UP_MIN = 150;
	public static final float DOWN_MAX = 60;
	Controls directionToMove;
	public HandArea(Hand hand) {
		directionToMove = determineDirection(hand);
	}
	
	private Controls determineDirection(Hand hand) {
		if (isInLeft(hand)) return Controls.LEFT;
		else if (isInRight(hand)) return Controls.RIGHT;
		else if (isInUp(hand) && isInCenter(hand)) return Controls.UP;
		else if (isInDown(hand) && isInCenter(hand)) return Controls.DOWN;
		else return Controls.CENTER;
	}
	private boolean isInLeft(Hand hand) {
		return (hand.palmPosition().getX() < LEFT_MAX) && (hand.palmPosition().getX() > LEFT_MIN);
	}
	private boolean isInRight(Hand hand) {
		return (hand.palmPosition().getX() < RIGHT_MAX) && (hand.palmPosition().getX() > RIGHT_MIN);
	}
	private boolean isInCenter(Hand hand) {
		return (hand.palmPosition().getX() < CENTER_MAX) && (hand.palmPosition().getX() > CENTER_MIN);
	}
	private boolean isInUp(Hand hand) {
		return (hand.palmPosition().getY()) > UP_MIN;
	}
	
	private boolean isInDown(Hand hand) {
		return (hand.palmPosition().getY()) < DOWN_MAX;
	}
}
