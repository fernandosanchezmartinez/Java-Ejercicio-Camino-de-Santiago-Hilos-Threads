import java.util.HashSet;
import java.util.Scanner;

/**
 * @author 
 * @date 26/11/2015
 */

/**
 * Camino: - Es la �nica clase que tiene main y desde la que se deben lanzar
 * todos los hilos. - Al comenzar el camino se preguntar� por teclado el n�mero
 * de Peregrinos que comienzan el camino y se lanzar� un hilo por cada uno de
 * ellos. - Tambi�n se crear� un hilo para representar la Barca y otro para el
 * Barquero. - El lago del camino s�lo se puede atravesar en barca. - El
 * programa informar� por consola qui�n llega al lago, qui�n viaja en barca y
 * qui�n llega a Santiago. - El programa termina cuando llegan todos los
 * peregrinos.
 */
public class Camino {
	static int numperegrinos;
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {

		System.out.println("                      <----------- CAMINO DE SANTIAGO -------------->");
		System.out.println("ESCRIBE EL N�MERO DE PEREGRINOS:");
		numperegrinos = in.nextInt();

		Barca barca = new Barca();

		Thread Peregrino;

		for (int i = 0; i < numperegrinos; i++) {
			Peregrino = new Peregrino(barca, i);
			System.out.println("EL PEREGRINO N�: " + i
					+ "  EST� LISTO PARA EMPEZAR A ANDAR");

			Peregrino.start();
			// Peregrino.join();

		}
		System.out.println("");

		Thread Barquero = new Barquero(barca);
		Barquero.start();

	}
}

// --------------------------------------------------------------------------------------------------------------------------------------//
// --------------------------------------------------------------------------------------------------------------------------------------//

/**
 * Peregrino: - Cada peregrino est� identificado con un n�mero, que se utilizar�
 * para los mensajes por pantalla. - Desde que comienza el trayecto, tarda un
 * tiempo aleatorio (entre 1 y 4 segundos) en atravesar el camino que le lleva
 * hasta el Lago. - Si cuando llega la barca est� libre, se sube en ella
 * (informando tambi�n por consola) y comienza a pasar el lago. En caso
 * contrario, deber� esperar. - Al finalizar el trayecto en barca continua su
 * camino, tardando un tiempo aleatorio entre 0,5 - 1 segundos en llegar a
 * Santiago.
 */
class Peregrino extends Thread {

	private Barca barca;
	private int id;

	public Peregrino(Barca barca, int id) {
		this.barca = barca;
		this.id = id;
	}

	public void run() {

		// double tiempo = (double) Math.floor(Math.random() * 4000 + 1);

		try {
			Thread.sleep(2000);
			System.out.println("EL PEREGRINO " + id + " Ha LLEGADO AL LAGO");
			barca.hayPlaza();
			// System.out.println("EL PEREGRINO " + id +
			// " ANDA HASTA SANTIAGO");
			Thread.sleep(1000);
			//System.out.println("");
			System.out.println("EL PEREGRINO " + id
					+ " HA LLEGADO A SANTIAGO ------------------------>  N�: "
					+ id);
			//System.out.println("");

		} catch (Exception e) {

		}

	}

}

// --------------------------------------------------------------------------------------------------------------------------------------//

/**
 * Barquero: - Es quien conduce la Barca y debe esperar a que haya alg�n
 * peregrino para comenzar su viaje. - En el trayecto de ida tarda 0,5 segundos
 * y al terminar informa por consola del peregrino que ha llegado al otro lado
 * del lago. - En el trayecto de vuelta tarda 0,1 segundos, ya que vuelve vac�o.
 */
class Barquero extends Thread {

	Barca barca;

	// Camino cam;

	public Barquero(Barca barca) {
		this.barca = barca;
	}

	public void run() {

		for (int i = 0; i < Camino.numperegrinos; i++) {

			try {

				sleep(500);
				barca.hacerviaje();

			} catch (Exception e) {

			}
		}
		System.out.println("");
		System.out.println("  ---> BARQUERO:  HAN NAVEGADO TODOS LOS PEREGRINOS");

	}

}

// --------------------------------------------------------------------------------------------------------------------------------------//

/**
 * Barca: - Debe ser el objeto compartido entre todos los hilos. - S�lo tiene
 * una plaza libre (adem�s de la del propio barquero). - Cuando sube un
 * peregrino en su interior, se anota su identificador.
 */
class Barca {

	private int haySitio = 0;
	HashSet<Integer> usuariosBarca;
	Peregrino pere;

	public Barca() {

		usuariosBarca = new HashSet<Integer>();
	}

	public synchronized void hacerviaje() throws InterruptedException {

		while (haySitio == 0) {
			try {
				System.out.println("BARQUERO ESPERA PEREGRINOS....");
				System.out.println("");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// usuariosBarca.add((int) pere.getId());

		System.out.println("EL BARQUERO HA COJIDO PEREGRINO");
		System.out.println("    NAVEGANDO...");
		Thread.sleep(500);
		System.out.println("    SE HA CRUZADO EL LAGO");

		System.out.println("    VOLVIENDO...");
		//System.out.println("");
		Thread.sleep(100);

		haySitio++;

		notify();

	}

	public synchronized void hayPlaza() {

		while (haySitio != 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		haySitio--;

		notify();

	}

}
