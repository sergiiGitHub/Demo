import numpy as np
#dft implementation
from scipy.fftpack import fft
import sys, os, math

sys.path.append(os.path.join(os.path.dirname(os.path.realpath(__file__)), '../../../../audioSignalProcessing/sms-tools/software/models/'))
import utilFunctions as UF

#find duration 
M = 501
#where is the cener of middle
hM1 = int(math.floor((M+1)/2))
hM2 = int(math.floor((M)/2))

(fs, x) = UF.wavread('../../../../audioSignalProcessing/sms-tools/sounds/soprano-E4.wav')

x1 = x[5000:5000+M] * np.hamming(M)

#number of samples for fft
N = 1024
#centered oround 0
fftbuffer = np.zeros(N)
#from begining to 8 plase 
fftbuffer[:hM1] = x1[hM2:]
fftbuffer[N-hM2:] = x1[:hM2]

#dft by fft  
X = fft(fftbuffer)

#magniture
#mX = abs(X)
#magniture in db
mX = 20*np.log10(abs(X))
#take half mX[0:512]
#phase calc
pX = np.angle(X)
#phase calc unwrap
#pX = np.unwrap(np.angle(X))

