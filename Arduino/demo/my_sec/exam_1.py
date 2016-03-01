print ("Hello World!");

import serial;

ser = serial.Serial('/dev/ttyUSB0',9600);
ser.write('110,')
print(ser.read());
