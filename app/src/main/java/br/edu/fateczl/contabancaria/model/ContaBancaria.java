package br.edu.fateczl.contabancaria.model;

import java.io.Serializable;

public class ContaBancaria implements Serializable {
    protected String cliente;
    protected int numConta;
    protected float saldo;


    public boolean sacar(float valor){
        if(valor > saldo)
            return false;

        saldo -= valor;
        return true;
    }

    public void depositar(float valor){
        saldo += valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

}
