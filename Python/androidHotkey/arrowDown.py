import keyboard
import core

arrowDownHotkey = core.getHotKeyCombination() + str('down')

def process():
    print("process: onArrowDown! api: ", core.getAndroidApi() )
    if core.getAndroidApi() is 23:
        core.adbKeyEvent('281')
    elif core.getAndroidApi() is 25:
        core.adbKeyEvent('KEYCODE_SYSTEM_NAVIGATION_DOWN')
    else:
        print("not defined for such api")


print 'Registrate ARROW_DOWN hotkey: ', arrowDownHotkey
keyboard.add_hotkey(arrowDownHotkey, process)
