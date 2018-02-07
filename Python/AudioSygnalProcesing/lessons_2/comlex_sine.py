import matplotlib.pyplot as plt
import numpy as np

#array angth
#size of dft
N = 32
#frequency
k = 5
#time indexes
n = np.arange(-N/2, N/2)
#complex sine
s = np.exp(1j*2*np.pi * k * n / N)

#real
#plt.plot(n, np.real(s))
plt.plot(n, np.imag(s))
plt.axis([-N/2, N/2, -1, 1])
plt.xlabel('n index')
plt.ylabel('amplitude')
plt.show();


