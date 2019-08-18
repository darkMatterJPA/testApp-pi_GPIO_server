#!/usr/bin/

from gpiozero import LED
import socketio
from time import sleep


red = LED(14)
red.off()

sio = socketio.Client()
sio.connect('http://localhost:6969')

@sio.event
def message(data):
    print(data)
    sio.emit('data', data[::-1])
    if data == "on":
        red.on()
    elif data == "off":
        red.off()

