import numpy as np
import matplotlib.pyplot as plt

N = 64
k0 = 7
x = np.exp(1j * 2 * np.pi * k0 / N * np.arange(N))

#empty array
X = np.array([])

#iter over frequency sample
for k in range(N):
    #complex exponential
    s = np.exp(1j*2 * np.pi * k / N * np.arange(N))
    # compute output spectrum
    X = np.append(X, sum(x*np.conjugate(s)))
    
plt.plot(np.arange(N), abs(X))
plt.axis([0, N-1, 0, N])

plt.show()
