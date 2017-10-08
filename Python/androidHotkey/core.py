import subprocess

api = 0;
hotkeyCombination = 'ctrl+'

#hotkey
def getHotKeyCombination():
    return hotkeyCombination;

def setHotKeyCombination(arg):
    print'setHotKeyCombination ' + arg
    global hotkeyCombination
    hotkeyCombination = arg;

#api
def getAndroidApi():
    return api;

def setAndroidApi(aApi):
    print'setAndroidApi ', aApi
    global api
    api = aApi;

#execute comand
def adbKeyEvent(arg):
    adbKeyevent = 'adb shell input keyevent '
    cmd = adbKeyevent + arg
    print 'execute comand: ' + cmd
    s = subprocess.check_output(cmd.split())
    print s.split('\r\n')