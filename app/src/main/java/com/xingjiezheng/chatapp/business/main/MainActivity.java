package com.xingjiezheng.chatapp.business.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.xingjiezheng.chatapp.business.contacts.ContactsFragment;
import com.xingjiezheng.chatapp.business.message.list.MessageFragment;
import com.xingjiezheng.chatapp.communication.CommunicationService;
import com.xingjiezheng.chatapp.glide.GlideCircleTransform;

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

    private int position;
    private static final int POSITION_MESSAGE = 1;
    private static final int POSITION_CONTACTS = 2;
    private ContactsFragment contactsFragment;
    private MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initToolBar();
        initDrawer();
        setListener();
        refreshDrawer();
        handleFragment();
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

    private void handleFragment() {
        goToMessageFragment();
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
        goToMessageFragment();
    }

    @OnClick(R.id.txtContacts)
    void clickContactsButton() {
        goToContactsFragment();
    }

    @OnClick(R.id.imgToolBarAvatar)
    void clickToolBarAvatar() {
        if (drawer != null) {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

    private void showFragment(int position, Fragment fragment) {
        if (this.position != position) {
            this.position = position;
            replaceFragment(fragment);
        }
    }


    private void goToMessageFragment() {
        showFragment(POSITION_MESSAGE, getMessageFragment());
    }

    private void goToContactsFragment() {
        showFragment(POSITION_CONTACTS, getContactsFragment());
    }

    private Fragment getMessageFragment() {
        if (messageFragment == null) {
            messageFragment = new MessageFragment();
        }
        return messageFragment;
    }

    private Fragment getContactsFragment() {
        if (contactsFragment == null) {
            contactsFragment = new ContactsFragment();
        }
        return contactsFragment;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommunicationService.startActionClose(this);
    }
}
