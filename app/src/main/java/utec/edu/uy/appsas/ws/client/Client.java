package utec.edu.uy.appsas.ws.client;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import utec.edu.uy.appsas.model.HTTPResponse;

public class Client {
    private final static String URL_LOGIN = "http://192.168.1.3:8081/AppWebCasoEstudioSAS_WS/sas/usuario/login";
    private final static String URL_GET_DOCENTES = "http://192.168.1.3:8081/AppWebCasoEstudioSAS_WS/sas/docente/getDocentes";
    private final static String URL_CREATE_DOCENTE = "http://192.168.1.3:8081/AppWebCasoEstudioSAS_WS/sas/docente/createDocente";

    private final static String TYPE_URLENCODED = "application/x-www-form-urlencoded";
    private final static String TYPE_JSON = "application/json";
    private final static String CHARSET = "UTF-8";
    private final static String METHOD_POST = "POST";
    private final static String METHOD_GET = "GET";

    private final static int HTTP_CODE_OK = 200;
    private final static int HTTP_CODE_CREATED = 201;
    private final static int HTTP_CODE_UNAUTHORIZED = 401;
    private final static int HTTP_CODE_SERVER_ERROR = 500;

    private final static String DEFAULT_ERROR = "Ups! Ha ocurrido un error inesperado";

    public static HTTPResponse login(String usuario, String contrasenia){
        URL url;
        HttpURLConnection urlConnection = null;
        HTTPResponse httpResponse = null;
        try {
            String urlServicio = URL_LOGIN;

            String charset = CHARSET;
            String query = String.format("usuario=%s", URLEncoder.encode(usuario, charset));
            query += String.format("&contrasenia=%s", URLEncoder.encode(contrasenia, charset));
            url = new URL(urlServicio + "?" + query);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(METHOD_POST);
            urlConnection.setRequestProperty("Content-Type", TYPE_URLENCODED);

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            int code = urlConnection.getResponseCode();
            httpResponse = new HTTPResponse();
            httpResponse.setCode(code);

            if(code == HTTP_CODE_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                httpResponse.setMessage(getResponseText(in));
            }else if(code == HTTP_CODE_UNAUTHORIZED || code == HTTP_CODE_SERVER_ERROR) {
                InputStream in = new BufferedInputStream(urlConnection.getErrorStream());
                httpResponse.setMessage(getResponseText(in));
            }else{
                httpResponse.setMessage(DEFAULT_ERROR);
            }
        } catch(Exception ex) {
            httpResponse.setMessage(DEFAULT_ERROR);
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return httpResponse;
    }

    public static HTTPResponse getDocentes(String usuario, String token){
        URL url;
        HttpURLConnection urlConnection = null;
        HTTPResponse httpResponse = null;
        try {
            String urlServicio = URL_GET_DOCENTES;

            String charset = CHARSET;
            String query = String.format("usuario=%s", URLEncoder.encode(usuario, charset));
            query += String.format("&token=%s", URLEncoder.encode(token, charset));
            url = new URL(urlServicio + "?" + query);

            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();
            httpResponse = new HTTPResponse();
            httpResponse.setCode(code);

            if(code == HTTP_CODE_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                httpResponse.setMessage(getResponseText(in));
            }else if(code == HTTP_CODE_UNAUTHORIZED || code == HTTP_CODE_SERVER_ERROR) {
                InputStream in = new BufferedInputStream(urlConnection.getErrorStream());
                httpResponse.setMessage(getResponseText(in));
            }else{
                httpResponse.setMessage(DEFAULT_ERROR);
            }

        } catch(Exception ex) {
            httpResponse.setMessage(DEFAULT_ERROR);
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return httpResponse;
    }

    public static HTTPResponse createDocente(String usuario, String token, String jsonDocente){
        URL url;
        HttpURLConnection urlConnection = null;
        HTTPResponse httpResponse = null;
        try {
            String urlServicio = URL_CREATE_DOCENTE;

            String charset = CHARSET;
            String query = String.format("usuario=%s", URLEncoder.encode(usuario, charset));
            query += String.format("&token=%s", URLEncoder.encode(token, charset));
            query += String.format("&jsonDocente=%s", URLEncoder.encode(jsonDocente, charset));
            url = new URL(urlServicio + "?" + query);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(METHOD_POST);
            urlConnection.setRequestProperty("Content-Type", TYPE_JSON);

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            int code = urlConnection.getResponseCode();
            httpResponse = new HTTPResponse();
            httpResponse.setCode(code);

            if(code == HTTP_CODE_CREATED) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                httpResponse.setMessage(getResponseText(in));
            }else if(code == HTTP_CODE_UNAUTHORIZED || code == HTTP_CODE_SERVER_ERROR) {
                InputStream in = new BufferedInputStream(urlConnection.getErrorStream());
                httpResponse.setMessage(getResponseText(in));
            }else{
                httpResponse.setMessage(DEFAULT_ERROR);
            }

        } catch(Exception ex) {
            httpResponse.setMessage(DEFAULT_ERROR);
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return httpResponse;
    }






    private static String getResponseText(InputStream inStream) {
        //http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
