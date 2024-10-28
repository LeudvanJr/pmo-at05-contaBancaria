package br.edu.fateczl.contabancaria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.contabancaria.model.ContaBancaria;
import br.edu.fateczl.contabancaria.model.ContaEspecial;
import br.edu.fateczl.contabancaria.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private EditText etNomeCliente;
    private EditText etNumConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNomeCliente = findViewById(R.id.etNomeCliente);
        etNumConta = findViewById(R.id.etNumConta);
        Button btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(e -> cadastrarCliente());
    }

    private void cadastrarCliente() {
        if(!validar())
            return;
        ContaBancaria cb = new ContaBancaria();
        cb.setCliente(etNomeCliente.getText().toString());
        cb.setNumConta(Integer.parseInt(etNumConta.getText().toString()));

        ContaEspecial ce = new ContaEspecial(cb);
        ce.setLimite(1000);
        ContaPoupanca cp = new ContaPoupanca(cb);
        cp.setDiaRendmento(15);

        Intent intent = new Intent(this, TransacaoActivity.class);
        intent.putExtra("ce",ce);
        intent.putExtra("cp",cp);
        startActivity(intent);
    }

    private boolean validar() {
        if(etNomeCliente.getText().toString().length() == 0){
            etNomeCliente.setError(getString(R.string.erro_nome));
            return false;
        }
        if(etNumConta.getText().toString().length() == 0){
            etNumConta.setError(getString(R.string.erro_numConta));
            return false;
        }
        return true;
    }
}