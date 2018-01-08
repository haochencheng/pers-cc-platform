package pers.platform.common.utils.exception;

public class PlatformExpection extends Exception{

    public PlatformExpection() {
        super();
    }

    public PlatformExpection(String message) {
        super(message);
    }

    public PlatformExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformExpection(Throwable cause) {
        super(cause);
    }
}
