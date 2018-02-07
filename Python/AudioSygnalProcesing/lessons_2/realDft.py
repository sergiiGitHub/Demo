import numpy as np
import matplotlib.pyplot as plt

N = 64
k0 = 7
x = np.cos(2 * np.pi * k0 / N * np.arange(N))

#empty array
X = np.array([])

#time indexes
nv = np.arange(-N/2, N/2)
#frequency indexes
kv = np.arange(-N/2, N/2)

#iter over frequency sample
for k in kv:
    #complex exponential
    s = np.exp(1j*2 * np.pi * k / N * nv)
    # compute output spectrum
    X = np.append(X, sum(x*np.conjugate(s)))
    
plt.plot(kv, abs(X))
plt.axis([-N/2, N/2-1, 0, N])

plt.show()
