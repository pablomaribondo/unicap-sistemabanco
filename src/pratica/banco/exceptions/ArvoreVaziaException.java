package pratica.banco.exceptions;

public class ArvoreVaziaException extends Exception {

    private static final String MSG_ARVORE_VAZIA = "ÁRVORE VAZIA!";

    public ArvoreVaziaException() {
        super(MSG_ARVORE_VAZIA);
    }

}
