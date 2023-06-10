import {authVerification, displayAuthErrorMessage} from "./authFunctions.js";
import { io } from "../libraries/socket.io.esm.min.js";
import { Game } from "./gameLogic.js";
function getLoginFormLogic(){

    const loginForm = document.getElementById("form-login");

    loginForm.addEventListener("submit", async (e) =>{
        e.preventDefault();
    
        const username = loginForm.elements.namedItem("username").value
        const userpassword = loginForm.elements.namedItem("userpassword").value 
    
        const {errorMessage: front_auth_verification_error_message} = authVerification(username, userpassword);
    
        if (front_auth_verification_error_message === ""){
            
            const response = await fetch("http://localhost/userMicroservice/login", {
                method: "POST",
                body: JSON.stringify({
                    "name": username,
                    "password": userpassword
        
                }),
                headers:{
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }
            })
    
    
            if(response.ok){
                return location.href ="/";
            }
            
            // else{
            //     displayAuthErrorMessage(webservice_error_message);
            // }
            
            
        }else{
    
            displayAuthErrorMessage(front_auth_verification_error_message);
    
        }
        
    })
            
}


function getRegisterFormLogic(){

    const registerForm = document.getElementById("form-register");

    registerForm.addEventListener("submit", async (e) =>{
        e.preventDefault();
    
        const username = registerForm.elements.namedItem("username").value
        const userpassword = registerForm.elements.namedItem("userpassword").value 
        const userrepassword = registerForm.elements.namedItem("userrepassword").value 
    
        const {errorMessage: front_auth_verification_error_message} = authVerification(username, userpassword, userrepassword);
    
        if (front_auth_verification_error_message === ""){
            
            const response = await fetch("http://localhost/userMicroservice/register", {
                method: "POST",
                body: JSON.stringify({
                    "name": username,
                    "password": userpassword
        
                }),
                headers:{
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }
            })

            if(response.ok){
                location.href ="/login";
            }
             

            //else{
            //     displayAuthErrorMessage(webservice_error_message);
            // }
            
            
        }else{
    
            displayAuthErrorMessage(front_auth_verification_error_message);
    
        }
        
    })



}



function getRoomFormLogic(router){

    const roomForm = document.getElementById("room-creation-form");
    roomForm.addEventListener("submit", async (e)=>{
        e.preventDefault();
        const roomName = document.getElementById('room-name').value; 
        const bet = document.getElementById('room-bet').value;

        const game = new Game(router);
        await game.createRoom(roomName,bet);

    })


}


export {getLoginFormLogic, getRegisterFormLogic, getRoomFormLogic};




