package us.bojie.dropdownmenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by bojiejiang on 1/31/18.
 */

public class DropDownMenu extends LinearLayout {

    public DropDownMenu(Context context) {
        this(context, null);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }
}
