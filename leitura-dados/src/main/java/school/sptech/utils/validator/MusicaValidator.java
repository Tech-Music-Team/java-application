package school.sptech.utils.validator;

public class MusicaValidator {

    public static boolean validar(String trackId) {
        return trackId != null && !trackId.trim().isEmpty();
    }

    public static String validarComMensagem(String trackId) {
        if (trackId == null || trackId.trim().isEmpty()) {
            return "TrackId não pode estar vazio";
        }
        return "";
    }
}
