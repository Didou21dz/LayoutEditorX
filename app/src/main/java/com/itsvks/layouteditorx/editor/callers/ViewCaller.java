package com.itsvks.layouteditorx.editor.callers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.itsvks.layouteditorx.Constants;
import com.itsvks.layouteditorx.managers.DrawableManager;
import com.itsvks.layouteditorx.managers.IdManager;
import com.itsvks.layouteditorx.utils.ArgumentUtil;
import com.itsvks.layouteditorx.utils.DimensionUtil;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;

public class ViewCaller {
  public static void setId(View target, String value, Context context) {
    IdManager.getInstance().addNewId(target, value);
  }

  public static void setLayoutWidth(View target, String value, Context context) {
    target.getLayoutParams().width = (int) DimensionUtil.parse(value, context);
    target.requestLayout();
  }

  public static void setLayoutHeight(View target, String value, Context context) {
    target.getLayoutParams().height = (int) DimensionUtil.parse(value, context);
    target.requestLayout();
  }

  public static void setBackground(View target, String value, Context context) {
    if (ArgumentUtil.parseType(value, new String[]{"color", "drawable"})
        .equals(ArgumentUtil.COLOR)) {
      target.setBackgroundColor(Color.parseColor(value));
    } else {
      String name = value.replace("@drawable/", "");
      target.setBackground((Drawable) DrawableManager.getInstance().getDrawable(context, name, new Continuation<>() {
        @NonNull
        @Override
        public CoroutineContext getContext() {
          return Dispatchers.getDefault();
        }

        @Override
        public void resumeWith(@NonNull Object o) {

        }
      }));
    }
  }

  public static void setForeground(View target, String value, Context context) {
    if (ArgumentUtil.parseType(value, new String[]{"color", "drawable"})
        .equals(ArgumentUtil.COLOR)) {
      target.setForeground(new ColorDrawable(Color.parseColor(value)));
    } else {
      String name = value.replace("@drawable/", "");
      target.setForeground((Drawable) DrawableManager.getInstance().getDrawable(context, name, new Continuation<>() {
        @NonNull
        @Override
        public CoroutineContext getContext() {
          return Dispatchers.getDefault();
        }

        @Override
        public void resumeWith(@NonNull Object o) {

        }
      }));
    }
  }

  public static void setElevation(View target, String value, Context context) {
    target.setElevation(DimensionUtil.parse(value, context));
  }

  public static void setAlpha(View target, String value, Context context) {
    target.setAlpha(Float.valueOf(value));
  }

  public static void setRotation(View target, String value, Context context) {
    target.setRotation(Float.valueOf(value));
  }

  public static void setRotationX(View target, String value, Context context) {
    target.setRotationX(Float.valueOf(value));
  }

  public static void setRotationY(View target, String value, Context context) {
    target.setRotationY(Float.valueOf(value));
  }

  public static void setTranslationX(View target, String value, Context context) {
    target.setTranslationX(DimensionUtil.parse(value, context));
  }

  public static void setTranslationY(View target, String value, Context context) {
    target.setTranslationY(DimensionUtil.parse(value, context));
  }

  public static void setTranslationZ(View target, String value, Context context) {
    target.setTranslationZ(DimensionUtil.parse(value, context));
  }

  public static void setScaleX(View target, String value, Context context) {
    target.setScaleX(Float.valueOf(value));
  }

  public static void setScaleY(View target, String value, Context context) {
    target.setScaleY(Float.valueOf(value));
  }

  public static void setPadding(View target, String value, Context context) {
    int pad = (int) DimensionUtil.parse(value, context);
    target.setPadding(pad, pad, pad, pad);
  }

  public static void setPaddingLeft(View target, String value, Context context) {
    int pad = (int) DimensionUtil.parse(value, context);
    target.setPadding(
        pad, target.getPaddingTop(), target.getPaddingRight(), target.getPaddingBottom());
  }

  public static void setPaddingRight(View target, String value, Context context) {
    int pad = (int) DimensionUtil.parse(value, context);
    target.setPadding(
        target.getPaddingLeft(), target.getPaddingTop(), pad, target.getPaddingBottom());
  }

  public static void setPaddingTop(View target, String value, Context context) {
    int pad = (int) DimensionUtil.parse(value, context);
    target.setPadding(
        target.getPaddingLeft(), pad, target.getPaddingRight(), target.getPaddingBottom());
  }

  public static void setPaddingBottom(View target, String value, Context context) {
    int pad = (int) DimensionUtil.parse(value, context);
    target.setPadding(
        target.getPaddingLeft(), target.getPaddingTop(), target.getPaddingRight(), pad);
  }

  public static void setEnabled(View target, String value, Context context) {
    if (value.equals("true")) target.setEnabled(true);
    else target.setEnabled(false);
  }

  public static void setVisibility(View target, String value, Context context) {
    target.setVisibility(Constants.visibilityMap.get(value));
  }
}
