package domain.exceptions;

import java.io.IOException;

public abstract class ParametroNoValido extends IOException {

    public ParametroNoValido(String param){
        super("Parametro no reconocido. " + param);
    }
}
