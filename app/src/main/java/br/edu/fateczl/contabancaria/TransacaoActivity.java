package br.edu.fateczl.contabancaria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaEspecial;
import br.edu.fateczl.contabancaria.model.ContaPoupanca;

public class TransacaoActivity extends Activity {
    private ContaEspecial ce;
    private ContaPoupanca cp;
    private TextView tvSaldo;
    private EditText etValor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.transacao), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        this.ce = (ContaEspecial) intent.getSerializableExtra("ce");
        this.cp = (ContaPoupanca) intent.getSerializableExtra("cp");

        RadioButton rdPoupanca = findViewById(R.id.rdPoupanca);
        RadioButton rdEspecial = findViewById(R.id.rdEspecial);
        RadioGroup rdGroup = findViewById(R.id.radioGroup);
        etValor = findViewById(R.id.etValor);
        tvSaldo = findViewById(R.id.tvSaldo);
        TextView tvInvestimento = findViewById(R.id.tvInvestimento);
        Button btnSacar = findViewById(R.id.btnSacar);
        Button btnDepositar = findViewById(R.id.btnDepositar);
        Button btnInvestimento = findViewById(R.id.btnInvestimento);

        rdPoupanca.setChecked(true);
        atualizarSaldo(cp);

        rdPoupanca.setOnClickListener(op -> {
                btnInvestimento.setVisibility(View.VISIBLE);
                tvInvestimento.setVisibility(View.VISIBLE);
                atualizarSaldo(cp);
        });
        rdEspecial.setOnClickListener(op -> {
                System.out.println("ESPECIALLL");
                btnInvestimento.setVisibility(View.INVISIBLE);
                tvInvestimento.setVisibility(View.INVISIBLE);
                atualizarSaldo(ce);
        });

        btnSacar.setOnClickListener(op -> {
            float valor = Float.parseFloat(etValor.getText().toString());
            if(rdPoupanca.isChecked())
                sacar(valor,cp);
            else if (rdEspecial.isChecked()) {
                sacar(valor,ce);
            }
        });

        btnDepositar.setOnClickListener(op -> {
            float valor = Float.parseFloat(etValor.getText().toString());
            if(rdPoupanca.isChecked())
                depositar(valor,cp);
            else if (rdEspecial.isChecked()) {
                depositar(valor,ce);
            }
        });

        btnInvestimento.setOnClickListener(op -> {
            float saldoFuturo = cp.calcularNovoSaldo(2.4f);
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.projecao));
            sb.append(" ");
            sb.append(getString(R.string.moeda));
            sb.append(saldoFuturo);
            tvInvestimento.setText(sb.toString());
        });

    }

    private void sacar(float valor, ContaBancaria cb) {
        cb.sacar(valor);
        atualizarSaldo(cb);
        limparCampoValor();
    }

    private void depositar(float valor, ContaBancaria cb) {
        cb.depositar(valor);
        atualizarSaldo(cb);
        limparCampoValor();
    }

    private void atualizarSaldo(ContaBancaria cb){
        tvSaldo.setText(Float.toString(cb.getSaldo()));
    }

    private void limparCampoValor(){
        etValor.setText("");
    }
}
