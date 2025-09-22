package br.com.hc.exception;

public class HcException extends RuntimeException {
    public HcException(String mensagem) {
        super(mensagem);
    }
    public HcException(String mensagem,Throwable causa) {
        super(mensagem,causa);
    }
}
