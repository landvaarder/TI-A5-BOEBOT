import stamp.core.*;

public class Remote
{

  private int sensorPin = -1;

  public RemoteControl( int sensorPin )
  {
    this.sensorPin = sensorPin;
  }

  public boolean decodeMsg(IrMsg msg)
  {
    int result = 0;
    boolean err = true;
    int pulseLengte;

    if( CPU.pulseIn (30000, this.sensorPin, false) > 200 )
    {
      err = false;
      for( int idx = 0; (idx < 12) && (!err); idx++ )
      {
        pulseLengte = CPU.pulseIn (10000, this.sensorPin, false );

        if (pulseLengte == -1 )
        {
          err = true;
        }
        else
        {
          if( pulseLengte > 100 )
          {
            result |= 0x01;
          }
          result <<=1;
        }
      }
    }

    msg.decodeRawMsg((result>>1));

    return !err;
  }


  /*
   * Check for startbit
   */
  private boolean chkStartBit()
  {
    boolean retval = false;
    int pulseLengte = CPU.pulseIn ( 30000, this.sensorPin, false );
    if( pulseLengte > 200 )
    {
      retval = true;
    }
    return retval;
  }
};