package com.example.chessboard;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ChessBoardView chessboardView;
    private Button buttonContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chessboardView = findViewById(R.id.chessboard);
        buttonContext = findViewById(R.id.btn_context);

        registerForContextMenu(buttonContext);

        // Restore colors on rotation
        if (savedInstanceState != null) {
            int[] savedColors = savedInstanceState.getIntArray("colors");
            if (savedColors != null) chessboardView.setColors(savedColors);
        }

        buttonContext.setOnClickListener(v -> openContextMenu(v));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("colors", chessboardView.getColors());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        menu.setHeaderTitle("Wybierz opcję");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcja1) {
            chessboardView.changeColors(); // Opcja 1 = czarne → czerwone
            Toast.makeText(this, "Czarne zmieniono na czerwone", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.opcja2) {
            chessboardView.resetColors(); // Opcja 2 = reset czarno-biały
            Toast.makeText(this, "Reset planszy", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
