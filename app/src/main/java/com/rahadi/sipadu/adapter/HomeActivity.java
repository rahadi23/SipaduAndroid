package com.rahadi.sipadu.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.rahadi.sipadu.R;
import com.rahadi.sipadu.gettersetter.BeritaOverviewGetsetter;
import com.rahadi.sipadu.gettersetter.JadwalOverviewGetsetter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    String[][] konten_jadwal = new String[][] {
            {"1", "Pemrograman Berorientasi Objek", "Takdir SST., M.T.", "244"},
            {"2", "Basis Data Lanjutan", "Abdul Ghofar S.Si, MTI.", "254"}
    };
    String[][] konten_berita = new String[][] {
            {"Kan bisa diatur di settings hehew", "29/04/2016", "Komnet Dev Team"},
            {"Kayaknya 5 item itu kebanyakan", "29/04/2016", "Komnet Dev Team"},
            {"TODO: tombol, transaksi, read", "29/04/2016", "Komnet Dev Team"},
            {"Wututututu!", "29/04/2016", "Komnet Dev Team"},
            {"Welcome to the new Sipadu!", "11/04/2016", "Komnet Dev Team"},
            {"Kuliah Pertama", "23/03/2016", "Said Mirza Pahlevi"},
            {"Her Statmat", "13/03/2016", "BAAK"}
    };

    private ListView jadwal_overview, berita_overview;
    private ArrayList<JadwalOverviewGetsetter> jadwal_overview_array;
    private ArrayList<BeritaOverviewGetsetter> berita_overview_array;
    private ObservableScrollView mScrollView;
    private int parallaxHeight;
    private DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final int tinggi = getStatusBarHeight();

        if(toolbar.getOverflowIcon() != null) {
            toolbar.getOverflowIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.colorPrimary)));
        mScrollView = (ObservableScrollView)findViewById(R.id.scroller);
        mScrollView.setScrollViewCallbacks(this);

        parallaxHeight = getResources().getDimensionPixelSize(R.dimen.parallax_item);

//        TextView nama = (TextView)findViewById(R.id.username_home);
//        TextView kelas = (TextView)findViewById(R.id.class_home);

//        actionBar.setDisplayShowCustomEnabled(true);
//        View v = getLayoutInflater().inflate(R.layout.namadannim, null);
//        actionBar.setCustomView(v);

//        if (actionBar != null) {
//            actionBar.setTitle(getResources().getString(R.string.nama_lengkap));
//        }
//        actionBar.setTitle(nama.getText().toString().toUpperCase());
//        actionBar.setSubtitle(kelas.getText().toString().toUpperCase());

//        actionBar.setDisplayShowTitleEnabled(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapser);
//        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent));
////        collapsingToolbarLayout.setTitle(getResources().getString(R.string.nama_lengkap));
//        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
//        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.START | Gravity.BOTTOM);
//        collapsingToolbarLayout.setScrimsShown(true, true);

        Button[] buttons = new Button[4];
        buttons[0] = (Button)findViewById(R.id.button_absen);
        buttons[1] = (Button)findViewById(R.id.button_nilai);
        buttons[2] = (Button)findViewById(R.id.button_dashboard);
        buttons[3] = (Button)findViewById(R.id.button_sic);

        for (Button button : buttons) {
            setButtonBackgroundColor(button);
        }

        jadwal_overview = (ListView)findViewById(R.id.list_jadwal_overview);
        jadwal_overview_array = new ArrayList<>();

        JadwalOverviewGetsetter jadwal = null;

        for (String[] aKonten_jadwal : konten_jadwal) {
            jadwal = new JadwalOverviewGetsetter();
            jadwal.setSesi(aKonten_jadwal[0]);
            jadwal.setMatkul(aKonten_jadwal[1]);
            jadwal.setDosen(aKonten_jadwal[2]);
            jadwal.setRuang(aKonten_jadwal[3]);

            jadwal_overview_array.add(jadwal);
        }

        JadwalOverview adapterJadwal = new JadwalOverview(HomeActivity.this, jadwal_overview_array);
        jadwal_overview.setAdapter(adapterJadwal);

        berita_overview = (ListView)findViewById(R.id.list_berita_overview);
        berita_overview_array = new ArrayList<>();

        BeritaOverviewGetsetter berita = null;
        int jumlah_berita_overview = 5;

        for(int i = 0; i<jumlah_berita_overview; i++) {
            berita = new BeritaOverviewGetsetter();
            berita.setInisial(konten_berita[i][0].substring(0, 1));
            berita.setNama(konten_berita[i][0]);
            berita.setTanggal(konten_berita[i][1]);
            berita.setPengirim(konten_berita[i][2]);

            berita_overview_array.add(berita);
        }

        BeritaOverview adapterBerita = new BeritaOverview(HomeActivity.this, berita_overview_array);
        berita_overview.setAdapter(adapterBerita);

        setListViewHeight(jadwal_overview);
        setListViewHeight(berita_overview);

        CircleImageView circleImageView = (CircleImageView)findViewById(R.id.detail_userpic);
        circleImageView.setImageResource(R.drawable.ic_user);

//        View linearLayout = findViewById(R.id.user_detail);
//        linearLayout.setPadding(0, tinggi, 0, 0);

//        String nama_app = getString(R.string.nama_lengkap).toUpperCase();
//        actionBar.setTitle(nama_app);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        SharedPreferences preferences = getSharedPreferences("INPUT_NIM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("INPUT_NIM", false);
        editor.apply();
        preferences = getSharedPreferences("LOGIN_FLAG", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("LOGIN_FLAG", false);
        editor.apply();
        startActivity(i);
        finish();
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter == null) {
            return;
        }
        int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(), MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for(int i = 0; i<listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if(i==0) view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void setButtonBackgroundColor(Button button) {
        button.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_OVER);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        float alpha = Math.min(1, (float) scrollY / (parallaxHeight - toolbar.getHeight()));

        float alpha2 = ScrollUtils.getFloat(scrollY / (parallaxHeight - toolbar.getHeight()), 0, 1);
        toolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, getResources().getColor(R.color.colorPrimary)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(getActionBar() != null) {
                getActionBar().setElevation(alpha2);
            }
        }

        View tinthead = findViewById(R.id.tint_head);
        tinthead.setMinimumHeight(parallaxHeight);
        tinthead.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, getResources().getColor(R.color.colorPrimary)));
        tinthead.setTranslationY(0);

        View parallaxContent = findViewById(R.id.konten_parallax);
        parallaxContent.setTranslationY(scrollY / 2);

        CircleImageView circleImageView = (CircleImageView)findViewById(R.id.detail_userpic);
        circleImageView.setAlpha(Math.max(1 - alpha * 3, 0));
        circleImageView.setTranslationY(-scrollY);

        TextView uname = (TextView) findViewById(R.id.username_home);
        TextView kelas = (TextView) findViewById(R.id.class_home);

        View konten = findViewById(R.id.konten_home);

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        konten.setMinimumHeight(displayMetrics.heightPixels-toolbar.getHeight());

        int screenWidth = displayMetrics.widthPixels;
        int unameWidth = uname.getWidth();
        int unameSpace = (screenWidth - unameWidth)/2;

        int kelasWidth = kelas.getWidth();
        int kelasSpace = (screenWidth - kelasWidth) / 2;

        float marginDefault = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);

        float maxUnameY = (float)(parallaxHeight - toolbar.getHeight() - 0.3*marginDefault);
        int adjustedUnameY = (int)ScrollUtils.getFloat(scrollY, 0, maxUnameY);
        float maxKelasY = (float)(parallaxHeight - toolbar.getHeight());
        int adjustedKelasY = (int)ScrollUtils.getFloat(scrollY, 0, maxKelasY);

        uname.setTranslationY(-adjustedUnameY);
        kelas.setTranslationY(-adjustedKelasY);

        uname.setPivotX(0);
        uname.setPivotY(0);
        kelas.setPivotX(0);
        kelas.setPivotY(0);

        int tempUnameX = (int)ScrollUtils.getFloat(scrollY-(parallaxHeight/2), 0, (unameSpace-marginDefault));
        int tempKelasX = (int)ScrollUtils.getFloat(scrollY-(parallaxHeight/2), 0, (kelasSpace-marginDefault));
        int adjustedUnameX = (int)ScrollUtils.getFloat(((unameSpace-marginDefault)/uname.getY())*tempUnameX, 0, (unameSpace-marginDefault));
        int adjustedKelasX = (int)ScrollUtils.getFloat(((kelasSpace+toolbar.getHeight()+marginDefault)/kelas.getY())*tempKelasX, 0, (kelasSpace-marginDefault));
        uname.setTranslationX(-adjustedUnameX);
        kelas.setTranslationX(-adjustedKelasX);

        float scaler =((float)parallaxHeight - (adjustedUnameY-(parallaxHeight/2)))/parallaxHeight;
        float scale = ScrollUtils.getFloat(scaler, (float)0.9, 1);
        uname.setScaleX(scale);
        uname.setScaleY(scale);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }
}
