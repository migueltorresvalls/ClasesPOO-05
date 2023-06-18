package domain.exceptions;

public class ColorNoValido extends ParametroNoValido{
    public ColorNoValido(String color){
        super("Color: " + color);
    }
}
