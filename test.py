from gpiozero import LED
import socketio
from time import sleep


red = LED(14)
red.off()

sio = socketio.Client()

sio.emit('message',{'data':'off'})

@sio.event
def connect():
    print('connection established')

@sio.event
def disconnect():
    print('disconnected from server')

@sio.event
def message(data):
    print(data)
    sio.emit('data', data[::-1])
    if data == "on":
        red.on()
        print(data)
    elif data == "off":
        red.off()
        print(data)
    else:
        print(data)

sio.connect('http://localhost:6969')
sio.wait()