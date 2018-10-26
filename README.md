# Frontend para Móviles - Persistencia de datos
> En este proyecto se trabajará sobre los aspectos relacionados con la persistencia de datos (empleando preferencias, ficheros y SQLite).
> #### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> #### Asignatura: *FEM (Frontend para móviles)*

## Tecnologías necesarias
* Android
* GitHub

## Enunciado.

Para ello se recomienda comenzar con la lectura de los documentos [Settings](http://developer.android.com/intl/es/guide/topics/ui/settings.html) y [Saving Files](https://developer.android.com/training/basics/data-storage/files.html). Para trabajar sobre estos aspectos se ha desarrollado el juego conocido -entre otros nombres- como Solitario Celta, que consiste en ir saltando piezas en horizontal o en vertical con el objetivo de que finalmente quede el menor número de piezas en el tablero.

Se deberá seguir con el desarrollo de la aplicación implementando las siguientes opciones:

* Reiniciar partida: al pulsar el botón "reiniciar" se mostrará un diálogo de confirmación. En caso de respuesta afirmativa se procederá a reiniciar la partida actual.

* Guardar partida: esta opción permite guardar la situación actual del tablero. Sólo es necesario guardar una única partida y se empleará el sistema de ficheros del dispositivo.

* Recuperar partida: si existe, recupera el estado de una partida guardada (si se hubiera modificado la partida actual, se solicitará confirmación).

* Guardar puntuación: al finalizar cada partida se deberá guardar la información necesaria para generar un listado de resultados. Dicho listado deberá incluir -al menos- el nombre del jugador, el día y hora de la partida y el número de piezas que quedaron en el tablero. La información se deberá almacenar en una base de datos.

* Mejores resultados: esta opción mostrará el histórico con los mejores resultados obtenidos ordenados por número de piezas. Incluirá una opción -con confirmación- para eliminar todos los resultados guardados.


__Opcionalmente__ se propone mostrar el número de fichas que quedan en el tablero, añadir un cronómetro a la partida (y/o guardar el tiempo empleado), permitir guardar más de una partida, filtrar los mejores resultados por número de fichas o por jugador, añadir preferencias para modificar los colores empleados por la app, etc.
