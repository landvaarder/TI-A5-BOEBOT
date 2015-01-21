import stamp.core.*;

/*
*@author = Tim Schijvenaars
*@version = 1.0
*/

public class Profiles{

private int emitterLeft, recieverLeft, emitterRight, recieverRight, whiskerLeft, whiskerRight, servoLeft,
            servoRight, remotePin, pinLeft, pinRight, pinUpperRight, pinUpperLeft, rxPin, txPin;

public Profiles(int profileNumber){
    selectProfileNumber(profileNumber);
}

private void selectProfileNumber(int number){
    switch(number){

    case 1:
    this.emitterLeft = CPU.pin2;
    this.recieverLeft = CPU.pin9; //0
    this.emitterRight = CPU.pin3;
    this.recieverRight = CPU.pin1;
    this.whiskerLeft = CPU.pin10;
    this.whiskerRight = CPU.pin11;
    this.servoLeft = CPU.pin12;
    this.servoRight = CPU.pin13;
    this.remotePin = CPU.pin0;
    this.pinLeft = CPU.pin6;
    this.pinRight = CPU.pin5;
    this.pinUpperRight = CPU.pin4;
    this.pinUpperLeft = CPU.pin7;
    this.rxPin = CPU.pin15;
    this.txPin = CPU.pin14;
    break;

    default:
    this.emitterLeft = CPU.pin0;
    this.recieverLeft = CPU.pin0;
    this.emitterRight = CPU.pin0;
    this.recieverRight = CPU.pin0;
    this.whiskerLeft = CPU.pin0;
    this.whiskerRight = CPU.pin0;
    this.servoLeft = CPU.pin0;
    this.servoRight = CPU.pin0;
    this.remotePin = CPU.pin0;
    this.pinLeft = CPU.pin0;
    this.pinRight = CPU.pin0;
    this.pinUpperRight = CPU.pin0;
    this.pinUpperLeft = CPU.pin0;
    this.rxPin = CPU.pin0;
    this.txPin = CPU.pin0;
    break;
    }
}

public int getPins(int number){
    int[] pins;
    pins = new int[15];
    pins[0]= this.emitterLeft;
    pins[1]= this.recieverLeft;
    pins[2]= this.emitterRight;
    pins[3]= this.recieverRight;
    pins[4]= this.whiskerLeft;
    pins[5]= this.whiskerRight;
    pins[6]= this.servoLeft;
    pins[7]= this.servoRight;
    pins[8]= this.remotePin;
    pins[9]= this.pinLeft;
    pins[10]= this.pinRight;
    pins[11]= this.pinUpperRight;
    pins[12]= this.pinUpperLeft;
    pins[13] = this.rxPin;
    pins[14] = this.txPin;


    return pins[number];
}

}