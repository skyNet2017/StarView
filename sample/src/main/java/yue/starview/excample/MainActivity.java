package yue.starview.excample;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends BaseActivity {


    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        addFragment(new StarViewFragment(),
                new StarDrawableFragment(),
                new PathStarViewFragment());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return getFragment(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                String title = getFragment(position).getClass().getSimpleName();
                title = title.replaceAll("Fragment","");
                return title;
            }

            @Override
            public int getCount() {
                return getFragmentListSize();
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

}


}
