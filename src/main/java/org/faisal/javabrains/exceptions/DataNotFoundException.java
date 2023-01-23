package org.faisal.javabrains.exceptions;

import java.io.Serial;

public class DataNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1811740237888375509L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
