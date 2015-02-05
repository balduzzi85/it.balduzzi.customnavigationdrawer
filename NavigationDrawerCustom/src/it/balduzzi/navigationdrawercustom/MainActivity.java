package it.balduzzi.navigationdrawercustom;

import it.balduzzi.navigationdrawercustom.fragment.Nav_DrawerFragment;



import it.balduzzi.navigationdrawercustom.interfaces.DrawerCallbacks;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements DrawerCallbacks {

	
	private Toolbar mToolbar;

    private Nav_DrawerFragment mNavigationNavDrawerFragment;

    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_main);
		
		mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                }
                return false;
            }
        });
        
        

        mNavigationNavDrawerFragment = (Nav_DrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationNavDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
		
		
		
	}
	
	  @Override
	    public void onNavigationDrawerItemSelected(int position) {
	      //  Toast.makeText(this, "item no: " + position + "-Selected", Toast.LENGTH_SHORT).show();

	    }
	
	

    @Override
    public void onBackPressed() {
        if (mNavigationNavDrawerFragment.isDrawerOpen())
            mNavigationNavDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
