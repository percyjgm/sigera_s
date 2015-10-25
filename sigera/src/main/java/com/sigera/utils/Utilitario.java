package com.sigera.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

public class Utilitario {

    public static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config");
    public static final String PATTERN_LETRAS = "[A-Za-zñÑáéíóúüÁÉÍÓÚ ]+$";
    public static final String PATTERN_ALFA_NUMERICO = "[^A-Za-z0-9]";
    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATTERN_NUMEROS = "^\\d+$";
    public static final String PATTERN_NUMEROS_DECIMAL = "^\\d+(\\.\\d{1,3})?$";
    public static final String PATTERN_WORD_TXT = "(\\.txt$)|(\\.docx$)";
    public static final String PATTERN_JRE = "(jre|JRE)_[\\d|.|_]+(.zip$)";
    public static final String FORMATO_SQL_DATE = "YYYY-MM-DD HH:mm:ss";
    public static final String FORMATO_DATE_POR_DEFECTO = "dd/MM/yyyy HH:mm:ss";

    private static final Logger log = Logger.getLogger(Utilitario.class.getPackage().getName());

    public static boolean esNulo(String txt) {
        return txt == null || txt.trim().length() == 0;
    }

    public static boolean esRangoValido(String texto, int inicio, int fin) {
        if (esNulo(texto)) {
            return false;
        } else {
            if (texto.length() > fin || texto.length() < inicio) {
                return false;
            }
        }
        return true;
    }

    public static boolean esRangoValido(String texto, int fin) {
        return esRangoValido(texto, 0, fin);
    }

    public static boolean esPatternValido(String regex, String texto) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(texto).matches();
    }

    public static boolean esSoloLetras(String texto) {
        return esPatternValido(PATTERN_LETRAS, texto);
    }

    public static boolean esAlfaNumerico(String texto) {
        return esPatternValido(PATTERN_ALFA_NUMERICO, texto);
    }

    public static boolean esCorreoValido(String texto) {
        return esPatternValido(PATTERN_EMAIL, texto);
    }

    public static boolean esSoloNumero(String texto) {
        return esPatternValido(PATTERN_NUMEROS, texto);
    }

    public static boolean esSoloDecimalPositivo(String texto) {
        return esPatternValido(PATTERN_NUMEROS_DECIMAL, texto);
    }

    public static boolean esFormatoFechaValido(String date, String formatoFecha) {
        SimpleDateFormat formato = new SimpleDateFormat(formatoFecha, Locale.getDefault());
        formato.setLenient(false);
        try {
            formato.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void enviarMensajeGlobalValido(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje));
    }

    public static void enviarMensajeGlobalError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, mensaje));
    }

    public static void enviarMensajeValidoSocket(String mensaje, String CHANNEL_APP) {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL_APP, new FacesMessage(StringEscapeUtils.escapeHtml3(mensaje), StringEscapeUtils.escapeHtml3(mensaje)));
    }

    public static void putFlash(String k, Object v) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(k, v);
    }

    public static Object getFlash(String k) {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(k);
    }

    public static void TransferFile(String filename, InputStream in, String path_original) throws Exception {
        OutputStream out = new FileOutputStream(new File(path_original + filename));
        resize(in, out, 250, 190);
    }

    private static void resize(InputStream input, OutputStream output, int width, int height) throws Exception {
        BufferedImage src = ImageIO.read(input);
        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = dest.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance((double) width / src.getWidth(), (double) height / src.getHeight());
        g.drawRenderedImage(src, at);
        ImageIO.write(dest, "JPG", output);
        output.close();
    }

    public static void redireccionarJSF(FacesContext fc, String path) {
        NavigationHandler nh = fc.getApplication().getNavigationHandler();
        nh.handleNavigation(fc, null, path + "?faces-redirect=true");
    }

    public static Timestamp getFechaActual() {
        return new Timestamp(new Date().getTime());
    }

    public static String getCadenaFechaActual(String formato) {
        Timestamp stamp = new Timestamp(new Date().getTime());
        DateFormat dateFormat = new SimpleDateFormat(formato);
        String fecha = dateFormat.format(stamp);
        return fecha;
    }

    public static Date disminuirFecha(Date fechaActual, int cantidad) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, (-cantidad));
        return calendar.getTime();
    }

    public static Timestamp disminuirFecha(Timestamp fechaActual, int cantidad) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.DAY_OF_YEAR, (-cantidad));
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Date definirHoraMinuto(Date fechaActual, int hora, int minuto) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.set(Calendar.HOUR_OF_DAY, (hora));
        calendar.set(Calendar.MINUTE, (minuto));
        return calendar.getTime();
    }

    public static String convertirFormatoFecha(Date date, String formato) {

        DateFormat dateFormat = new SimpleDateFormat(formato);
        String fecha = dateFormat.format(date);
        return fecha;
    }

    public static boolean esFechaMayor(Date min, Date max) {

        return min.after(max) ? true : false;
    }

    public static File agregarLinea(File file, String linea) {
        if (file.exists()) {
            try {
                BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                writer.write(linea);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
    
    public static Date parseDate(String fecha){
        return parseDate(fecha, FORMATO_DATE_POR_DEFECTO);
    }
    
    public static Date parseDate(String fecha,String formato){
        Date date = null;
        DateFormat format = new SimpleDateFormat(formato);
        try {
            date = format.parse(fecha);
        } catch (ParseException ex) {
            log.error("[Utilitario / parseDate] error al parsear la cadena a fecha ",ex);
        }
        return date;
    }

    public static File reemplazarTextoCompleto(File file, String lineaARemplazar) {
        if (file.exists()) {
            try {
                String textoLeido = obtenerTextoFile(file);
                textoLeido = textoLeido.replaceFirst("wrapper.java.command=.*", "");
                StringBuilder builder = new StringBuilder();
                builder.append(textoLeido);
                builder.append(lineaARemplazar);
                BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String obtenerTextoFile(File file) {
        StringBuilder builder = new StringBuilder();
        if (file.exists()) {
            String mensaje = "";
            try {
                BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
                while ((mensaje = reader.readLine()) != null) {
                    builder.append(mensaje);
                    builder.append("\n");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

    public static boolean eliminarArchivo(String ruta) {
        boolean resultado = true;
        if (!Utilitario.esNulo(ruta)) {
            File file = new File(ruta);
            if (file.exists()) {
                if (!file.delete()) {
                    log.error("[Utilitario/eliminarArchivo] No se pudo eliminar el archivo " + file.getAbsolutePath());
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public static boolean eliminarArchivoEnWindows(String ruta) {
        boolean resultado = true;
        if (!Utilitario.esNulo(ruta)) {
            File file = new File(ruta);
            if (file.exists()) {
                try {
                    Process process = Runtime.getRuntime().exec(String.format("cmd /C RD /S /Q %s", ruta));
                    process.waitFor();
                    String errorOutput = IOUtils.toString(process.getErrorStream());
                    if (!Utilitario.esNulo(errorOutput)) {
                        log.error("[Utilitario / eliminarArchivoEnWindows] No se pudo eliminar el archivo " + errorOutput);
                        resultado = false;
                    }
                } catch (IOException ex) {
                    resultado = false;
                    log.error("[Utilitario / eliminarArchivoEnWindows] No se pudo eliminar el archivo", ex);
                } catch (InterruptedException ex) {
                    resultado = false;
                    log.error("[Utilitario / eliminarArchivoEnWindows] Se ha interrumpido la ejecucion de eliminacion", ex);
                }
            }
        }
        return resultado;
    }

    public static boolean existeArchivo(String ruta) {
        File file = new File(ruta);
        return file.exists();
    }

    public static boolean cambiarNombreArchivo(File viejoArchivo, String nuevoNombre) {
        File nuevoArchivo = new File(String.format("%s\\%s", viejoArchivo.getPath().replace(viejoArchivo.getName(), ""), nuevoNombre));
        return viejoArchivo.renameTo(nuevoArchivo);
    }

    public static Object getFirstElementSet(Set<?> collection) {
        Iterator<?> iterator = collection.iterator();
        Object value = null;
        if (iterator.hasNext()) {
            value = iterator.next();
        }
        return value;
    }

    public static void exportarJSFWord(FacesContext context, String nombreArchivo, String rutaArchivo) {
        ExternalContext externalContext = context.getExternalContext();
        externalContext.setResponseContentType("application/word");
        externalContext.setResponseHeader("Expires", "0");
        externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        externalContext.setResponseHeader("Pragma", "public");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=" + nombreArchivo);
        try {
            OutputStream outputStream = externalContext.getResponseOutputStream();
            File file = new File(rutaArchivo);
            InputStream inputStream = new FileInputStream(file);
            byte buffer[] = new byte[(20 * 1024)];
            while (true) {
                int readSize = inputStream.read(buffer);
                if (readSize == -1) {
                    break;
                }
                outputStream.write(buffer, 0, readSize);
            }
            externalContext.setResponseStatus(200);
            externalContext.responseFlushBuffer();
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
