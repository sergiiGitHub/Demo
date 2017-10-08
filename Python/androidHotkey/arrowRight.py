import keyboard
import core

hotkey = core.getHotKeyCombination() + str('right')

def process():
    print("process: onArrowRight! api: ", core.getAndroidApi() )
    if core.getAndroidApi() is 23:
        core.adbKeyEvent('283')
    elif core.getAndroidApi() is 25:
        core.adbKeyEvent('KEYCODE_SYSTEM_NAVIGATION_RIGHT')
    else:
        print("not defined for such api")


print 'Registrate hotkey: ', hotkey
keyboard.add_hotkey(hotkey, process)
