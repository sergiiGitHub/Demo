#
import numpy as np
#triangular function
from scipy.signal import triang
#dft implementation
from scipy.fftpack import fft

#declere (create) 15 traingular function
x = triang(15)
#dft by fft  
X = fft(x)
#magniture
mX = abs(X)
#phase calc
pX = np.angle(X)

