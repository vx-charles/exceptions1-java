package model.exceptions;

import java.io.Serial;

// Exception - Compilador obriga a você a tratar o erro ao usar a class.
// RuntimeExceptions - Compilador não obriga a tratar a exceção, quando não tem try/catch no código. Isso faz o código quebrar a execução.
public class DomainException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public DomainException(String msg) {
        super(msg); // permite instanciar a mensagem personalizada e passar a mensagem para o constructor da superclass Exception.
    }
}
