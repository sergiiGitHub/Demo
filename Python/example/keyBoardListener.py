#!/usr/bin/env python
 
import thread
import time
 
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
	return

thread.start_new_thread(keypress, ()) 
while True:
	if char is not None:
		print "Key pressed is " + char
		break
	time.sleep(1)

print "Program end"



