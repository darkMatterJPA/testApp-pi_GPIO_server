const WebSocket = require('ws')

const wss = new WebSocket.Server({ port: 5000 })

wss.on('listening', ws=> {
    console.log('listening on *:5000')
})

wss.on('connection', ws => {
  console.log('a user connected')
 
 // ws.send('ho!')
})

ws.on('message', message => {
    console.log(`Received message => ${message}`)
  })