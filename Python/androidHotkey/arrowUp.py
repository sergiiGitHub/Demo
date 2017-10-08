import keyboard
import core

arrowDownHotkey = core.getHotKeyCombination() + str('up')

def process():
    print("process: onArrowUp! api: ", core.getAndroidApi() )
    if core.getAndroidApi() is 23:
        core.adbKeyEvent('280')
    elif core.getAndroidApi() is 25:
        core.adbKeyEvent('KEYCODE_SYSTEM_NAVIGATION_UP')
    else:
        print("not defined for such api")

print 'Registrate ARROW_UP hotkey: ', arrowDownHotkey
keyboard.add_hotkey(arrowDownHotkey, process)
