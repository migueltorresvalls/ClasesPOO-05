package domain.exceptions;

public class ObjetoNoValido extends ParametroNoValido{
    public ObjetoNoValido(String objeto){
        super("Objeto: " + objeto);
    }
    
}
