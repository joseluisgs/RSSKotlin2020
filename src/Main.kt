import rss.Noticia
import rss.RSSController

/**
 * Clase principal
 */
fun main(){
    println("*** LECTOR DE NOTICIAS RSS ***")
    val noticias = RSSController.getNoticias("http://ep00.epimg.net/rss/tags/ultimas_noticias.xml")
    println("Ultimas noticias encontradas: ${noticias.size}")
    println()
    noticias.forEach {
        println("Noticia")
        println("--> Titular: ${it.titulo}")
        println("--> Descripcion: ${it.descripcion}")
        println("--> Texto: ${it.contenido}")
        println("--> Fecha: ${it.fecha}")
        println("--> Enlace: ${it.link}")
        println("--> Imagen: ${it.imagen}")
        println()
    }

}