// Generated by view binder compiler. Do not edit!
package ru.mirea.donetskaya.mireaproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public final class FragmentHardwareBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnStart;

  @NonNull
  public final Button btnStop;

  @NonNull
  public final Button button;

  @NonNull
  public final ImageView imageViewHardware;

  @NonNull
  public final TextView textViewAudio;

  @NonNull
  public final TextView textViewAzimuth;

  @NonNull
  public final TextView textViewGyroscope;

  @NonNull
  public final TextView textViewImage;

  @NonNull
  public final TextView textViewPitch;

  @NonNull
  public final TextView textViewSensor;

  private FragmentHardwareBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnStart,
      @NonNull Button btnStop, @NonNull Button button, @NonNull ImageView imageViewHardware,
      @NonNull TextView textViewAudio, @NonNull TextView textViewAzimuth,
      @NonNull TextView textViewGyroscope, @NonNull TextView textViewImage,
      @NonNull TextView textViewPitch, @NonNull TextView textViewSensor) {
    this.rootView = rootView;
    this.btnStart = btnStart;
    this.btnStop = btnStop;
    this.button = button;
    this.imageViewHardware = imageViewHardware;
    this.textViewAudio = textViewAudio;
    this.textViewAzimuth = textViewAzimuth;
    this.textViewGyroscope = textViewGyroscope;
    this.textViewImage = textViewImage;
    this.textViewPitch = textViewPitch;
    this.textViewSensor = textViewSensor;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHardwareBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHardwareBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_hardware, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHardwareBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnStart;
      Button btnStart = ViewBindings.findChildViewById(rootView, id);
      if (btnStart == null) {
        break missingId;
      }

      id = R.id.btnStop;
      Button btnStop = ViewBindings.findChildViewById(rootView, id);
      if (btnStop == null) {
        break missingId;
      }

      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.imageViewHardware;
      ImageView imageViewHardware = ViewBindings.findChildViewById(rootView, id);
      if (imageViewHardware == null) {
        break missingId;
      }

      id = R.id.textViewAudio;
      TextView textViewAudio = ViewBindings.findChildViewById(rootView, id);
      if (textViewAudio == null) {
        break missingId;
      }

      id = R.id.textViewAzimuth;
      TextView textViewAzimuth = ViewBindings.findChildViewById(rootView, id);
      if (textViewAzimuth == null) {
        break missingId;
      }

      id = R.id.textViewGyroscope;
      TextView textViewGyroscope = ViewBindings.findChildViewById(rootView, id);
      if (textViewGyroscope == null) {
        break missingId;
      }

      id = R.id.textViewImage;
      TextView textViewImage = ViewBindings.findChildViewById(rootView, id);
      if (textViewImage == null) {
        break missingId;
      }

      id = R.id.textViewPitch;
      TextView textViewPitch = ViewBindings.findChildViewById(rootView, id);
      if (textViewPitch == null) {
        break missingId;
      }

      id = R.id.textViewSensor;
      TextView textViewSensor = ViewBindings.findChildViewById(rootView, id);
      if (textViewSensor == null) {
        break missingId;
      }

      return new FragmentHardwareBinding((ConstraintLayout) rootView, btnStart, btnStop, button,
          imageViewHardware, textViewAudio, textViewAzimuth, textViewGyroscope, textViewImage,
          textViewPitch, textViewSensor);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
