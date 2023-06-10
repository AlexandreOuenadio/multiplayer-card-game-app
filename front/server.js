const express = require("express");
const path = require("path");


const app = express();




app.use('/assets', express.static(path.resolve(__dirname, "website", "assets")));
app.use('/templates', express.static(path.resolve(__dirname, "website", "templates")));
app.get('/*', (req,res) =>{

    res.sendFile(path.resolve(__dirname, "website", "index.html"));


});

const PORT = process.env.PORT || 8085;

app.listen(PORT, ()=>{

    console.log(`Server listening on port ${PORT}`)
});