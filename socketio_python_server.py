import socketio

# create a Socket.IO server
sio = socketio.Server()

@sio.event
def connect(sid, environ):
    print('connect ', sid)

@sio.event
def disconnect(sid):
    print('disconnect ', sid)

sio.wait()

# @sio.event
# def connect(sid, environ):
#     raise ConnectionRefusedError('authentication failed')