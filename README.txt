NOTA ANEXO: A continuación se presentan las versiones que debe tener cada microservicio para que se ejecuten exitosamente, esto debido a las versiones que soportan determinadas librerias:

- Product-ServiceHexagonal (IntelliJ): Version de java 17 y SDK 17
- User-service (IntelliJ): Version de java 21 y SDK 21
- Order-service (IntelliJ): Version de java 21 y SDK 21
- Item-service (IntelliJ): Version de java 21 y SDK 21
- HyunsedaFrontEnd (netbeans): Versión java 19


1. Resumen
Este documento proporciona una visión integral de los requisitos y decisiones clave para el desarrollo del sitio web, garantizando el uso de un software eficiente y seguro. Incluye una consideración exhaustiva tanto de los aspectos funcionales como de los no funcionales del sistema, así como la integración de prototipos y pruebas de usabilidad. Además, ofrece una descripción detallada de la arquitectura del sistema, respaldada por decisiones de diseño que abordan aspectos críticos como el rendimiento, la seguridad y la facilidad de modificación. Se realiza un análisis exhaustivo de las tecnologías elegidas, como Java, SQLite, Spring Boot, Lombok, etc. fundamentando su selección en términos de eficiencia, portabilidad y seguridad.

2. Requisitos no funcionales
2.1. Escenarios de atributos de Calidad:
De acuerdo con una reunión realizada con el cliente, fue posible encontrar que para su empresa los atributos de calidad más importantes son:

- Performance (Desempeño): el cliente desea aplicar este atributo teniendo en cuenta que los productos pueden incrementar, con ello el catálogo, es necesario tener un performance que haga fácil la modificación de productos. Además, menciona que busca una optimización del programa con tiempos de carga reducidos.

- Seguridad: para el cliente es importante que cada rol tenga claro sus ta-reas (gestión de roles) donde exista una ocultación de la información para cada rol correspondiente. También en este aspecto es importante que el usuario haga una autenticación con alguno de sus datos para validar que es él.

- Modificabilidad: el programa que se cree para esta página es importante que sea fácil de mantener y de cambiar, es decir entendible para que este no sea difícil de migrar a otro ambiente.

 Escenario Performance: 
 Contexto: una microempresa que busca posicionarse en el mercado de E-Commerce ofrece una página en línea para que sus clientes conozcan sus productos desde el sistema. Esta página es utilizada por una cantidad moderada de usuarios, de forma simultánea y concurrente, donde esta se encuentra en constante modificación por la carga de imágenes, al manejar un catálogo de productos únicos.

 Estimulo: durante las modificaciones, mientras el usuario hace uso de la página, el editor puede realizar una actualización de esta sin afectar su rendimiento, con carga de imágenes, actualización de publicaciones, entre otras.

 Respuesta: la página debe ser capaz de manejar de forma eficiente esta simultaneidad proporcionando tiempos de respuestas rápidos para el usuario. Debe proporcionar una experiencia fluida al usuario notificándole cuando la carga de una imagen se haya completado.

 Medición de calidad:  los criterios para evaluar el desempeño del sistema, incluye la capacidad de manejar la carga de imágenes de gran tamaño sin degradar el rendimiento de la página y la simultaneidad al momento de usar la página y hacer modificaciones.

 Resultado esperado: se espera que la página proporcione un desempeño continuo y eficiente en todo momento, incluso durante la carga de imágenes de gran tamaño. Se debe garantizar a los usuarios poder utilizar la página de manera fluida e independiente a las modificaciones del sistema.

 Escenario Seguridad: 
 Contexto: una microempresa que busca posicionarse en el mercado de E-Commerce ofrece una página en línea para que sus clientes conozcan sus productos desde el sistema. Esta página está sujeta a modificaciones según roles específicos que actúan sobre ella. La seguridad del servicio que se ofrece es de suma importancia para proteger la integridad de esta.

 Estimulo: un usuario con el rol de super administrador intenta acceder a la plataforma de gestión de usuarios a través de una URL especifica.

 Respuesta: una vez se ingresa a determinada URL el sistema debe verificar las credenciales de registro de super administrador limitando las acciones de los usuarios dependiendo su rol y mostrando información específica de acuerdo con los permisos de su rol. 

 Medición de calidad: los criterios para evaluar la seguridad de la página, incluye la resistencia de las medidas de autenticación contra ataques de suplantación de identidad, la restricción del acceso a cualquier usuario a la gestión del sistema y responder a intentos de acceso no autorizados. 

 Resultado esperado: se espera que el sistema proporcione un alto nivel de seguridad para proteger modificaciones no autorizadas de la página por parte de usuarios con acceso injustificado. Además, se esperan medidas efectivas de autenticación y redirección de usuarios ante posibles violaciones de seguridad.

 Escenario Modificabilidad: 
 Contexto: una microempresa que busca posicionarse en el mercado de E-Commerce ofrece una página en línea para que sus clientes conozcan sus productos desde el sistema. Con el tiempo la microempresa identifica la necesidad de migrar hacia otras plataformas con la facilidad de mantener el sistema y corregir errores.

 Estimulo: la microempresa desea sacar una aplicación móvil para llegar a más usuarios. Esta migración requerirá cambios significativos en la arquitectura del sistema y la portabilidad de este.

 Respuesta: el sistema debe ser desarrollado de manera que pueda adaptarse fácilmente a los cambios requeridos para ser una aplicación móvil. El código debe estar bien estructurado y documentado, para facilitar su comprensión y modificación con una clara separación de las funciones implementadas. 

 Medición de calidad: los criterios para evaluar la modificabilidad del sistema incluyen la facilidad con la que se pueden realizar cambios en este, sin afectar sus funcionalidades, el tiempo requerido para implementar cambios, la estabilidad del sistema y la facilidad de comprender y modificar el código existente. 

 Resultado esperado: se espera que el sistema pueda adaptarse fácilmente a ser portable como aplicación móvil. Los cambios se deben implementar de manera eficiente, sin introducir errores y la arquitectura de este debe estar bien diseñada para garantizar modificaciones seguras y mantener a la microempresa competitiva en el mercado.

3. Decisiones de Arquitectura
3.1. Decisiones de Diseño:

• En el contexto de la historia de usuario agregar al carrito de compras, con el fin de resolver la interacción del usuario con el sistema de compras, nos decidimos por el microservicio para separar la logica de negocio, la presentación y la gestión de datos y descartamos la opción de hacer una implementación directa, para lograr la simplicidad del sistema cuando el usuario agrega productos al carrito y este se actualiza, aceptando que esto puede afectar el rendimiento del sistema, porque se mostraran interfaces graficas de manera simultánea cada vez que el usuario haga uso de esta funcionalidad.

• En el contexto de la historia de usuario para realizar pagos y resolver el uso de diferentes plataformas de pago, se decidió utilizar el patrón de diseño de Microservicios. Esta elección se hizo para facilitar el despliegue y la integración de varias plataformas de pago. Aunque esta opción puede afectar la escalabilidad y el rendimiento del sistema, debido a la complejidad asociada con la gestión de múltiples servicios, se considera que los beneficios de flexibilidad y adaptabilidad superan estas preocupaciones.

• En el  contexto de la historia de usuario agregar productos como administrador, con el fin de resolver el control sobre los productos de catálogo, nos decidimos por microservicio para facilitar la introducción de cambios y la simplicidad del sistema y descartamos la opción de usar un estilo de arquitectura de tuberías, para lograr la agilidad para introducir cambios y la simplicidad del sistema, aceptan-do que esto afectara la escalabilidad y la capacidad de despliegue, porque probablemente se implementarían varios cambios que pueden requerir que el código tenga que ser modificado.

• En el contexto de la historia de usuario de gestionar el envío de pro-ductos, con el fin de resolver la flexibilidad y extensibilidad, nos decidimos por usar el patrón de diseño Microservicios. Esta elección se hizo para lograr la facilidad de extender la funcionalidad del sistema de envíos, aceptando que esto puede afectar la escalabilidad del sistema y su rendimiento. Aunque se utilizarán plugins para facilitar las opciones de envío, es importante considerar que escalar la aplicación puede resultar costoso debido a la complejidad asociada con la gestión de múltiples servicios.

• En el  contexto de la historia de usuario para realizar la búsqueda de productos, con el fin de resolver la optimización de la velocidad y eficiencia, nos decidimos por usar el microservicio para optimizar la velocidad y eficiencia y descartamos la opción de usar el patrón de diseño MVC, para lograr la simplicidad del sistema al gestionar los envío, aceptando que esto afectará el rendimiento del sistema, porque puede afectar el rendimiento del sistema al mostrar interfaces gráficas de manera simultánea cada vez que el usuario haga uso de la funcionalidad de gestionar envíos, sin embargo, esta estructura es esencial para la mantenibilidad y la escalabilidad del sistema a largo plazo.

3.2. Selección Tipo de Aplicación:
En cuanto a las tecnologías seleccionadas, hemos optado por Java debido a su seguridad, fiabilidad y la capacidad de integrar diferentes estilos de arquitectura como lo son los Microservicios. Además, para el front-end hemos utilizado NetBeans y para el back-end hemos empleado IntelliJ IDEA. Estas herramientas nos han proporcionado las funcionalidades necesarias para desarrollar una aplicación robusta y fácil de mantener.

3.3. Selección Estilo Arquitectónico: 
La elección de abrazar la arquitectura de microservicios se sustenta en la imperiosa necesidad de desarrollar un sistema sorprendentemente flexible y exquisitamente escalable, facilitando el proceso de desarrollo y permitiendo una adaptación fluida al descomponer una aplicación monolítica en componentes independientes. Java se destaca por su capacidad asombrosamente eficaz para implementar y gestionar estos servicios, gracias a sus herramientas y frameworks excepcionalmente poderosos que posibilitan la creación, despliegue y administración eficaz de microservicios. Además, Java ofrece un sólido respaldo para la comunicación entre estos servicios mediante RESTful APIs, mensajería asombrosamente asíncrona o RPC, asegurando una integración fluida y una interoperabilidad excepcionalmente confiable. En síntesis, la combinación de microservicios y Java permite la construcción de un sistema sólido y formidablemente adaptable, capaz de ajustarse ágilmente a los cambios empresariales y satisfacer las exigencias de un entorno tecnológico en constante evolución, garantizando una arquitectura sorprendentemente escalable y exquisitamente flexible.

3.4. Selección de Tecnologías
En esta ocasión, se escogió H2Database como base de datos debido a su ligereza, facilidad de implementación y capacidad para garantizar la integridad de los datos. Para el desarrollo, se utilizó IntelliJ IDEA como entorno de desarrollo integrado (IDE), además, se han utilizado tecnologías complementarias como Hibernate y Lombok para simplificar la interacción con la base de datos y reducir la cantidad de código boilerplate, respectivamente, utilizando también Junit para la ejecución de pruebas. Además, se utilizó Swagger con el fin de facilitar la documentación de las API REST, asi como la autorización y autenticación con JWT y Spring Security para realizar un chequeo de identidad según quien esta tratando de ingresar al programa. Estas herramientas adicionales contribuyen a la eficiencia y calidad del desarrollo de nuestra aplicación.
