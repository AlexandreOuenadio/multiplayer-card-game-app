
function authVerification(username, password, repassword=null){

    

    const regexUserName = new RegExp("^[a-zA-Z][a-zA-Z0-9]*$")

    if (!regexUserName.test(username)){

        return {
            "errorMessage": "Votre nom doit commencer par une lettre et doit ne contenir que des lettres (sans accents) et des chiffres !"
        }
    }

    if (password.length < 8){

        return {
            "errorMessage": "Veuillez choisir un mot de passe de plus de 8 caractères !"
        }
    }

    if(repassword !== null){
     
        if(password !== repassword){

            return {
                "errorMessage": "Les deux mots de passe doivent correspondre !"
            }

        }
        

    }


    return {

        "errorMessage": ""
    }


}


function displayAuthErrorMessage(message){
    const formStatus = document.getElementById("form-status");
    const formStatusMessage = document.getElementById("form-status-message");
    formStatus.classList.add("error");

    formStatusMessage.textContent = message;

}


function isCookiePresent(cookieName) {
    return document.cookie.indexOf(cookieName + '=') !== -1;
}

function getCookie(nomCookie) {
    var nom = nomCookie + "=";
    var cookies = document.cookie.split(';');
  
    for(var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i];
      while (cookie.charAt(0) === ' ')
        cookie = cookie.substring(1);
      if (cookie.indexOf(nom) === 0)
        return cookie.substring(nom.length, cookie.length);
    }
  
    return "";
}

function getUserId(){

    return getCookie("id");
}

function checkIfUserConnected(){

    if(isCookiePresent("id")){
        return true;
    }

    return false;


}


async function logoutUser(){

    try {
        const res = await fetch('http://localhost/userMicroservice/logout',{
            method: "POST"
        });
    }catch(e){
        console.log(e);
    }
    

    
}




export {authVerification, displayAuthErrorMessage, checkIfUserConnected, logoutUser, getUserId};
