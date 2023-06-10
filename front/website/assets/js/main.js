import Router from "./classes/Router.js";
import {getLoginFormLogic, getRegisterFormLogic, getRoomFormLogic} from './functions/formLogic.js';
import {checkIfUserConnected, getUserId} from './functions/authFunctions.js';
import { displayCards } from "./functions/displayCardsLogic.js";
import { displayRooms } from "./functions/displayRoomLogic.js";



const router = new Router();

// --------- CONFIGURATION DU ROUTEUR ---------

// ---------- HOME ---------- 

router.addRoute({
    endpoint: '/',
    template: 'home.html',
    options: {hasNavigation: true, isHome: true, title: "Accueil"},
    callback: () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }
    }
})
// ---------- ABOUT ---------- 

router.addRoute({
    endpoint: '/about',
    template: 'about.html',
    options: {hasNavigation: true, title: "A propos"},
    callback:  async () =>{
        const res = await fetch('http://localhost/userMicroservice/1');
        const data = await res.json();
        console.log(data);
    }
})



// ---------- AUTH ROUTES ---------- 

router.addRoute({
    endpoint: '/login',
    template: 'login.html',
    options: {title: "Se connecter"},
    callback: () =>{
        if(checkIfUserConnected()){
            return router.resolve('/');
        }
        getLoginFormLogic();
    }
})

router.addRoute({
    endpoint: '/register',
    template: 'register.html',
    options: {title: "S'enregistrer"},
    callback: () =>{
        if(checkIfUserConnected()){
            return router.resolve('/');
        }
        getRegisterFormLogic();
    }
})

// ---------- TRANSACTION ROUTES ---------- 


router.addRoute({
    endpoint: '/card/buy',
    template: 'displayCards.html',
    options: {hasNavigation: true, title: "Acheter des cartes"},
    callback: async () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }


        const res = await fetch('http://localhost/cardMicroservice/getAllCards');
        const cards = await res.json();

        displayCards(cards,"Acheter");


        


    }
})

router.addRoute({
    endpoint: '/card/sell',
    template: 'displayCards.html',
    options: {hasNavigation: true, title: "Vendre des cartes"},
    callback: async () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }

        const res = await fetch(`http://localhost/cardMicroservice/getUserCards/${getUserId()}`);
        const cards = await res.json();

        displayCards(cards,"Vendre");
    }
})


// ---------- GAME ROUTES ----------  

router.addRoute({
    endpoint: '/game/room',
    template: 'room.html',
    options: {hasNavigation: true, title: "Jouer"},
    callback: async () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }

        const res = await fetch('http://localhost/roomMicroservice/getAllRooms');
        const rooms = await res.json();

        displayRooms(rooms, router);
        getRoomFormLogic(router);
        
    }
})

router.addRoute({
    endpoint: '/game/room/waiting',
    template: 'waitingRoom.html',
    options: {hasNavigation: true, title: "En attente de joueurs..."},
    callback: () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }
    }
})

router.addRoute({
    endpoint: '/game/card',
    template: 'displayCards.html',
    options: {hasNavigation: true, title: "Choisissez une carte à jouer"},
    callback: async () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }

        const res = await fetch(`http://localhost/cardMicroservice/getUserCards/${getUserId()}`);
        const cards = await res.json();

        displayCards(cards,"Choisir",router);
    }
})

router.addRoute({
    endpoint: '/game/play',
    template: 'game.html',
    options: {hasNavigation: true, title: "C'est parti !"},
    callback: async () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }

       
    }
})

router.addRoute({
    endpoint: '/game/result',
    template: 'gameResult.html',
    options: {hasNavigation: true, title: "Résultat de la partie"},
    callback: () =>{

        if(!checkIfUserConnected()){
            return router.resolve('/login');
        }
    }
})


  

document.addEventListener("DOMContentLoaded", async () =>{
    console.log("Page loaded");

    //Résolution au clic sur les flèches d'historique du navigateur.
    window.addEventListener("popstate", async () =>{
        console.log("Changement route")
        await router.resolve(location.pathname);

    })

    //Résolution au chargement de la page
    await router.resolve(location.pathname);
   


})

