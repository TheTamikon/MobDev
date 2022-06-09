// Generated by view binder compiler. Do not edit!
package ru.mirea.donetskaya.mireaproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.mirea.donetskaya.mireaproject.R;

public final class FragmentNetworkBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button;

  @NonNull
  public final TextView textViewCity;

  @NonNull
  public final TextView textViewCountry;

  @NonNull
  public final TextView textViewIP;

  @NonNull
  public final TextView textViewLocation;

  @NonNull
  public final TextView textViewOrganisation;

  @NonNull
  public final TextView textViewPostal;

  @NonNull
  public final TextView textViewReadme;

  @NonNull
  public final TextView textViewRegion;

  @NonNull
  public final TextView textViewTimezone;

  private FragmentNetworkBinding(@NonNull ConstraintLayout rootView, @NonNull Button button,
      @NonNull TextView textViewCity, @NonNull TextView textViewCountry,
      @NonNull TextView textViewIP, @NonNull TextView textViewLocation,
      @NonNull TextView textViewOrganisation, @NonNull TextView textViewPostal,
      @NonNull TextView textViewReadme, @NonNull TextView textViewRegion,
      @NonNull TextView textViewTimezone) {
    this.rootView = rootView;
    this.button = button;
    this.textViewCity = textViewCity;
    this.textViewCountry = textViewCountry;
    this.textViewIP = textViewIP;
    this.textViewLocation = textViewLocation;
    this.textViewOrganisation = textViewOrganisation;
    this.textViewPostal = textViewPostal;
    this.textViewReadme = textViewReadme;
    this.textViewRegion = textViewRegion;
    this.textViewTimezone = textViewTimezone;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentNetworkBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentNetworkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_network, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentNetworkBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.textViewCity;
      TextView textViewCity = ViewBindings.findChildViewById(rootView, id);
      if (textViewCity == null) {
        break missingId;
      }

      id = R.id.textViewCountry;
      TextView textViewCountry = ViewBindings.findChildViewById(rootView, id);
      if (textViewCountry == null) {
        break missingId;
      }

      id = R.id.textViewIP;
      TextView textViewIP = ViewBindings.findChildViewById(rootView, id);
      if (textViewIP == null) {
        break missingId;
      }

      id = R.id.textViewLocation;
      TextView textViewLocation = ViewBindings.findChildViewById(rootView, id);
      if (textViewLocation == null) {
        break missingId;
      }

      id = R.id.textViewOrganisation;
      TextView textViewOrganisation = ViewBindings.findChildViewById(rootView, id);
      if (textViewOrganisation == null) {
        break missingId;
      }

      id = R.id.textViewPostal;
      TextView textViewPostal = ViewBindings.findChildViewById(rootView, id);
      if (textViewPostal == null) {
        break missingId;
      }

      id = R.id.textViewReadme;
      TextView textViewReadme = ViewBindings.findChildViewById(rootView, id);
      if (textViewReadme == null) {
        break missingId;
      }

      id = R.id.textViewRegion;
      TextView textViewRegion = ViewBindings.findChildViewById(rootView, id);
      if (textViewRegion == null) {
        break missingId;
      }

      id = R.id.textViewTimezone;
      TextView textViewTimezone = ViewBindings.findChildViewById(rootView, id);
      if (textViewTimezone == null) {
        break missingId;
      }

      return new FragmentNetworkBinding((ConstraintLayout) rootView, button, textViewCity,
          textViewCountry, textViewIP, textViewLocation, textViewOrganisation, textViewPostal,
          textViewReadme, textViewRegion, textViewTimezone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}