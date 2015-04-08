package gibeon.app.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

public class TextViewCustom{

    public static Typeface customFonts(Activity activity,String name,TextView tv,String textResult){
    	Typeface fonts=Typeface.createFromAsset(activity.getAssets(),name);
    	tv.setText(textResult);
        tv.setTypeface(fonts);
    	return fonts;
    }

    public static Typeface customFontBold(Activity activity,TextView tv){
    	Typeface fonts=Typeface.createFromAsset(activity.getAssets(),"font/ExoBold.otf");
        tv.setTypeface(fonts);
    	return fonts;
    }
    
    public static Typeface customFontRegular(Activity activity,TextView tv){
    	Typeface fonts=Typeface.createFromAsset(activity.getAssets(),"font/ExoRegular.otf");
        tv.setTypeface(fonts);
    	return fonts;
    }
}