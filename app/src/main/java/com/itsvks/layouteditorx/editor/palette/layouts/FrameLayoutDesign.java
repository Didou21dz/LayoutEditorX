package com.itsvks.layouteditorx.editor.palette.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.FrameLayout;

import com.itsvks.layouteditorx.Constants;
import com.itsvks.layouteditorx.utils.Utils;

public class FrameLayoutDesign extends FrameLayout {

  private boolean drawStrokeEnabled;
  private boolean isBlueprint;

  public FrameLayoutDesign(Context context) {
    super(context);
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
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
  public void draw(Canvas canvas) {
    if (isBlueprint) Utils.drawDashPathStroke(this, canvas, Constants.BLUEPRINT_DASH_COLOR);
    else super.draw(canvas);
  }

  public void setBlueprint(boolean isBlueprint) {
    this.isBlueprint = isBlueprint;
    invalidate();
  }
}
