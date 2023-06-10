//Fonction utilisée pour l'affichage de /game/room

import { Game } from "./gameLogic.js";

function displayRooms(rooms, router){

    if (rooms.length > 0){

        rooms.forEach(room =>{
            //POUR CHAQUE CARTE ...
            console.log(room);
            const templateRoom= document.getElementById("template-table-room")
            const cloneRoom = document.importNode(templateRoom.content,true).children;
            console.log(cloneRoom);

            Array.from(cloneRoom).forEach(field =>{
                //ON PARCOURS SES CHAMPS ...
                field.setAttribute("data-roomId", room.roomId);

                if(field.hasAttribute("data-name")){
                    field.textContent = room.name;
                }else if(field.hasAttribute("data-user")){
                    field.textContent = room.player1Id;
                }else if(field.hasAttribute("data-bet")){
                    field.textContent = room.bet;
                }else if(field.hasAttribute("data-roomSelect")){
                    //GESTION DU CLICK DU BOUTON DE SELECTION DE LA ROOM
                    field.querySelector(".room-table-button").addEventListener('click', async ()=>{

                        const game = new Game(router);
                        await game.joinRoom(room.name);
                        console.log(room.name);

                    });
                }

                document.getElementById("room-table").appendChild(field);
                        
            });


            
            
        });

    }




}


export {displayRooms};

