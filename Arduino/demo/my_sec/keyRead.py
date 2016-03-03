#!/usr/bin/env python
 
# this solution will work only in Windows, as msvcrt is a Windows only package
 
import thread
import time
import serial; 
 
#key interupt
try:
    from msvcrt import getch  # try to import Windows version
except ImportError:
    def getch():   # define non-Windows version
        import sys, tty, termios
        fd = sys.stdin.fileno()
        old_settings = termios.tcgetattr(fd)
        try:
            tty.setraw(sys.stdin.fileno())
            ch = sys.stdin.read(1)
        finally:
            termios.tcsetattr(fd, termios.TCSADRAIN, old_settings)
        return ch
 
char = None
 
def keypress():
    global char
    char = getch()


#serial port 
ser = serial.Serial('/dev/ttyUSB0',9600,timeout=1);
def serRead():
	print ser.readline(1000);
	
thread.start_new_thread(keypress, ()) 
while True:
	if char is not None:
		print "Key pressed is " + char
		break
	serRead();#print ser.readline(100);
	time.sleep(1)

print ("ser.close" );
ser.close;
print "Program end"



