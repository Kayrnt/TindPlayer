package fr.kayrnt.tindplayer.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

public class TypefaceSpan extends MetricAffectingSpan
{
  private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(12);
  private Typeface mTypeface;

  public TypefaceSpan(Context paramContext, String paramString)
  {
    this.mTypeface = sTypefaceCache.get(paramString);
    if (this.mTypeface == null)
    {
      this.mTypeface = Typeface.createFromAsset(paramContext.getApplicationContext().getAssets(), String.format("fonts/%s", new Object[]{paramString}));
      sTypefaceCache.put(paramString, this.mTypeface);
    }
  }

  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setTypeface(this.mTypeface);
    paramTextPaint.setFlags(0x80 | paramTextPaint.getFlags());
  }

  public void updateMeasureState(TextPaint paramTextPaint)
  {
    paramTextPaint.setTypeface(this.mTypeface);
    paramTextPaint.setFlags(0x80 | paramTextPaint.getFlags());
  }
}
