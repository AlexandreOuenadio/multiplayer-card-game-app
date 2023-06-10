import { getUserId } from "./authFunctions.js";
import { Game } from "./gameLogic.js";

//Fonction utilisée pour l'affichage de /card/buy, /card/sell, /game/card

function displayCards(cards, action,router){

    

       
        if (action == "Acheter"){
            document.getElementById("displayCards-title-icon").setAttribute("name","cart-outline" );
        
        }else if (action == "Vendre"){
            document.getElementById("displayCards-title-icon").setAttribute("name","cash-outline" );
        }   

        document.getElementById("displayCards-title").textContent = document.title;
       

    if (cards.length > 0){

        cards.forEach(card =>{
            //POUR CHAQUE CARTE ...
            console.log(card);
            const templateCard = document.getElementById("template-table-card")
            const cloneCard  = document.importNode(templateCard.content,true).children;
            console.log(cloneCard);

            Array.from(cloneCard).forEach(field =>{
                //ON PARCOURS SES CHAMPS ...
                field.setAttribute("data-cardId", card.cardId);

                if(field.hasAttribute("data-name")){
                    field.textContent = card.name;
                }else if(field.hasAttribute("data-family")){
                    field.textContent = card.family;
                }else if(field.hasAttribute("data-hp")){
                    field.textContent = card.hp;
                }else if(field.hasAttribute("data-mp")){
                    field.textContent = card.mp;
                }else if(field.hasAttribute("data-attack")){
                    field.textContent = card.attack;
                }else if(field.hasAttribute("data-defence")){
                    field.textContent = card.defence;
                }else if(field.hasAttribute("data-price")){
                    field.textContent = card.price;
                }else if(field.hasAttribute("data-cardSelect")){
                    //GESTION DU CLICK DU BOUTON DE SELECTION DE LA CARTE
                    field.querySelector(".displayCards-table-button").addEventListener('click', ()=>{
                        console.log(card);
                        if(action === "Acheter"){
                            document.getElementById("displayCards-card-selection").setAttribute("data-cardSelected", card.cardId );

                        }else if (action === "Vendre"){
                            document.getElementById("displayCards-card-selection").setAttribute("data-cardSelected", card.cardUserId);
                        }else if (action === "Choisir"){
                            document.getElementById("displayCards-card-selection").setAttribute("data-cardSelected", card.cardUserId);
                        }

                        const templateCardSelected = document.getElementById("template-displayCards-card-selected");
                        //PREMIERE DIV AVEC LE PRIX, LE BOUTON D'ACTION DU FORMULAIRE
                        const cloneCardSelectedFirstPart  = document.importNode(templateCardSelected.content,true).children[0]; 
                        console.log(cloneCardSelectedFirstPart);
                        
                        //ON MODIFIE LE TITRE DU BOUTON D'ACTION
                        cloneCardSelectedFirstPart.innerHTML = cloneCardSelectedFirstPart.innerHTML.replace(/{{displayCards-verb}}/, action);

                        //ON MODIFIE EN FONCTION DE L'ACTION
                        //on affiche le prix que si l'on se trouve sur /card/buy/ ou /card/sell
                        if(action === "Acheter" || action === "Vendre"){
                            cloneCardSelectedFirstPart.innerHTML = cloneCardSelectedFirstPart.innerHTML.replace(/{{price}}/, card.price);

                        }else if (action === "Choisir"){ //On est sur /game/card

                            cloneCardSelectedFirstPart.querySelector("#card-price").classList.add("not-active");
                           
                        }
                        //on change l'action du formulaire et le type de requête lorsqu'on clique sur l'action
                        const formAction = cloneCardSelectedFirstPart.querySelector("#displayCards-form");
                        
                        formAction.addEventListener('submit', async (e) =>{

                            e.preventDefault();

                            //on récupère l'id de la carte selectionnée
                            console.log(document.getElementById("displayCards-card-selection"))
                            const cardId = document.getElementById("displayCards-card-selection").getAttribute("data-cardSelected");
                            
                            console.log(cardId);
                            console.log(cloneCardSelectedFirstPart);

                            try{
                                if(action === "Acheter"){

                                    const res = await fetch(`http://localhost/transactionMicroservice/buy/${cardId}`, {
                                        method: "POST",

                                    })

                                    if(res.ok){
                                        document.getElementById("displayCards-message").textContent = "Votre achat a été effectué avec succès !";
                                        document.getElementById("displayCards-message-container").classList.add("active"); 
                                    }else{
                                        document.getElementById("displayCards-message").textContent = "Il y a eu un problème lors de l'achat de la carte !";
                                        document.getElementById("displayCards-message-container").classList.add("active", "error"); 
                                    }
                                }else if(action === "Vendre"){

                                    const res = await fetch(`http://localhost/transactionMicroservice/sell/${cardId}`, {
                                        method: "POST",

                                    })

                                    if(res.ok){
                                        document.getElementById("displayCards-message").textContent = "Votre vente a été effectué avec succès !";
                                        document.getElementById("displayCards-message-container").classList.add("active"); 
                                    }else{
                                        document.getElementById("displayCards-message").textContent = "Il y a eu un problème lors de la vente de la carte !";
                                        document.getElementById("displayCards-message-container").classList.add("active", "error"); 
                                    }


                                }else if (action === "Choisir"){

                                    document.getElementById("displayCards-button").addEventListener("click", async (e)=>{
                                        e.preventDefault();
                                        console.log("Choisir!")
                        
                                        try{
                                            
                                            const res = await fetch("http://localhost/gameMicroservice/cardChosen", {
                                                method: "POST",
                                                body: JSON.stringify({
                                                    player1CardId: +cardId
                                                }),
                                                headers: {
                                                    "Content-Type": "application/json"
                                                }
                                            })
                                            if(res.ok){
                                                console.log("Card CHosen!")
                                                const game = new Game(router);
                                                const roomName = document.getElementById("main-displayCards").getAttribute("data-roomName");
                                                game.chooseCard(roomName);
                                            }
                                        }catch(e){
                                            console.log(e);
                                        }
                        
                                    })
                                }

                            }catch(e){
                                console.log(e);
                            }
                                
                                


                        
                            

                            // if(action == "Acheter"){



                            // }else if(action == "Vendre"){



                            // }



                        });
                
                        
                        
                        
                        
                        //SECOND DIV AVEC TOUT LE RESTE
                        const cloneCardSelectedSecondPart  = document.importNode(templateCardSelected.content,true).children[1]; 

                        cloneCardSelectedSecondPart .innerHTML = cloneCardSelectedSecondPart.innerHTML.replace(/{{description}}/, card.description)
                                                            .replace(/{{name}}/, card.name)
                                                            .replace(/{{family}}/, card.family)
                                                            .replace(/{{hp}}/, card.hp)
                                                            .replace(/{{mp}}/, card.mp)
                                                            .replace(/(<img\s+src=")\s*(")/,`$1${card.imgURL}$2`)
                                                            .replace(/{{attack}}/, card.attack)
                                                            .replace(/{{defence}}/, card.defence);
                                                            
                                                            
                        
                        
                        
                        //ON SUPPRIME LA CARTE COURANTE SELECTIONNEE
                        const nonTemplateChildren = Array.from(document.getElementById("displayCards-card-selection").children).filter(child => child.id !== "template-displayCards-card-selected");
                            nonTemplateChildren.forEach(child => {
                            document.getElementById("displayCards-card-selection").removeChild(child);
                        });
                        //ON AFFICHE LA NOUVELLE CARTE SELECTIONNEE
                        document.getElementById("displayCards-card-selection").appendChild(cloneCardSelectedFirstPart);
                        document.getElementById("displayCards-card-selection").appendChild(cloneCardSelectedSecondPart);

                    })


                }else{
                    console.log("Un des champs ne possède pas d'attribut data ou a un mauvais attribut data")
                }

                //ON APPEND LE CHAMPS DANS LE DOM
                document.getElementById("displayCards-table").appendChild(field);

            })


            
            
        });

    }




}


export {displayCards};

