package sg.edu.rp.c346.id22026882.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText enterTask;
    Spinner addRemove;
    ListView lvTasks;
    String hintTask = "";
    String hintRemove = "";
    String addTask;
    Integer indexDel;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    String invalPos;
    String removeSuccess;
    String listEmpty;
    String clearSuccess;
    String taskShow;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterTask = findViewById(R.id.editTextTask);
        addRemove = findViewById(R.id.spinner);
        lvTasks = findViewById(R.id.listViewTasks);
        hintTask = getString(R.string.spinner_newtask);
        hintRemove = getString(R.string.spinner_removetask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        invalPos = getString(R.string.invalidPosition);
        removeSuccess = getString(R.string.successRemove);
        listEmpty = getString(R.string.emptyList);
        clearSuccess = getString(R.string.successClear);
        taskShow = getString(R.string.showTask);

        ArrayList<String> alTasks;
        alTasks = new ArrayList<String>();

        ArrayAdapter aaList = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(aaList);

        addRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        enterTask.setHint(hintTask);
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        enterTask.setHint(hintRemove);
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask = enterTask.getText().toString();
                alTasks.add(addTask);
                aaList.notifyDataSetChanged();
                enterTask.setText("");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexDel = Integer.parseInt(enterTask.getText().toString());
                if(indexDel > alTasks.size()){
                    Toast.makeText(MainActivity.this, invalPos, Toast.LENGTH_SHORT).show();
                }else{
                    alTasks.remove(indexDel-1);
                    aaList.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, removeSuccess + indexDel, Toast.LENGTH_SHORT).show();
                }
                enterTask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.size() ==0) {
                    Toast.makeText(MainActivity.this, listEmpty , Toast.LENGTH_SHORT).show();
                }else{
                    alTasks.clear();
                    aaList.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, clearSuccess, Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, taskShow + (position+1), Toast.LENGTH_SHORT).show();
            }
        });

    }
}