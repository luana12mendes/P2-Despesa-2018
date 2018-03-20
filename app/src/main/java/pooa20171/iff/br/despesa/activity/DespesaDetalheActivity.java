package pooa20171.iff.br.despesa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import pooa20171.iff.br.despesa.R;
import pooa20171.iff.br.despesa.model.Despesa;

public class DespesaDetalheActivity extends AppCompatActivity {
    EditText nome, autor, descricao;
    Button btsalvar,btalterar, btdeletar;

   // final RadioButton desp = (RadioButton) findViewById(R.id.rbDesp);
   // final RadioButton rec = (RadioButton) findViewById(R.id.rbRec);

    int id;
    Despesa despesa;
    private Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_livro);
        autor = (EditText) findViewById(R.id.ed_autor_livro);
        descricao = (EditText) findViewById(R.id.ed_descricao_livro);

        btsalvar = (Button) findViewById(R.id.bt_salvar_livro);
        btalterar = (Button) findViewById(R.id.bt_alterar_livro);
        btdeletar = (Button) findViewById(R.id.bt_deletar_livro);


        Intent intent    = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();


        Button livroBT = (Button) findViewById(R.id.bt_livros);

        Button ok = (Button) findViewById(R.id.btnOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(DespesaDetalheActivity.this, LocalizaActivity.class); startActivity(intent);
            }
        });

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            despesa = realm.where(Despesa.class).equalTo("id",id).findFirst();

            nome.setText(despesa.getNome());
            autor.setText(despesa.getAutor());
            descricao.setText(despesa.getDescricao());

        }else{
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }


   /*      btsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (desp.isChecked() == true) {
                    Toast.makeText(getBaseContext(), "Receita selecionado!", Toast.LENGTH_SHORT).show();
                } else if (rec.isChecked() == true) {
                    Toast.makeText(getBaseContext(), "Despesa selecionado!", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

       btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btalterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });


    }

    public void deletar(){
        realm.beginTransaction();
        despesa.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Despesa deletado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Despesa.class).max("id") !=null)
            proximoID = realm.where(Despesa.class).max("id").intValue()+1;

        realm.beginTransaction();
        Despesa despesa = new Despesa();
        despesa.setId(proximoID);
        despesa.setNome(nome.getText().toString());
        despesa.setAutor(autor.getText().toString());
        despesa.setDescricao(descricao.getText().toString());

        realm.copyToRealm(despesa);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Cadastro OK",Toast.LENGTH_LONG).show();
        this.finish();

    }
    public void alterar() {

        realm.beginTransaction();

        despesa.setNome(nome.getText().toString());
        despesa.setAutor(autor.getText().toString());
        despesa.setDescricao(descricao.getText().toString());

        realm.copyToRealm(despesa);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Despesa Alterado",Toast.LENGTH_LONG).show();
        this.finish();

    }
}
