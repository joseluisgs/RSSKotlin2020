package rss

import org.w3c.dom.DOMException
import org.w3c.dom.Element
import org.xml.sax.SAXException
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

/**
 * Controlador de noticias. Singleton
 */
object RSSController {
    /**
     * Devuleve la lista de noticias de un RSS
     * @param uri String Dirección del RSS
     * @return MutableList<Noticia> Lista de noticias
     */
    fun getNoticias(uri: String): MutableList<Noticia> {
        // Parser
        val factory = DocumentBuilderFactory.newInstance()
        // Lista de noticias
        val noticias = mutableListOf<Noticia>()

        try {
            // Filtramos por elementos del RSS
            val builder = factory.newDocumentBuilder()
            val document = builder.parse(uri)
            val items = document.getElementsByTagName("item")

            for (i in 0 until items.length) {
                val nodo = items.item(i)
                val noticia = Noticia()
                // Vamos a contar las imagenes que hay
                var contadorImagenes = 0
                var n = nodo.firstChild
                while (n != null) {
                    if (n.nodeName == "title") {
                        noticia.titulo = n.textContent
                       // println("Título: $noticia.titulo");
                    }
                    if (n.nodeName == "link") {
                        noticia.link = n.textContent
                        // println("Enlace: $noticia.enlace");
                    }
                    if (n.nodeName == "description") {
                        noticia.descripcion = n.textContent
                        // println("Descripción: $noticia.descripcion");
                    }
                    if (n.nodeName == "pubDate") {
                        noticia.fecha = n.textContent
                        // println("Fecha: $noticia.fecha");
                    }
                    if (n.nodeName == "content:encoded") {
                        noticia.contenido = n.textContent
                        // println("Contenido: $noticia.contenido");
                    }
                    if (n.nodeName == "enclosure") {
                        val e: Element = n as Element
                        val imagen: String = e.getAttribute("url")
                        //Controlamos que solo rescate una imagen
                        if (contadorImagenes == 0) {
                            noticia.imagen = imagen
                        }
                        contadorImagenes++
                    }
                    n = n.nextSibling
                }
                noticias.add(noticia)
            }
            return noticias
        } catch (e: ParserConfigurationException) {
            println("Error: " + e.message)
        } catch (e: IOException) {
            println("Error: " + e.message)
        } catch (e: DOMException) {
            println("Error: " + e.message)
        } catch (e: SAXException) {
            println("Error: " + e.message)
        }
        return noticias
    }
}