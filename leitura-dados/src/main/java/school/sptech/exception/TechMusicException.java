package school.sptech.exception;

public class TechMusicException extends Exception {

    public TechMusicException(String mensagem) {
        super(mensagem);
    }

    public TechMusicException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
