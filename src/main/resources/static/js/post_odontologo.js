window.addEventListener('load', function(){
    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function(event){
        event.preventDefault();        

        const odontologo = {
            nombre: document.querySelector("#nombre").value,
            apellido: document.querySelector("#apellido").value,
            matricula: document.querySelector("#matricula").value
        }

        const url = '/odontologos'

        const settings = {
            method: 'POST',
            body: JSON.stringify(odontologo),
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch(url, settings)
            .then(response => { response => response.json() })
            .then(data => {
                document.querySelector("header").innerHTML = '<div class="alert alert-success" role="alert">'+
                'El odontólogo se ha registrado correctamente'+
                '</div>';

                //reseteamos los campos
                resetUploadForm();
            })
            .catch(error => {
                document.querySelector("header").innerHTML = '<div class="alert alert-danger" role="alert">Error: ' + error + '</div>';

                resetUploadForm();
            })

    })

    function resetUploadForm(){
        document.querySelector("#nombre").value="";
        document.querySelector("#apellido").value="";
        document.querySelector("#matricula").value="";
    }

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/registrarOdontologo.html") {
             document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
});