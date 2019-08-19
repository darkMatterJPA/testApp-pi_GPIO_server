from gpiozero import LED
import socketio
from time import sleep


red = LED(14)
red.off()

sio = socketio.Client()

@sio.event
def connect():
    print('connection established')
    sio.emit('message',{'data':'off'})

@sio.event
def disconnect():
    print('disconnected from server')
    exit(0)

@sio.event
def message(data):
    print(data)
    #sio.emit('data', data[::-1])
    if data == "on":
        red.on()
    elif data == "off":
        red.off()

sio.connect('http://localhost:5000')
sio.wait()