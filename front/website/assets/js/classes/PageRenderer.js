import { getUserId, logoutUser } from "../functions/authFunctions.js";

export default class PageRenderer{
    #root = null;
    #templateFolder = null;
    
    setRoot(root){
        this.#root = document.getElementById(root);
    }

    setTemplateFolder(templateFolder){
        this.#templateFolder = templateFolder;
    }




    async render(template, options=null){

        //options = {hasNavigation: boolean, isHome: boolean}
        this.#root.style.display = "none";
        //On efface toute la page et en redéssine une nouvelle
        this.#root.innerHTML = "";

        
        
        try {
            //S'il y a une navigation on l'a déssine avant le template
            if(options && options.hasNavigation){
                const responseNav = await fetch(`${this.#templateFolder}/partials/navigation.html`);
                
                if (responseNav.ok) {

                    const HTMLNav = await responseNav.text();
                    this.#root.insertAdjacentHTML("beforeend", HTMLNav);;

                    const res = await fetch(`http://localhost/userMicroservice/${getUserId()}`);
                    const userData = await res.json();
                    document.getElementById("nav-username").textContent = userData.name;
                    document.getElementById("nav-user-money").textContent= userData.money;
                    document.getElementById("logoutBtn").addEventListener("click", async (e) =>{  
                        e.preventDefault();
                        e.stopPropagation();
                        await logoutUser();
                        window.location.href = "/login";
            
                    }) 
                    if(options.isHome){
                    
                        this.#root.querySelector("#back").style.display = "none";
                    

                    }

    
                }else {
    
                  throw new Error('Request failed with status ' + responseNav.status);
                }
            
            }


            const responseTemp = await fetch(`${this.#templateFolder}/${template}`);
            if (responseTemp.ok) {

                const HTMLTemp = await responseTemp.text();
                this.#root.insertAdjacentHTML("beforeend", HTMLTemp);
                this.#root.style.display = "block";

            }else {

              throw new Error('Request failed with status ' + responseTemp.status);
            }
           
            
           

        }catch(error) {

            console.error('Error:', error.message);
            
        }


        
       


    }


}