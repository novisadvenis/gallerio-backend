package ch.bzzz.galleriobackend.exception;

public class StorageFileNotFoundException extends Exception{
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
