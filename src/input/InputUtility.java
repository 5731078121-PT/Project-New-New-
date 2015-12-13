package input;

public class InputUtility {
	private static int mouseX,mouseY;
	private static boolean mouseLeftDown,mouseOnScreen;
	private static boolean mouseLeftDownUp;
	private static boolean mouseLeftDownTrigger;
	private static boolean mouseRightClickUp;
	
	public static boolean isMouseLeftDownTrigger() {
		return mouseLeftDownTrigger;
	}
	public static void setMouseLeftDownTrigger(boolean mouseLeftDownTrigger) {
		InputUtility.mouseLeftDownTrigger = mouseLeftDownTrigger;
	}
	public static int getMouseX() {
		return mouseX;
	}
	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}
	public static int getMouseY() {
		return mouseY;
	}
	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}
	
	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}
	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}
	
	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}
	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen;
	}
	public static boolean isMouseLeftDownUp() {
		return mouseLeftDownUp;
	}
	public static void setMouseLeftDownUp(boolean mouseLeftDownUp) {
		InputUtility.mouseLeftDownUp = mouseLeftDownUp;
	}
	public static boolean isMouseRightClickUp() {
		return mouseRightClickUp;
	}
	public static void setMouseRightClickUp(boolean mouseRightClickUp) {
		InputUtility.mouseRightClickUp = mouseRightClickUp;
	}
	
	
}
