package Swapnil.com.example.tossacoinapp;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        // set the title in toolbar.
        toolbar.setTitle(getTitle());
        // set color of the text or title in toolbar.
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FlipFragment(), getResources().getString(R.string.flip));
        adapter.addFragment(new HistoryFragment(),getResources().getString(R.string.history));
        viewPager.setAdapter(adapter);
    }


}
