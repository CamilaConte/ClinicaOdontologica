window.addEventListener('load', function (){
    (function(){

      //con fetch invocamos a la API de pacientes con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
        .then(function(respuesta){
            return respuesta.json();
        })
        .then(function (info) {
            console.log(info);
            //recorremos la colección de pacientes del JSON
            for(let i=0;i<info.length;i++){
                //por cada paciente armaremos una fila de la tabla
                //cada fila tendrá un id
                var table = document.getElementById("pacienteTable");
                var pacienteRow =table.insertRow();
                let tr_id = 'tr_' + info[i].id;
                pacienteRow.id = tr_id;


                //armamos cada columna de la fila
                pacienteRow.innerHTML = '<td class="td_id">' + info[i].id + '</td>' + '<td class="td_nombre">' + info[i].nombre.toUpperCase() + '</td>' +
                    '<td class="td_apellido">' + info[i].apellido.toUpperCase() + '</td>' + '<td class="td_dni">' + info[i].dni + '</td>';

            };

        })
    })

    (function(){
            let pathname = window.location.pathname;
            if (pathname == "/listaPacientes.html") {
                document.querySelector(".nav .nav-item a:last").addClass("active");
            }
        }
    )
    })
