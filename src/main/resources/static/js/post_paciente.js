window.addEventListener('load', function(){
    const formulario = document.querySelector('#add_new_paciente');

    formulario.addEventListener('submit', function(event){
        event.preventDefault();        

        const domicilio = {
            calle: document.querySelector("#calle").value,
            numero: document.querySelector("#numero").value,
            localidad: document.querySelector("#localidad").value,
            provincia: document.querySelector("#provincia").value
        }

        const paciente = {
            nombre: document.querySelector("#nombre").value,
            apellido: document.querySelector("#apellido").value,
            email: document.querySelector("#email").value,
            dni: document.querySelector("#dni").value,
            fechaIngreso: document.querySelector("#fechaIngreso").value,
            domicilio: domicilio
        }

        const url = '/pacientes'

        const settings = {
            method: 'POST',
            body: JSON.stringify(paciente),
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch(url, settings)
            .then(response => { response => response.json() })
            .then(data => {
                document.querySelector("header").innerHTML = '<div class="alert alert-success" role="alert">'+
                'El paciente se ha registrado correctamente'+
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
        document.querySelector("#email").value="";
        document.querySelector("#dni").value="";
        document.querySelector("#fechaIngreso").value="";
        document.querySelector("#calle").value = "";
        document.querySelector("#numero").value = "";
        document.querySelector("#localidad").value = "";
        document.querySelector("#provincia").value = "";
    }

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/registrarPaciente.html") {
             document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })
});
    
        


