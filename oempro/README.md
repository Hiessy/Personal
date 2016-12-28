#Para que la aplicacion funcione correctamente se debaran crear las siguientes carpetas en el disco "C" o el "/" dependiendo del sistema operativo que se use.

#1. Se debera generar la carpeta "/controlm/integracion/load_data" la cual recivira el resultado de las inserciones de los clientes a los cuales se les acaba de crear una lista de subscripcion

#2. Se debera generar la "/controlm/integracion/output" la misma debera contener el archivo a parsear para que se pueda generar la nueva lista de correo y crear la tabla con los nuevos subcriptores

#3. Se bedera generar la carpeta "/controlm/integracion/resources" que contendra el archivo "config.properties". El mismo debera tener la siguiente informacion:

dir.output = /controlm/integracion/load_data/

dir.path=/controlm/integracion/output

file.extension=txt

file.separator=;

oempro.user.name=admin

timer.init.hour=16:31

timer.end.hour=08:00

timer.dummy.query.time=120

#Esto es necesario para que se pueda modificar los parametrso de ejecucion sin necesidad de recompilar la aplicacion. Adicionalmente exite un archivo "config.properties" dentro del classpath que cuenta con los parametros de configuracion de la base de datos que se mantuvo en el classpath por motivos de seguridad.