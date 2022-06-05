window.addEventListener('load', function (){
    (function(){

      //con fetch invocamos a la API de turnos con el método GET
      //nos devolverá un JSON con una colección de turnos
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
        .then(function(respuesta){
            return respuesta.json();
        })
        .then(function (info) {
            console.log(info);
            //recorremos la colección de turnos del JSON
            for(let i=0;i<info.length;i++){
                //por cada turno armaremos una fila de la tabla
                //cada fila tendrá un id
                var table = document.getElementById("turnosTable");
                var turnoRow =table.insertRow();
                let tr_id = 'tr_' + info[i].id;
                turnoRow.id = tr_id;


                //armamos cada columna de la fila
                turnoRow.innerHTML = '<td class="td_paciente">' + info[i].paciente.nombre.toUpperCase() + " " + info[i].paciente.apellido.toUpperCase() + '</td>' +
                    '<td class="td_odontologo">' + info[i].odontologo.nombre.toUpperCase() + " " + info[i].odontologo.apellido.toUpperCase() + '</td>' + 
                    '<td class="td_fecha">' + info[i].fecha + '</td>';

            };

        })
    })

    (function(){
            let pathname = window.location.pathname;
            if (pathname == "/listaTurnos.html") {
                document.querySelector(".nav .nav-item a:last").addClass("active");
            }
        }
    )
})
