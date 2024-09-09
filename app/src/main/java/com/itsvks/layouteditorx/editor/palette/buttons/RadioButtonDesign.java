package com.itsvks.layouteditorx.editor.palette.buttons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.RadioButton;

import androidx.annotation.NonNull;

import com.itsvks.layouteditorx.Constants;
import com.itsvks.layouteditorx.utils.Utils;

@SuppressLint("AppCompatCustomView")
public class RadioButtonDesign extends RadioButton {

  private boolean drawStrokeEnabled;
  private boolean isBlueprint;

  public RadioButtonDesign(Context context) {
    super(context);
  }

  @Override
  protected void dispatchDraw(@NonNull Canvas canvas) {
    super.dispatchDraw(canvas);

    if (drawStrokeEnabled)
      Utils.drawDashPathStroke(
          this, canvas, isBlueprint ? Constants.BLUEPRINT_DASH_COLOR : Constants.DESIGN_DASH_COLOR);
  }

  public void setStrokeEnabled(boolean enabled) {
    drawStrokeEnabled = enabled;
    invalidate();
  }

  @Override
  public void draw(@NonNull Canvas canvas) {
    if (isBlueprint) Utils.drawDashPathStroke(this, canvas, Constants.BLUEPRINT_DASH_COLOR);
    else super.draw(canvas);
  }

  public void setBlueprint(boolean isBlueprint) {
    this.isBlueprint = isBlueprint;
    invalidate();
  }
}
