package com.xingjiezheng.chatapp.business.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xingjiezheng.chatapp.R;
import com.xingjiezheng.chatapp.business.Global;
import com.xingjiezheng.chatapp.glide.GlideCircleTransform;
import com.xingjiezheng.chatapp.glide.GlideRoundTransform;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.imgToolBarAvatar)
    ImageView imgToolBarAvatar;

    private TextView txtName;
    private TextView txtUserId;
    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initToolBar();
        initDrawer();
        setListener();
        refreshDrawer();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void initToolBar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void initDrawer() {
        txtName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtName);
        txtUserId = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtUserId);
        imgAvatar = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imgAvatar);

    }

    private void setListener() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void refreshDrawer() {
        txtName.setText(Global.loginAccount.getUser().getUserName());
        txtUserId.setText(Global.loginAccount.getUser().getUserId());
        Glide.with(this).load(Global.loginAccount.getUser().getAvatar()).transform(new GlideCircleTransform(this)).into(imgAvatar);
        Glide.with(this).load(Global.loginAccount.getUser().getAvatar()).transform(new GlideCircleTransform(this)).into(imgToolBarAvatar);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.txtMessage)
    void clickMessageButton() {

    }

    @OnClick(R.id.txtContacts)
    void clickContactsButton() {

    }

    @OnClick(R.id.imgToolBarAvatar)
    void clickToolBarAvatar() {
        if (drawer != null) {
            drawer.openDrawer(GravityCompat.START);
        }
    }
}
