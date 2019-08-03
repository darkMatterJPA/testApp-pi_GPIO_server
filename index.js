const http = require('http');
const port = 8080;

var stat;


const requestHandler = (request, response) => {
    if(request.method === 'POST')
    {
        stat = request.body;
        response.end(stat);
    }
    
    if(request.method === 'GET')
    {
        response.end(stat);
    }

    console.log(request.url);
    console.log(stat);
    
  }


  const server = http.createServer(requestHandler);

server.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
});