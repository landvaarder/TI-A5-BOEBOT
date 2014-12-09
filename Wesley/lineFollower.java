import stamp.core.*;

/**
 * Line follower class
 */

public class LineFollower {

  private int readPin;
  private int powerPin;
  private boolean isLine;

  public LineFollower(int readPin, int powerPin) {
    this.readPin = readPin;
    this.powerPin = powerPin;
  }

  public boolean getLine() {
    CPU.writePin(powerPin,true);
    CPU.delay(1);
    isLine = CPU.readPin(readPin);
    CPU.writePin(powerPin,false);
    return isLine;
  }
}