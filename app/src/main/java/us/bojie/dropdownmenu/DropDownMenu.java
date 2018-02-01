package us.bojie.dropdownmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by bojiejiang on 1/31/18.
 */

public class DropDownMenu extends LinearLayout {

    // Top
    private LinearLayout tabMenuView;
    // Container includes content, mask and pop menu
    private FrameLayout containerFrameLayout;
    private View contentView;
    private View maskView;
    private FrameLayout popupMenuViews;

    private int dividerColor = 0xFFCCCCCC;
    private int textSelectedColor = 0xff890c85;
    private int textUnselectedColor = 0xff111111;
    private int maskColor = 0x88888888;
    private int menuBackgroundColor = 0xffffffff;
    private int underlineColor = 0xffcccccc;

    private int menuTextSize = 14;
    private int menuSelectedIcon;
    private int menuUnselectedIcon;

    public DropDownMenu(Context context) {
        this(context, null);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        underlineColor = typedArray.getColor(R.styleable.DropDownMenu_underlineColor, underlineColor);
        dividerColor = typedArray.getColor(R.styleable.DropDownMenu_dividerColor, dividerColor);
        textSelectedColor = typedArray.getColor(R.styleable.DropDownMenu_textSelectedColor, textSelectedColor);
        textUnselectedColor = typedArray.getColor(R.styleable.DropDownMenu_textUnSelectedColor, textUnselectedColor);
        maskColor = typedArray.getColor(R.styleable.DropDownMenu_maskColor, maskColor);
        menuBackgroundColor = typedArray.getColor(R.styleable.DropDownMenu_menuBackgroundColor, menuBackgroundColor);
        menuTextSize = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_menuTextSize, menuTextSize);
        menuSelectedIcon = typedArray.getResourceId(R.styleable.DropDownMenu_menuSelectedIcon, menuSelectedIcon);
        menuUnselectedIcon = typedArray.getResourceId(R.styleable.DropDownMenu_menuUnselectedIcon, menuUnselectedIcon);

        typedArray.recycle();
    }
}
