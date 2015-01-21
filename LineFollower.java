import stamp.core.CPU;

/*
 *@author Wesley de Hek
 *@version 1.0
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