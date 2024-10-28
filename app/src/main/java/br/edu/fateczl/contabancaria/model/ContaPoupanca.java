package br.edu.fateczl.contabancaria.model;

public class ContaPoupanca extends ContaBancaria{
    private int diaRendmento;
    private ContaBancaria cb;

    public ContaPoupanca(ContaBancaria cb){
        this.cb = cb;
    }

    public float calcularNovoSaldo(float taxaRendimentoPorcentagem){
        if(saldo <= 0)
            return 0;

        float saldoFuturo = saldo;
        float taxa = 1+(taxaRendimentoPorcentagem / 100);
        saldoFuturo *= taxa;
        return saldoFuturo;
    }

    public int getDiaRendmento() {
        return diaRendmento;
    }

    public void setDiaRendmento(int diaRendmento) {
        this.diaRendmento = diaRendmento;
    }
}
