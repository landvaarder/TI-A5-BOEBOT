import stamp.core.*;

/**
 * Line follower class
 */

public class LineFollower {

  private int readPin;
  private boolean isLine;

  public LineFollower(int readPin) {
    this.readPin = readPin;
  }

  public boolean getLine() {
    isLine = CPU.readPin(readPin);
    return isLine;
  }
}