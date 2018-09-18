package sqlite.cursoandroid.com.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("app",MODE_PRIVATE, null);

            //tabela
            //bancoDados.execSQL("DROP TABLE pessoas");
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT,nome VARCHAR(255),idade INT(3))");



            //Inserir Dados
            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES ('Fabrício',35)");
//            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES ('Jeanny',23)");
//            bancoDados.execSQL("INSERT INTO pessoas(nome,idade) VALUES ('João',3)");
            //bancoDados.execSQL("UPDATE pessoas SET idade = 28 WHERE nome = 'Jeanny'");
            //bancoDados.execSQL("DELETE FROM pessoas");

            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas",null);

            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(cursor != null){

                Log.i("Resultado - id: ",cursor.getString(indiceColunaId));
                Log.i("Resultado - nome: ",cursor.getString(indiceColunaNome));
                Log.i("Resultado - idade: ",cursor.getString(indiceColunaIdade));
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

