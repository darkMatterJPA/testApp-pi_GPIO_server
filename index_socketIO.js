const server = require('http').createServer();
const io = require('socket.io')(server);
io.on('connection', client => {
    console.log('a user connected');
  client.on('event', data => { /* … */ });
  client.on('disconnect', () => { /* … */ });
});
server.listen(5000, function(){
    console.log('listening on *:5000');
  });