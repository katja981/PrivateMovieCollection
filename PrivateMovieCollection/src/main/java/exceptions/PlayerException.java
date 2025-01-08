package exceptions;

import java.sql.SQLException;

public class PlayerException extends RuntimeException {
    public PlayerException(SQLException message) {super(message);}
    public PlayerException(String message) {super(message);}
    public PlayerException(String message, Throwable cause) {super(message, cause);}
}