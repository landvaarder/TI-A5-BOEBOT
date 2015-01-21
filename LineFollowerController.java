/*
 *@author Wesley de Hek
 *@version 1.0
 */
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

  public LineFollowerController(int pinLeft, int pinRight, int pinUpperRight, int pinUpperLeft) {
    lineFollowerLeft = new LineFollower(pinLeft);
    lineFollowerRight = new LineFollower(pinRight);
    lineFollowerUpperRight = new LineFollower(pinUpperRight);
    lineFollowerUpperLeft = new LineFollower(pinUpperLeft);
  }

  public int getLineFollowersCode() {
    int code = 0;
    upperLeft = lineFollowerUpperLeft.getLine();
    left = lineFollowerLeft.getLine();
    right = lineFollowerRight.getLine();
    upperRight = lineFollowerUpperRight.getLine();
    if(upperRight)  //crossway
     code = 5;
    else if(upperLeft)  //crossway
     code = 5;
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