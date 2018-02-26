import os
import sys
import numpy as np
sys.path.append(os.path.join(os.path.dirname(os.path.realpath(__file__)),
 '../../software/models/'))
import utilFunctions as UF
import A3Part4 as a
(fs, x) = UF.wavread('test_50_100.wav')

x1 = x[:1000]

#y, yf, mx, mxf, t = a.suppressFreqDFTmodel(x1, fs, 2048)
y, yf = a.suppressFreqDFTmodel(x1, fs, 1024)





