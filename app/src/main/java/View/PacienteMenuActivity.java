package View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import Fragment.AgendarConsultaFragment;
import Fragment.AtualizarCadastroNutricionistaFragment;
import Fragment.HomeFragment;
import Fragment.VisualizarPlanoAlimentarFragment;

import com.example.smartnutri.R;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class PacienteMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    private static final int FRAGMENT_HOME = R.id.nav_home;
    private static final int FRAGMENT_AGENDAR_CONSULTA = R.id.nav_agendar_consulta;
    private static final int FRAGMENT_VISUALIZAR_PLANO_ALIMENTAR = R.id.nav_visualizar_plano_alimentar;
    private static final int FRAGMENT_ATUALIZAR_CADASTRO = R.id.nav_atualiza_cadastro;
    private static final int FRAGMENT_SAIR = R.id.nav_sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_menu);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        inicializarFragmentos();
        if (savedInstanceState == null) {
            trocarFragmento(FRAGMENT_HOME);
            navigationView.setCheckedItem((R.id.nav_home));
        }
    }

    private void inicializarFragmentos() {
        fragmentMap.put(FRAGMENT_HOME, new HomeFragment());
        fragmentMap.put(FRAGMENT_AGENDAR_CONSULTA, new AgendarConsultaFragment());
        fragmentMap.put(FRAGMENT_VISUALIZAR_PLANO_ALIMENTAR, new VisualizarPlanoAlimentarFragment());
        fragmentMap.put(FRAGMENT_ATUALIZAR_CADASTRO, new AtualizarCadastroNutricionistaFragment(getSupportFragmentManager()));
        // Adicione outros fragmentos ao mapa conforme necessário...
    }

    private void trocarFragmento(int fragmentId) {
        Fragment selectedFragment = fragmentMap.get(fragmentId);
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == FRAGMENT_SAIR) {
            fazerLogout();
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        } else {
            trocarFragmento(id);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
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

    private void fazerLogout() {
        // Após fazer logout, inicie a tela de login
        Intent intent = new Intent(PacienteMenuActivity.this, LoginActivity.class);
        // Adicione as flags para limpar a pilha de atividades e iniciar uma nova atividade
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finaliza a atividade atual para que o usuário não possa voltar à tela do menu após fazer logout
    }
}