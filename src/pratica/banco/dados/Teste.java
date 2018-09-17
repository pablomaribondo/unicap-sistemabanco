package pratica.banco.dados;

public class Teste implements Comparable<Teste>{

    public int chave;

    Teste() {
        this.chave = chave;
    }

    @Override
    public int compareTo(Teste t) {
        return Integer.compare(chave, t.chave);
    }

}
