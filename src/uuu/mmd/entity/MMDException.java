package uuu.mmd.entity;

public class MMDException extends Exception {

    public MMDException() {
    }

    //丟出錯誤訊息
    public MMDException(String msg) {
        super(msg);
    }

    //丟出錯誤訊息，且回傳底層錯誤原因
    public MMDException(String message, Throwable cause) {
        super(message, cause);
    }

}
