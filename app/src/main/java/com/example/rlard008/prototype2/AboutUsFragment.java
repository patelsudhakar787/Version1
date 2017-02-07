package com.example.rlard008.prototype2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Element copyright = new Element();
        copyright.setTitle("Copyrights © 2017");

        copyright.setGravity(Gravity.CENTER);

        View aboutPage = new AboutPage(getActivity())
                .isRTL(false)
                .setImage(R.drawable.vibe_logo)
                .setDescription("About Us")
                .addItem(new Element().setTitle("Version 1.1"))
                .addGroup("Connect with us")
                .addEmail("prasanna.deshpande@rlard.com")
                .addWebsite("http://www.rlard.com/")
                .addItem(copyright)
                .create();
        return aboutPage;
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        //final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle("CopyRight");
        copyRightsElement.setIcon(R.drawable.logo_small);
      //  copyRightsElement.setColor(ContextCompat.getColor(getActivity(), mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AboutUsActivity.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }

}
