/**
 * @author 
 * @date 26/11/2015
 */

/**	Camino:
	- Es la única clase que tiene main y desde la que se deben lanzar todos los hilos. 
  	- Al comenzar el camino se preguntará por teclado el número de Peregrinos que comienzan el camino 
	y se lanzará un hilo por cada uno de ellos.
	- También se creará un hilo para representar la Barca y otro para el Barquero.
	- El lago del camino sólo se puede atravesar en barca.
	- El programa informará por consola quién llega al lago, quién viaja en barca y quién llega a Santiago.
	- El programa termina cuando llegan todos los peregrinos.
 */
public class Camino {
	public static void main(String[] args) {
	}
}

/** Peregrino:
	- Cada peregrino está identificado con un número, que se utilizará para los mensajes por pantalla.
	- Desde que comienza el trayecto, tarda un tiempo aleatorio (entre 1 y 4 segundos) en atravesar el camino que le lleva hasta el Lago.
	- Si cuando llega la barca está libre, se sube en ella (informando también por consola) y comienza a pasar el lago. En caso contrario, deberá esperar.
	- Al finalizar el trayecto en barca continua su camino, tardando un tiempo aleatorio entre  0,5 - 1 segundos en llegar a Santiago.
 */
class Peregrino {
	
}


/** Barca:
	- Debe ser el objeto compartido entre todos los hilos.
	- Sólo tiene una plaza libre (además de la del propio barquero).
	- Cuando sube un peregrino en su interior, se anota su identificador.
 */
class Barca {
	
}


/** Barquero:
	- Es quien conduce la Barca y debe esperar a que haya algún peregrino para comenzar su viaje.
	- En el trayecto de ida tarda 0,5 segundos y al terminar informa por consola del peregrino 
	que ha llegado al otro lado del lago.
	- En el trayecto de vuelta tarda 0,1 segundos, ya que vuelve vacío.
 */
class Barquero {
	
}