import math

x = [ 0.5, 0.4, 0.3, 0.7, 0.1 ]
u = [0.98, 0.23, 0.43, 0.67]
v = [0.34, 0.77]
t = [1, -1]
bias = 1;

def sigmoid(v):
    return 2 / (1 + math.exp(-3*v)) -1
    # if v >= 0.5: return 1
    # else: return 0

def mse(o, t):
    result = 0
    for k in range(0, len(t)):
        result += (t[k] - o[k]) ** 2
    return result / 2

z = []
for uj in u:
    zsum = bias
    for xi in x:
        zsum += xi * uj

    z.append(sigmoid(zsum))
print(z)
o = []
for vk in v:
    osum = bias
    for zp in z:
        osum += zp * vk

    o.append(sigmoid(osum))

print(o)
print(mse(o,t))




