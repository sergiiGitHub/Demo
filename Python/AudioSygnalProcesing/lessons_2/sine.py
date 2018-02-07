import matplotlib.pyplot as plt
import numpy as np

#amplitude
A = .8
#frequency
f0 = 1000 
phi = np.pi / 2
#sampling rate / period
fs = 44100

t = np.arange(-0.002, 0.002, 1.0/fs)

x = A * np.cos (2*np.pi * f0 *t + phi)
plt.plot(t, x)
plt.axis([-0.002, 0.002, -0.8, 0.8])
plt.xlabel('time')
plt.ylabel('amp')
plt.show();

