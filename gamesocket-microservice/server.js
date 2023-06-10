
const {createServer} = require('http');
const {Server}= require('socket.io');
const axios = require("axios");


const serv = createServer();
const io = new Server(serv, {
  cors: true,
  origins: ["http://localhost/"]
});




// // Lorsqu'un client se connecte
io.on('connection', (socket) => {
  console.log('Nouvelle connexion : ' + socket.id);

  // Événement pour rejoindre la salle d'attente
  socket.on('joinWaitingRoom', (roomName) => {

    socket.join(roomName);
    console.log(`${socket.id} a rejoint la room ${roomName}`);

  });

  // Lorsqu'un client quitte la salle d'attente
  socket.on('leaveWaitingRoom', () => {
    // Votre logique pour supprimer le client de la salle d'attente
  });

  socket.on('cardChosen', async (roomName)=>{
    const res = await axios.get(`http://localhost:8087/isGameReady/${roomName}`);
    if(res.status === 200 && res.data){

        console.log(res.data)
        const res2 = await axios.get(`http://localhost:8087/getGameResult/${roomName}`);
        if(res2.status === 200){
          
          io.to(roomName).emit("gameResult", res2.data);
        
        }
    }

  })

  // Lorsque le serveur est prêt à démarrer le jeu ou à effectuer une autre action
  socket.on('joinRoom', (roomName) => {
    socket.join(roomName);
    console.log(`${socket.id} a rejoint la room ${roomName}`)
    io.to(roomName).emit('playersChooseCard', roomName);
  });

  // Autres événements ou logique spécifique à votre application

  // Lorsqu'un client se déconnecte
  socket.on('disconnect', () => {
    // Votre logique pour gérer la déconnexion du client
  });
});

const PORT = process.env.PORT || 8088;
// Démarrez le serveur
serv.listen(PORT);

console.log('Server listening on port ' + PORT);