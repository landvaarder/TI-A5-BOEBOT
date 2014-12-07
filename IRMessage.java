public class IRMessage
{
  private int deviceID;
  private int buttonValue;

  public int getDeviceID()
  {
    return deviceID;
  }

  public int getButtonValue()
  {
    return buttonValue;
  }

  public void decodeRawMsg(int rawMsg)
  {
    switch( rawMsg&0x1F )
    {
      case 0x10: //1 0000:
        deviceID = 1;
        break;
      case 0x80: //0x0 1000:
        deviceID = 2;
        break;
      default:
        deviceID = -1;
        break;
    }

    switch( (rawMsg>>5)&0x7F )
    {
      // 000 0000
      case 0x00: buttonValue = 0; break;
      // 100 0000
      case 0x40: buttonValue = 1; break;
      // 010 0000
      case 0x20: buttonValue = 2; break;
      // 110 0000
      case 0x60: buttonValue = 3; break;
      // 001 0000
      case 0x10: buttonValue = 4; break;
      // 101 0000
      case 0x50: buttonValue = 5; break;
      // 011 0000
      case 0x30: buttonValue = 6; break;
      // 111 0000
      case 0x70: buttonValue = 7; break;
      // 000 1000
      case 0x08: buttonValue = 8; break;
      // 100 1000
      case 0x48: buttonValue = 9; break;
      default: buttonValue = -1; break;
    }
  }


}