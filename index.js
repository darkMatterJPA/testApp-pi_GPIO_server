var io = require('socket.io')(6969);

io.on('connection', function (socket) {
  console.log('Connected')
  socket.on('message', function () {

   });
  socket.on('disconnect', function () { 

  });
});