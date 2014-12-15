import stamp.core.*;

/**
 * codes:
 * 1 - adjust to right
 * 2 - adjust to left
 * 3 - turn right
 * 4 - turn left
 * 5 - crossway
 * 99 - forward
 */

public class LineFollowerController {

  private LineFollower lineFollowerLeft;
  private LineFollower lineFollowerRight;
  private LineFollower lineFollowerUpperRight;
  private LineFollower lineFollowerUpperLeft;
  private boolean left;
  private boolean upperLeft;
  private boolean right;
  private boolean upperRight;

  public LineFollowerController() {
    lineFollowerLeft = new LineFollower(CPU.pin6);
    lineFollowerRight = new LineFollower(CPU.pin5);
    lineFollowerUpperRight = new LineFollower(CPU.pin4);
    lineFollowerUpperLeft = new LineFollower(CPU.pin7);
  }

  public int getLineFollowersCode() {
    int code = 0;
    upperLeft = lineFollowerUpperLeft.getLine();
    left = lineFollowerLeft.getLine();
    right = lineFollowerRight.getLine();
    upperRight = lineFollowerUpperRight.getLine();
    if(upperRight) { //crossway or turn right
     if(lineFollowerUpperLeft.getLine())
      return 5;
     else
      return 3;
    }
    else if(upperLeft) { //crossway or turn left
     if(lineFollowerUpperRight.getLine())
      return 5;
     else
      return 4;
    }
    else if(left && !right) //adjust to right
     code = 1;
    else if(!left && right) //adjust to left
     code = 2;
    else //forward
     code = 99;
    return code;
  }

  public boolean getRight() {
   return lineFollowerRight.getLine();
  }

  public boolean getLeft() {
   return lineFollowerLeft.getLine();
  }
}