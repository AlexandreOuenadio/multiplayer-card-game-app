import { getUserId } from "./authFunctions.js";
import { io } from "../libraries/socket.io.esm.min.js";

class Game{

    #socket = null;
    #room = null;
    #router = null;

    
    
    constructor(router){

        this.#socket =  io();
        this.#router = router;
        console.log(io);


        this.#socket.on("playersChooseCard", async (roomName)=>{
           
            await this.#router.resolve('/game/card');
            console.log(document.getElementById("main-displayCards"));
            document.getElementById("main-displayCards").setAttribute("data-roomName", roomName);

            
        

        })



        this.#socket.on("gameResult", async (result)=>{

            //On attend 5 secondes avant d'afficher les résultats
            setTimeout(()=>{}, 5000);


           
            this.#router.resolve('/game/result');

            // fetch('http://localhost://')


            

        })

        


    }

    async createRoom(roomName, bet){


        if(bet <= 1500 && bet >= 1 && roomName != ""){

            try{

                const res = await fetch("http://localhost/roomMicroservice/create", {
                    method:"POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        player1Id: getUserId(),
                        name: roomName,
                        bet: bet
                    })
                })

                if(res.ok){

                    this.joinWaitingRoom(roomName);

                }

            }catch(e){

                console.log(e);
            }
            

        }else{

            document.getElementById("room-creation-message").textContent = "La mise doit être comprise entre 1 et 1500$ et le nom de la room non vide !";
            document.getElementById("room-creation-message-container").classList.add("active", "error");

        }
    }

    joinWaitingRoom(roomName){

        this.#router.resolve('/game/room/waiting');
        this.#socket.emit('joinWaitingRoom', roomName);

    }

    async joinRoom(roomName){

        try{

            const res = await fetch(`http://localhost/roomMicroservice/join/${roomName}`,
            {method: "POST"});

            if(res.ok){
                console.log("yes")
                console.log(this.#socket);
                this.#socket.emit('joinRoom', roomName);
            }

        }catch(e){
            console.log(e);
        }
        
        
    }

    chooseCard(roomName){

        this.#socket.emit('cardChosen', roomName);
        this.#router.resolve('/game/play');

    }

}

export {Game};