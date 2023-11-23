package View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import Fragment.AprovarConsultaFragment;
import Fragment.AtualizarCadastroNutricionistaFragment;
import Fragment.HomeFragment;
import Fragment.MontarPlanoAlimentarFragment;

import com.example.smartnutri.R;

public class NutricionistaMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
//    public static TelaEspera telaEspera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        telaEspera = new TelaEspera(this, "Aguarde, carregando ...");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem((R.id.nav_home));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        telaEspera.show();
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_aprova_consulta) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AprovarConsultaFragment(getSupportFragmentManager())).commit();
        } else if (id == R.id.nav_monta_plano_alimentar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MontarPlanoAlimentarFragment(getSupportFragmentManager())).commit();
        } else if (id == R.id.nav_atualiza_cadastro) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AtualizarCadastroNutricionistaFragment(getSupportFragmentManager())).commit();
        } else if (id == R.id.nav_sair) {
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
//        telaEspera.dismiss();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}