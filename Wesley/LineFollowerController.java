import stamp.core.*;

/**
 * Put a one line description of your class here.
 * <p>
 * This comment should contain a description of the class. What it
 * is for, what it does and how to use it.
 *
 * You should rename the class and then save it in a file with
 * exactly the same name as the class.
 *
 * @version 1.0 Date
 * @author Your Name Here
 */

public class LineFollowerController {

  private LineFollower lineFollowerLeft;
  private LineFollower lineFollowerRight;
  private LineFollower lineFollowerMiddle;
  private boolean left;
  private boolean middle;
  private boolean right;

  public LineFollowerController() {
    lineFollowerLeft = new LineFollower(CPU.pin9,CPU.pin6);
    lineFollowerRight = new LineFollower(CPU.pin7,CPU.pin4);
    lineFollowerMiddle = new LineFollower(CPU.pin8,CPU.pin5);
  }

  public int getLineFollowersCode() {
    int code = 0;
    left = lineFollowerLeft.getLine();
    middle = lineFollowerMiddle.getLine();
    right = lineFollowerRight.getLine();
    if(left)
     code = 1;
    else if(right)
     code = 2;



    return code;
  }



}