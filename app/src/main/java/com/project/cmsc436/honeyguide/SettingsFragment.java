package com.project.cmsc436.honeyguide;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.preference.PreferenceFragmentCompat;


public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        final MainActivity activity = (MainActivity) getActivity();
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.fragment_settings);

        findPreference(getString(R.string.settings_clear)).setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        activity.clearData();
                        return true;
                    }
                });

        findPreference(getString(R.string.settings_intro)).setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        PrefManager prefManager = new PrefManager(activity);
                        prefManager.setFirstTimeLaunch(true);
                        Intent newIntent = new Intent(activity, WelcomePage.class);
                        startActivity(newIntent);
                        return true;
                    }
        });

        findPreference(getString(R.string.settings_about)).setOnPreferenceClickListener(
                new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, AboutFragment.newInstance());
                        transaction.commit();
                        return true;
                    }
                });

    }
}