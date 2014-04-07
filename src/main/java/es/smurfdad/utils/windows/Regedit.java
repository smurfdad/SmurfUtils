package es.smurfdad.utils.windows;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;

/**
 * Clase estática que proporciona acceso al registro de Windows
 */
public class Regedit {

    // Constantes para la construccion de las Suerys
    private static final String REGQUERY_UTIL = "Reg Query";

    private static final String REGVALUE_OF = "/v";

    private static final String CHR_ESPACIO = " ";

    private static final String CHR_COMILLAS_DOBLES = "\"";

    /** Tipos de datos del Registro **/
    private enum TipoDato {
        REG_SZ;
    }

    /**
     * Método que realiza la consulta al registro y devuelve el resultado.
     * 
     * @param pQuery
     * @return
     * @throws IOException
     */
    private static String execute(String pQuery) throws IOException {
        // Lanzamos la consulta al registro.
        Process proceso;
        proceso = Runtime.getRuntime().exec(pQuery);
        // Recuperamos el resultado del proceso.
        int I;
        StringWriter stringWriter = new StringWriter();
        while ((I = proceso.getInputStream().read()) != -1) {
            stringWriter.write(I);
        }
        // Recuperamos la secuencia de caracteres obtenida del registro.
        StringBuffer resultado = stringWriter.getBuffer();
        // Consultamos si existe el tipo de dato de registro que nos indicara cuando comienza el valor.
        int p = resultado.indexOf(TipoDato.REG_SZ.toString());
        if (p == -1) {
            // Si no existe el tipo de datos devolvemos null
            resultado = null;
        } else {
            String valor = StringUtils.trimToNull(resultado.delete(0, p + (TipoDato.REG_SZ.toString().length()))
                    .toString());
            resultado = new StringBuffer(valor);
        }
        return resultado.toString();

    }

    /**
     * Metodo que realiza la consulta al registro indicando el path y el nombre de la clave.
     * 
     * @param pPath
     * @param pClave
     * @return
     * @throws IOException
     */
    public static String getValue(String pPath, String pClave) throws IOException {
        return execute(getQuery(pPath, pClave));

    }

    /**
     * Metodo que realiza la consulta al registro indicando el path
     * 
     * @param pPath
     * @return
     * @throws IOException
     */
    public static String getValue(String pPath) throws IOException {
        return execute(getQuery(pPath));
    }

    /**
     * Metodo que construye la query a realizar en el registro.
     * 
     * @param pPath
     * @param pClave
     * @return
     */
    private static String getQuery(String pPath, String pClave) {
        StringBuffer sb = new StringBuffer(getQuery(pPath));
        sb.append(CHR_ESPACIO);
        sb.append(REGVALUE_OF);
        sb.append(CHR_ESPACIO);
        sb.append(pClave);
        return sb.toString();
    }

    /**
     * Metodo que construye la query a realizar en el registro.
     * 
     * @param pPath
     * @return
     */
    private static String getQuery(String pPath) {
        StringBuffer sb = new StringBuffer(REGQUERY_UTIL);
        sb.append(CHR_ESPACIO);
        sb.append(CHR_COMILLAS_DOBLES);
        sb.append(pPath);
        sb.append(CHR_COMILLAS_DOBLES);
        return sb.toString();
    }

}