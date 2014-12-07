import stamp.core.*;



public class RemoteController {

  private Remote remote;
  private IRMessage msg = new IRMessage();

  public RemoteController(int remoteRecieverPin)
  {
    irRX = new RemoteControl(remoteRecieverPin);
    transmission = new Transmission();
    moves();
  }

   public void demo()
  {
      while(true)
      {
        if(irRX.decodeMsg(msg) == true )
        {
          System.out.println(" --> " + msg.getButtonValue() );
        }
      }
  }

  public void moves(){
  while(true)
      {
      if(irRX.decodeMsg(msg) == true )
        {
   int button = msg.getButtonValue();
    switch(button) {
     case 0: transmission.turnLeft(); transmission.stop();
     break;
     case 1: transmission.forward() ; transmission.stop();
     break;
     case 2: transmission.turnRight() ;  transmission.stop();
     break;
     case 3:transmission.turnQuarterLeft() ;  transmission.stop();
     break;
     case 4:  transmission.turnHalfCW();  transmission.stop();
     break;
     case 5: transmission.turnQuarterRight() ;  transmission.stop();
     break;
     case 6: ;
     break;
     case 7: transmission.backwards() ;  transmission.stop();
     break;
     case 8: ;
     break;
     case 9:  ;
     break;
     }}
  }
}