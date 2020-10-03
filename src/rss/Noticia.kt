package rss

import java.io.Serializable

/**
 * Clase de Noticia
 * @property titulo String?
 * @property link String?
 * @property descripcion String?
 * @property contenido String?
 * @property fecha String?
 * @property imagen String?
 * @constructor
 */
data class Noticia(
    var titulo: String?, var link: String?, var descripcion: String?,
    var contenido: String?, var fecha: String?, var imagen: String?
) : Serializable {
    /**
     * Constructor sin parámetros
     * @constructor
     */

//    override fun toString(): String {
//        return ("Noticia{" + "titulo=" + titulo + ", link=" + link + ", descripcion=" + descripcion
//                + ", contenido=" + contenido + ", fecha=" + fecha + ", imagen=" + imagen + '}')
//    }
}