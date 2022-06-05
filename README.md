# ClinicaOdontologica
Proyecto Integrador para la materia BackEnd de la carrera C.T.D en Digital House

Se trata de un pequeño sistema de gestión de clínica odontológica, donde podemos registrar pacientes, odontológos y asignar turnos entre ellos. Cuenta con unas vistas básicas realizadas en HTML, CSS (con la ayuda de Bootstrap) y JavaScript puro ya que me enfoque en el correcto funcionamiento del backend. A pesar de ello, mediante las vistas se puede registrar un paciente y ver el listado de pacientes, de la misma manera podemos registrar y listar odontologos y, en cuanto a los turnos, podemos ver una lista de ellos.

Los métodos son testeados con JUnit.

Se implementó un login con Spring Security, el cual nos permite ingresar como un rol de ADMIN o USER. Estas credenciales pueden verse en la clase "DataLoader" dentro de la carpeta "security".
