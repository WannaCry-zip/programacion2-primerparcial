package com.example.academiaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment; // Importación necesaria
import androidx.navigation.ui.AppBarConfiguration; // Importación recomendada
import androidx.navigation.ui.NavigationUI;

import com.example.academiaapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Setup Toolbar
        setSupportActionBar(binding.toolbar);

        // --- INICIO DE LA CORRECCIÓN ---

        // 1. Obtener el NavHostFragment a través del FragmentManager
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        // 2. Obtener el NavController desde el NavHostFragment
        // Esta es la forma correcta y segura de hacerlo en una Activity.
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();

            // 3. Configurar la AppBar (Toolbar) con NavController
            // Esto gestionará automáticamente el título y el botón de "hacia atrás" (Up button).
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // 4. Configurar el BottomNavigationView con NavController
            NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        }

        // --- FIN DE LA CORRECCIÓN ---

        // Ya no necesitas este listener manual, NavigationUI.setupActionBarWithNavController lo hace por ti.
        /*
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(destination.getLabel());
            }
        });
        */
    }

    /**
     * Permite que el botón "hacia atrás" (flecha en la Toolbar) funcione
     * con la navegación del NavController.
     */
    @Override
    public boolean onSupportNavigateUp() {
        // Asegúrate de que navController no sea nulo antes de usarlo.
        return (navController != null && NavigationUI.navigateUp(navController, (AppBarConfiguration) null))
                || super.onSupportNavigateUp();
    }
}
