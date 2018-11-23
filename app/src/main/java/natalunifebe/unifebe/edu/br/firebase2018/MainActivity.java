package natalunifebe.unifebe.edu.br.firebase2018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import natalunifebe.unifebe.edu.br.firebase2018.dao.Alunos;

public class MainActivity extends Activity {
    ListView listaAlunos;
    private ArrayList arrayListAlunos;
    private ArrayList arrayListObjAlunos;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_firebase);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("alunos");
        arrayListAlunos = new ArrayList();
        arrayListObjAlunos = new ArrayList();
        Button salvar = (Button)findViewById(R.id.button);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarFirebase();
                Toast.makeText(MainActivity.this,
                        "Botao pressionado",
                        Toast.LENGTH_SHORT).show();
            }
        });
        listaAlunos = findViewById(R.id.listFuncionarios);
        listarDadosGravados();
    }

    private void listarDadosGravados() {

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayListAlunos= new ArrayList();
                arrayListObjAlunos= new ArrayList();
                for (DataSnapshot data:
                        dataSnapshot.getChildren()){
                    Alunos alSnap  = data.getValue(Alunos.class);

                    alSnap.setKey(data.getKey());
                    Log.i("Unifebe", "Retorno = " + alSnap.toString());
                    Toast.makeText(MainActivity.this, "Retorno =  " + alSnap.toString(), Toast.LENGTH_SHORT).show();
                    arrayListAlunos.add(alSnap.getNome());
                    arrayListObjAlunos.add(alSnap);
                }
                montarAdapter(arrayListAlunos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void montarAdapter(ArrayList arr){
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arr);
        listaAlunos.setAdapter(adapter);
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent alterarAlunos = new Intent(getApplicationContext(), AlterarAlunos.class);
                Alunos alunos = (Alunos)arrayListObjAlunos.get(i);
                alterarAlunos.putExtra("ALUNOS", alunos );
                startActivity(alterarAlunos);
            }
        });
    }
    private void salvarFirebase() {


        EditText editTextNomeFuncionario =
                (EditText)findViewById(
                        R.id.editTextNomeFuncionario);
        Alunos alunos = new Alunos();
        alunos.setNome(editTextNomeFuncionario.getText().toString());
        alunos.setInstituicao("Unifebe");
        alunos.setAno("2018");
        reference.
                child(reference.push().getKey()).
                setValue(alunos);


    }
}
