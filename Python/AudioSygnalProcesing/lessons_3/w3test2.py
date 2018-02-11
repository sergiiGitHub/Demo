#
import numpy as np
#triangular function
from scipy.signal import triang
#dft implementation
from scipy.fftpack import fft

#declere (create) 15 traingular function
x = triang(15)

#centered oround 0
fftbuffer = np.zeros(15)
#from begining to 8 plase 
fftbuffer[:8] = x[7:]
fftbuffer[8:] = x[:7]

#dft by fft  
X = fft(fftbuffer)

#magniture
mX = abs(X)

#phase calc
pX = np.angle(X)

