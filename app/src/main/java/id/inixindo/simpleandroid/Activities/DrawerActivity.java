package id.inixindo.simpleandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

import id.inixindo.simpleandroid.Fragments.DashboardFragment;
import id.inixindo.simpleandroid.Fragments.ProfileFragment;
import id.inixindo.simpleandroid.Fragments.SettingsFragment;
import id.inixindo.simpleandroid.R;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Fragment fragmentDashboard;
    private Fragment fragmentProfile;
    private Fragment fragmentSettings;

    private static final int FRAGMENT_DASHBOARD = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_SETTINGS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav_drawer);

        // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        this.showFirstFragment();
    }

    private void showFirstFragment() {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null) {
            this.showFragment(FRAGMENT_DASHBOARD);
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_DASHBOARD:
                this.showFragmentDashboard();
                break;
            case FRAGMENT_PROFILE:
                this.showFragmentProfile();
                break;
            case FRAGMENT_SETTINGS:
                this.showFragmentSettings();
                break;
            default:
                break;
        }
    }

    private void showFragmentDashboard() {
        if (this.fragmentDashboard == null)
            this.fragmentDashboard = DashboardFragment.newInstance();
        this.startTransactionFragment(this.fragmentDashboard);
    }

    private void showFragmentProfile() {
        if (this.fragmentProfile == null)
            this.fragmentProfile = ProfileFragment.newInstance();
        this.startTransactionFragment(this.fragmentProfile);
    }

    private void showFragmentSettings() {
        if (this.fragmentSettings == null)
            this.fragmentSettings = SettingsFragment.newInstance();
        this.startTransactionFragment(this.fragmentSettings);
    }

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.activity_main_frame_layout, fragment
            ).commit();
        }
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.activity_main_drawer_dashboard:
                this.showFragment(FRAGMENT_DASHBOARD);
                break;
            case R.id.activity_main_drawer_profile:
                this.showFragment(FRAGMENT_PROFILE);
                break;
            case R.id.activity_main_drawer_settings:
                this.showFragment(FRAGMENT_SETTINGS);
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

}