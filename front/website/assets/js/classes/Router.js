import PageRenderer from "./pageRenderer.js";

export default class {

    #register =  [];
    #pageRenderer = null;

    constructor(){
        
        this.#pageRenderer = new PageRenderer();

        //--------- CONFIGURATION DU PAGERENDERER ---------
        this.#pageRenderer.setRoot("root");
        this.#pageRenderer.setTemplateFolder("/templates");

        

    }


    addRoute(config){

        //options = {hasNavigation: boolean, isHome: boolean}
        this.#register.push(config);
    }

    async resolve(url){
        
        //Recherche dans la liste des routes pour une correspondance
        const foundRoute = this.#register.find(route => route.endpoint === url);

        //Affichage de la view correspondant à la route trouvée
        if (foundRoute != null){
            
            if(foundRoute.options && foundRoute.options.hasNavigation){

                await this.#pageRenderer.render(foundRoute.template, foundRoute.options);
                

            }else{
                await this.#pageRenderer.render(foundRoute.template);
            }

            if(foundRoute.options && foundRoute.options.title){
                document.title = foundRoute.options.title;
            }
            

            

        

            //Résolution au clic d'un lien dans la navigation du site ou autre
            const linkElements = document.querySelectorAll('[data-link]');
            linkElements.forEach(element => {
                element.addEventListener('click', async (e) => {
                    e.preventDefault();
                    await this.resolve(element.getAttribute('href'));
                });
            });

            
            //On met à jour l'url
            history.pushState(null, null, foundRoute.endpoint);


            foundRoute.callback();


            return;
        }
            
        //Si route non trouvée affiche 404
        //On met à jour l'url
        history.pushState(null, null, '/pagenotfound');
        await this.#pageRenderer.render('404.html', null);

        return;
        

        
    }


}