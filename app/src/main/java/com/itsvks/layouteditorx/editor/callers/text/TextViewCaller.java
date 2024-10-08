package com.itsvks.layouteditorx.editor.callers.text;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.itsvks.layouteditorx.Constants;
import com.itsvks.layouteditorx.managers.DrawableManager;
import com.itsvks.layouteditorx.managers.ProjectManager;
import com.itsvks.layouteditorx.managers.ValuesManager;
import com.itsvks.layouteditorx.models.Project;
import com.itsvks.layouteditorx.parser.ValuesResourceParser;
import com.itsvks.layouteditorx.utils.DimensionUtil;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Dispatchers;

public class TextViewCaller {
  public static void setText(View target, String value, Context context) {
    if (value.startsWith("@string/")) {
      Project project = ProjectManager.getInstance().getOpenedProject();

      value =
          ValuesManager.INSTANCE.getValueFromResources(
              ValuesResourceParser.TAG_STRING, value, project.getStringsPath());
    }
    ((TextView) target).setText(value);
  }

  public static void setTextSize(View target, String value, Context context) {
    ((TextView) target).setTextSize(DimensionUtil.parse(value, context));
  }

  public static void setTextColor(View target, String value, Context context) {
    ((TextView) target).setTextColor(Color.parseColor(value));
  }

  public static void setGravity(View target, String value, Context context) {
    String[] flags = value.split("\\|");
    int result = 0;

    for (String flag : flags) {
      result |= Constants.gravityMap.get(flag);
    }

    ((TextView) target).setGravity(result);
  }

  public static void setCheckMark(View target, String value, Context context) {
    String name = value.replace("@drawable/", "");
    if (target instanceof CheckedTextView)
      ((CheckedTextView) target).setCheckMarkDrawable((Drawable) DrawableManager.getInstance().getDrawable(context, name, new Continuation<Drawable>() {
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

  public static void setChecked(View target, String value, Context context) {
    if (target instanceof CheckedTextView) {
      if (value.equals("true")) ((CheckedTextView) target).setChecked(true);
      else if (value.equals("false")) ((CheckedTextView) target).setChecked(false);
    }
  }

  public static void setTextStyle(View target, String value, Context context) {
    ((TextView) target).setTypeface(null, Constants.textStyleMap.get(value));
  }
}
