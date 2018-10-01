package pratica.banco.negocio;

public enum TipoConta {

    ESPECIAL {
        @Override
        public ContaAbstrata instanciar(String numero, double saldo, Cliente cliente) {
            ContaAbstrata conta = new ContaEspecial(numero, saldo, cliente, ESPECIAL);
            return (ContaEspecial) conta;
        }
    },
    SIMPLES {
        @Override
        public ContaAbstrata instanciar(String numero, double saldo, Cliente cliente) {
            ContaAbstrata conta = new ContaSimples(numero, saldo, cliente, SIMPLES);
            return (ContaSimples) conta;
        }
    };
    
    public abstract ContaAbstrata instanciar(String numero, double saldo, Cliente cliente);

}
