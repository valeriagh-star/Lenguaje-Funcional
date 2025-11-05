// ===============================================
// Tema: Paso de una función como parámetro en Scala
// Editor: García Herrera Valeria
// Creado el 04 de noviembre 2025
// Descripción:Este programa demuestra cómo pasar una función como parámetro y ejecutarla repetidamente con una pausa entre ejecuciones.
// ===============================================

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FuncionalTemporizador {

  /**
   * Función que ejecuta una acción una vez por segundo, n veces.
   * Se implementa de manera recursiva (sin bucles imperativos).
   *
   * @param acción función que no recibe parámetros ni devuelve valor
   * @param n número de veces que se ejecutará la acción
   */
  
  def repetirCadaSegundo(accion: () => Unit, n: Int): Unit = {
    if (n <= 0) return
    else {
      accion() // Ejecuta la función pasada
      println(s"Ejecución número: $n")
      esperarUnSegundo()
      repetirCadaSegundo(accion, n - 1) // Llamada recursiva
    }
  }

  /**
   * Función auxiliar que pausa el programa durante 1 segundo.
   * Usa Future y Await para no bloquear de manera imperativa.
   */
  
  def esperarUnSegundo(): Unit = {
    Await.result(Future {
      Thread.sleep(1000)
    }, 2.seconds)
  }

  /**
   * Función que imprime un mensaje.
   */
  
  def mensajeTiempo(): Unit = {
    println("El tiempo vuela y nunca regresa...")
  }

  /**
   * Punto de entrada del programa.
   * Llama a repetirCadaSegundo pasando mensajeTiempo como parámetro.
   */
  
  def main(args: Array[String]): Unit = {
    println("Iniciando temporizador funcional...")
    repetirCadaSegundo(mensajeTiempo, 3)
    println("Temporizador terminado.")
  }
}

/** La salida esperada de este código sería:  
 *  Iniciando temporizador funcional...
 *  El tiempo vuela y nunca regresa...
 *  Ejecución número: 3
 *  El tiempo vuela y nunca regresa...
 *  Ejecución número: 2
 *  El tiempo vuela y nunca regresa...
 *  Ejecución número: 1
 *  Temporizador terminado. 
 *  (haciendo una pausa de un segundo entre cada ejecución)
 */

