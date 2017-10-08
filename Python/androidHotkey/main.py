#!/usr/bin/env python
#For use keyboard
#https://pypi.python.org/pypi/keyboard/

#android key input see
#https://developer.android.com/reference/android/view/KeyEvent.html 
import keyboard
import core
import arrowUp
import arrowDown
import arrowRight
import arrowLeft

#set api for correct work
#android 6
api = 23
#android 7
#api = 25
core.setAndroidApi(api)

#todo
#Possibel set hotkey for ex.: 
#core.setHotKeyCombination('ctrl+alt+')
#default is 'ctrl+'

def onKLetter():
    print("onKLetter!")
    core.adbKeyEvent('KEYCODE_K')

# init hotkey
keyboard.add_hotkey(core.getHotKeyCombination() + str('k'), onKLetter, timeout=['0.1'])
#Delay aproximatly 3sec

#exit 
print "For exit press 'esc'"
keyboard.wait('esc')


