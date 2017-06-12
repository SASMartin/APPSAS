package utec.edu.uy.appsas.model;

public class HTTPResponse {
    private int code = -1;
    private String message;

    public HTTPResponse(){
        //Default Constructor
    }

    public HTTPResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
