### Objetivo de esta librería
Esta librería permite usar Remote Method Invocation (RMI) en aplicaciones y juegos que usen el patrón Modelo-Vista-Controlador (MVC) con mínimos cambios y abstrayendo el funcionamiento de RMI. De esta forma se pueden crear juegos en red o aplicaciones distribuídas de forma sencilla.

### Documentación
El JavaDoc de la librería se encuentra en la carpeta `doc` del repositorio o puede ser consultada online en <https://federicoradeljak.github.io/libreria-rmimvc/doc/index.html>.

### Cómo convertir una aplicación o juego MVC a uno distribuído usando la librería RMIMVC
Si usted ya tiene un juego o aplicación con el patrón MVC aplicado, usar la librería rmimvc para hacerlo funcionar en red y con usuarios remotos no implica grandes modificaciones. 

**Los cambios a realizar en cualquier aplicación con MVC y observer entre el modelo y el controlador son los siguientes:**
- Hacer que el **modelo herede** de `ObservableRemoto`   
Eliminar el comportamiento de gestión de observables de la clase (ya lo gestiona la librería).

- Cambiar el **método de notificación** a los observadores en uso por el método `notificarObservadores(Object arg)`  

- **Agregar throws** `RemoteException` a cada método público del modelo   
Como el modelo será accedido a través de la red las posibilidades de que falle la llamada a uno de sus métodos es mucha, si pasa, se lanzará la excepción `RemoteException`.

- **Crear interface** con los métodos públicos del modelo y **hacer que la interfaz extienda** de `IObservableRemoto`    
Para esta tarea, si estamos usando Eclipse, podemos seleccionar el código de nuestra clase y hacer click derecho, ir a *Refactor->Extract Interface...*. Si se usa IntelliJ, hacer click derecho en el nombre de la clase y elegir **

- Hacer que el **controlador implemente** `IControladorRemoto`   
Esta interface extiende de `IObservadorRemoto` y agrega un método para establecer el modelo al controlador (método `setModeloRemoto()`). 

- **Reemplazar el método de actualización/notificación** del controlador por el método `actualizar()`   

- **Crear el método** del controlador que permite setear el modelo remoto `setModeloRemoto()`    
Este método únicamente tiene que guardar la instancia del modelo remoto pasado como parámetro para usar durante la ejecución de la aplicación.

*Ejemplo:*
```
public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) {
	this.modelo = (IModelo) modeloRemoto; // es necesario castear el modelo remoto 
}
```
*NOTA: no hace falta usar el método `agregarObservador()` del modelo, la libreria agrega al controlador automáticamente 	como observador del modelo.*

- **Implementar control de errores** en cada llamada del controlador a métodos del modelo para manejar las excepciones `RemoteException`    
Todas las llamadas a métodos del modelo ahora pueden fallar por varias razones dependientes de la red. Es necesario manejar los errores que puedan generar y mostrar mensajes de error y recuperar el estado de la aplicación. 

- **Crear las clases** `Cliente` y `Servidor`    
Es necesario crear las clases que crearán y comenzarán la ejecución del servidor y de la aplicación cliente.   

*Ejemplo de clase del cliente:*
```
Controlador controlador = new Controlador();
IVista vista = new VistaGrafica(controlador);
Cliente cliente = new Cliente(ip, port, ipServidor, portServidor);
vista.iniciar(); // muestra la vista gráfica
try {
	cliente.iniciar(controlador); // enlaza el controlador con el modelo remoto 
} catch (RemoteException e) {
	// error de conexión
	e.printStackTrace();
} catch (RMIMVCException e) {
	// error al crear el objeto de acceso remoto del modelo o del controlador
	e.printStackTrace();
}
```

*Ejemplo de clase del servidor:*
```
Chat modelo = new Chat(); // modelo
Servidor servidor = new Servidor(ip, port);
try {
	servidor.iniciar(modelo);
} catch (RemoteException e) {
	// error de conexión
	e.printStackTrace();
} catch (RMIMVCException e) {
	// error al crear el objeto de acceso remoto del modelo
	e.printStackTrace();
}
```

- **Hacer serializables** todas las clases que sean pasadas como parámetro o devueltas en métodos del modelo    
Todas las clases y tipos que sean pasadas como parámetro a métodos de modelo o sean devueltas por el mismo deben implementar la interface `Serializable`, para poder ser enviados a través de la red.   
Los tipos y clases básicos de java son serializables y no es necesario hacer nada antes de usarlos.

### Código de ejemplo
Como código de ejemplo se puede ver una aplicación de chat donde cada usuario establece un seudónimo y luego puede escribir en un chat grupal.   
Éste es el repositorio con la aplicación usando el patrón MVC y Observer con la implementación que viene en Java: <https://github.com/federicoradeljak/ejemplo-chat-mvc>.   
Como ejercicio puede modificarse para usar la librería RMIMVC y debería quedar como el proyecto del siguiente repositorio que también sirve como ejemplo de la librería en funcionamiento: <https://github.com/federicoradeljak/ejemplo-chat-rmimvc>.
