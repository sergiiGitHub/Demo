import keyboard
import core

hotkey = core.getHotKeyCombination() + str('left')

def process():
    print("process: onArrowLeft! api: ", core.getAndroidApi() )
    if core.getAndroidApi() is 23:
        core.adbKeyEvent('282')
    elif core.getAndroidApi() is 25:
        core.adbKeyEvent('KEYCODE_SYSTEM_NAVIGATION_LEFT')
    else:
        print("not defined for such api")


print 'Registrate hotkey: ', hotkey
keyboard.add_hotkey(hotkey, process)
