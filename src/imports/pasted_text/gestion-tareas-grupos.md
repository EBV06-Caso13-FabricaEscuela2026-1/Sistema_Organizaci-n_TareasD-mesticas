📌 HU-02: Creación de grupo familiar

Como administrador del sistema,
quiero crear un grupo familiar,
para gestionar y organizar el espacio.

✅ CA-01: Control de creación

Escenario: CA-01-A - Solo administradores pueden crear grupos

Dado que el usuario está autenticado
Y tiene rol de administrador
Cuando intenta crear un grupo familiar
Entonces el sistema permite iniciar el proceso de creación

Escenario: CA-01-B - Restricción para usuarios no administradores

Dado que el usuario está autenticado
Y no tiene rol de administrador
Cuando intenta crear un grupo familiar
Entonces el sistema rechaza la acción
Y muestra un mensaje indicando que no tiene permisos para crear grupos
✅ CA-02: Validación del nombre del grupo

Escenario: CA-02-A - Longitud inválida del nombre

Dado que el administrador ingresa un nombre de grupo
Cuando el nombre tiene menos de 6 caracteres o más de 25
Entonces el sistema rechaza la acción
Y muestra un mensaje indicando que el nombre debe tener entre 6 y 25 caracteres
Y permite al administrador ingresar un nuevo nombre

Escenario: CA-02-B - Caracteres permitidos

Dado que el administrador ingresa un nombre de grupo
Cuando el nombre contiene únicamente letras, números, espacios, tildes o "ñ"
Entonces el sistema acepta el nombre como válido

Escenario: CA-02-C - Caracteres no permitidos

Dado que el administrador ingresa un nombre de grupo
Cuando el nombre contiene caracteres especiales (@, #, %, etc.)
Entonces el sistema rechaza el nombre
Y muestra un mensaje indicando los caracteres permitidos
Y solicita corregir el nombre
✅ CA-03: Generación de código único

Escenario: CA-03-A - Asignación automática de código

Dado que el administrador ha ingresado un nombre válido
Cuando finaliza la creación del grupo
Entonces el sistema genera automáticamente un código único
Y asocia el grupo con ese código

Escenario: CA-03-B - Visualización del código

Dado que el administrador ha creado el grupo
Cuando el sistema realiza la asociación
Entonces muestra un mensaje de confirmación
Y presenta el código único asignado
✅ CA-04: Confirmación de creación

Escenario: CA-04-A - Creación exitosa

Dado que el administrador ingresa un nombre válido
Cuando confirma la creación
Entonces el sistema registra el grupo correctamente
Y muestra un mensaje de éxito

Escenario: CA-04-B - Cancelación de creación

Dado que el administrador ingresa un nombre válido
Cuando decide cancelar
Entonces el sistema no registra el grupo
Y descarta la información
Y finaliza el proceso
📌 HU-11: Creación de tareas

Como administrador del sistema,
quiero crear tareas dentro del hogar,
para gestionar actividades.

✅ CA-01: Validación del nombre de la tarea

Escenario: CA-01-A - Nombre único

Dado que existe un grupo familiar
Cuando crea una tarea con nombre único
Entonces el sistema valida que no exista previamente
Y registra la tarea

Escenario: CA-01-B - Nombre duplicado

Dado que el administrador crea una tarea
Cuando el nombre ya existe
Entonces el sistema rechaza la creación
Y muestra mensaje de nombre en uso

Escenario: CA-01-C - Longitud válida

Dado que ingresa un nombre
Cuando tiene entre 3 y 60 caracteres
Entonces el sistema lo acepta

Escenario: CA-01-D - Longitud inválida

Dado que ingresa un nombre
Cuando tiene menos de 3 o más de 60 caracteres
Entonces el sistema rechaza
Y muestra mensaje de error

Escenario: CA-01-E - Caracteres permitidos

Dado que ingresa un nombre
Cuando contiene letras, números, espacios, tildes y ñ
Entonces el sistema lo acepta

Escenario: CA-01-F - Caracteres no permitidos

Dado que ingresa un nombre
Cuando contiene caracteres especiales
Entonces el sistema rechaza
Y muestra mensaje de error
✅ CA-02: Validación de la descripción

Escenario: CA-02-A - Descripción válida

Dado que el nombre es válido
Cuando ingresa una descripción ≤ 100 palabras
Entonces el sistema registra la tarea

Escenario: CA-02-B - Exceso de palabras

Dado que el nombre es válido
Cuando la descripción supera 100 palabras
Entonces el sistema rechaza
Y muestra mensaje de error

Escenario: CA-02-C - Descripción vacía

Dado que el nombre es válido
Cuando no ingresa descripción
Entonces el sistema permite crear la tarea
✅ CA-03: Fecha límite

Escenario: CA-03-A - Fecha válida

Dado que el administrador ingresa datos
Cuando la fecha es posterior a la actual
Entonces el sistema la valida y guarda

Escenario: CA-03-B - Fecha inválida

Dado que ingresa datos
Cuando la fecha es anterior a la actual
Entonces el sistema rechaza
Y muestra mensaje de error
✅ CA-04: Estado de la tarea

Escenario: CA-04-A - Estado por defecto

Dado que crea la tarea
Entonces el sistema asigna "Pendiente"
Y la guarda correctamente
📌 HU-12: Visualización de tareas

Como administrador del sistema,
quiero visualizar el detalle de cada tarea,
para controlar su progreso.

✅ CA-01: Visualización del detalle

Escenario: CA-01-A - Consulta exitosa

Dado que el administrador está en la lista
Cuando selecciona una tarea
Entonces el sistema muestra:
nombre
descripción
miembro asignado
fecha límite
estado
✅ CA-02: Tarea inexistente

Escenario: CA-02-A - No encontrada

Dado que busca una tarea
Cuando no existe
Entonces el sistema muestra mensaje "Tarea no encontrada"
✅ CA-03: Tarea sin miembro

Escenario: CA-03-A - Sin asignación

Dado que accede al detalle
Y no tiene miembro asignado
Entonces muestra la información
Y un mensaje indicando que no tiene integrante
✅ CA-04: Visualización del estado

Escenario: CA-04-A - Mostrar estado

Dado que visualiza la tarea
Entonces el sistema muestra el estado actual
(Pendiente, en proceso o terminada)
✅ CA-05: Miembro asignado

Escenario: CA-05-A - Mostrar responsable

Dado que la tarea tiene miembro asignado
Entonces el sistema muestra su nombre