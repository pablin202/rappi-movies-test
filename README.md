# rappi-movies-test

Esta aplicacion fue diseñada bajo el patron de diseño MVVM, tratando de cumplir con todos los requsitos necesarios para la implementacion de clean architecture, usando la inversion de dependiencias como principal actor.

La aplicación fue dividida en 4 capas: Presenter, UserCases, Data y Domain. 

## Capas de la aplicación

### Presenter

Esta capa es la que interactura con el usuario y contiene las activities, fragments, y lo relacionado al patron MVVM, ya sea la Factory y el ViewModel de cada pantalla.

### UserCases

Esta capa contiene las clases donde se detallan las acciones que se desencadenan desde el Presenter.Esta capa permite ejecutar las acciones principales del sistema por fuera del hilo principal.

### Domain

Esta capa contiene las reglas del la aplicación o negocio. Tambien el modelo de datos.

### Data

Esta capa define la fuente de datos y los repositorios. Aqui se encuentran el acceso a la API (Retrofit) de las Movies y tambien el acceso al cache (Room).



## En qué consiste el principio de responsabilidad única? Cuál es su propósito?

Consiste en que cada objeto tenga una unica responsabilidad. La protege ante gran cantidad de mofificaciones y permite su facil testeo.

## Qué características tiene, según su opinión, un “buen” código o código limpio?

Un buen codigo, o un codigo limpio se logra a partir de la division de capas, ya que cada una tiene una resposabilidad marcada, y el estar estas capas desacopladas, es facil su mentenimiento, su entendimiento y su testeo unitario.
Es un codigo limpio, mantenible y escalable.

