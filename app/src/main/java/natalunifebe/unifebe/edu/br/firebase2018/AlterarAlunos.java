package natalunifebe.unifebe.edu.br.firebase2018;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import natalunifebe.unifebe.edu.br.firebase2018.dao.Alunos;

public class AlterarAlunos extends Activity {
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText editTextAlterarNomeFuncionario;
    Alunos alunoOBJ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_aluno);

        if (getIntent().getExtras().get("ALUNOS")!=null){
            alunoOBJ = (Alunos)getIntent().getExtras().get("ALUNOS");
             editTextAlterarNomeFuncionario = (EditText)findViewById(R.id.editTextAlterarNomeFuncionario);
            editTextAlterarNomeFuncionario.setText(alunoOBJ.getNome());
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("alunos");

        Button alterar = findViewById(R.id.buttonAlterar);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alunoOBJ.setNome(editTextAlterarNomeFuncionario.getText().toString());

                reference.
                        child(alunoOBJ.getKey()).
                        setValue(alunoOBJ);
                finish();
            }
        });

        Button buttonExcluir =(Button)findViewById(R.id.buttonExcluir);
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.
                        child(alunoOBJ.getKey()).removeValue();
                finish();

            }
        });
    }
}
