package rss

import org.w3c.dom.DOMException
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import org.xml.sax.SAXException;

object RSSController {

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
                        //System.out.println("Título: " + titulo);
                    }
                    if (n.nodeName == "link") {
                        noticia.link = n.textContent
                        //System.out.println("Enlace: " + enlace);
                    }
                    if (n.nodeName == "description") {
                        noticia.descripcion = n.textContent
                        //System.out.println("Descripción: " + descripcion);
                    }
                    if (n.nodeName == "pubDate") {
                        noticia.fecha = n.textContent
                        //System.out.println("Fecha: " + fecha);
                    }
                    if (n.nodeName == "content:encoded") {
                        noticia.contenido = n.textContent
                        //System.out.println("Contenido: " + contenido);
                    }
//                    if (n.nodeName == "enclosure") {
//                        val imagen: Node? =
//                        //Controlamos que solo rescate una imagen
//                        if (contadorImagenes == 0) {
//                            noticia.imagen = imagen
//                        }
//                        contadorImagenes++
//                    }
                    n = n.nextSibling
                }
                noticias.add(noticia)
            }
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