import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animate_frame
import sys

plt.style.use('dark_background')
fig = plt.figure()
#fig.set_dpi(100)
# f = 2*pow(10,3)
# c = 3 * 10 ** 8
# lmda = c / f
# b = 2 * pi / lmda
b = 10000
z = np.arange(0, 10, 0.01)
#Initial time
t0 = 0
#Time increment
dt = 0.01
if len(sys.argv) == 1:
        reflection_coeff = float(input("Enter value of reflection coefficient :"))
        amplitude = float(input("Enter Amplitude of the Wave :"))
        type_3or1 = int(input("Press 1 to plot 3 graphs in different plots, 0 to plot all 3 in 1 : "))
else:
        reflection_coeff = float(sys.argv[1])
        amplitude = float(sys.argv[2])
        type_3or1 = int(sys.argv[3])

def wave_standing(z, t, b, amplitude, reflection_coeff):
        return amplitude*((np.exp(-1j * z * b) + (reflection_coeff * np.exp(1j * z * b))) * np.exp(t * b * 1j))

def wave_incident(z, t, b, amplitude):
        return amplitude*(np.exp(-1j * z * b)) * np.exp(t * b * 1j)

def wave_reflected(z, t, b, amplitude, reflection_coeff):
        return amplitude * reflection_coeff * np.exp(1j * z * b) * np.exp(t * b * 1j)

arr_wave_standing = []
t0 = 0
for i in range(1000):
    value = wave_standing(z, t0, b, amplitude, reflection_coeff)
    t0 = t0 + dt
    arr_wave_standing.append(value)

arr_wave_incident = []
t0 = 0
for i in range(1000):
    value = wave_incident(z, t0, b, amplitude)
    t0 = t0 + dt
    arr_wave_incident.append(value)

arr_wave_reflected = []
t0 = 0
for i in range(1000):
    value = wave_reflected(z, t0, b, amplitude, reflection_coeff)
    t0 = t0 + dt
    arr_wave_reflected.append(value)
k = 0
def animate_wave_iterable_AllInOne(iterable):
        global k
        x_incident = arr_wave_incident[k]
        x_reflected = arr_wave_reflected[k]
        x_standing = arr_wave_standing[k]
        k += 1
        fig.clear()
        plt.ylim([-(2*amplitude), 2*amplitude])
        plt.xlim([0, 2])
        plt.plot(z, x_incident, color='green', label='Incident Wave')
        plt.plot(z, x_reflected, color='red', label='Reflected Wave')
        plt.plot(z, x_standing, color='cyan', label='Standing Wave')
        plt.grid(True)
k=0
def animate_wave_iterable_3plots(iterable):
        global k
        x_incident = arr_wave_incident[k]
        x_reflected = arr_wave_reflected[k]
        x_standing = arr_wave_standing[k]
        k += 1
        fig.clear()
        plt.subplot(3,1,1)
        plt.ylim([-(2*amplitude), 2*amplitude])
        plt.xlim([0, 2])
        plt.plot(z, x_incident, color='yellow',label='Incident Wave')
        plt.subplot(3, 1, 2)
        plt.ylim([-(2*amplitude), 2*amplitude])
        plt.xlim([0, 2])
        plt.plot(z, x_reflected, color='red', label='Reflected Wave')
        plt.subplot(3, 1, 3)
        plt.ylim([-(2*amplitude), 2*amplitude])
        plt.xlim([0, 2])
        plt.plot(z, x_standing, color='cyan', label='Standing Wave')
        plt.grid(True)
     

try :
        if type_3or1 == 1 :
                wave_iter = animate_wave_iterable_3plots
        else:
                wave_iter = animate_wave_iterable_AllInOne
        anim = animate_frame.FuncAnimation(fig, wave_iter,frames=400, interval=30)
except Exception as ex:
        print(ex)
else :
        plt.show()
finally :
        exit()

