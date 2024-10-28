package br.edu.fateczl.contabancaria.model;

public class ContaEspecial extends ContaBancaria{
    private float limite;
    private ContaBancaria cb;

    public ContaEspecial(ContaBancaria cb){
        this.cb = cb;
    }

    @Override
    public boolean sacar(float valor) {
        if(valor > (saldo+limite))
            return false;

        saldo -= valor;
        return true;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }
}
