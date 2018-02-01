package us.bojie.dropdownmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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

        initViews(context);
    }

    private void initViews(Context context) {
        // Top menu
        tabMenuView = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(HORIZONTAL);
        tabMenuView.setLayoutParams(layoutParams);
        addView(tabMenuView, 0);

        // UnderLine
        View underLineView = new View(context);
        underLineView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                dp2Px(1.0f)));
        underLineView.setBackgroundColor(underlineColor);
        addView(underLineView, 1);

        // Container View
        contentView = new FrameLayout(context);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(contentView, 2);
    }

    private int dp2Px(float value) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, displayMetrics);
    }

    // Initialize DropDownMenu
    public void setDropDownMenu(List<String> tabTexts, List<View> popupViews, View contentView) {
        this.contentView = contentView;
        if (tabTexts.size() != popupViews.size()) {
            throw new IllegalArgumentException(
                    "tabTexts size and popupViews size should be equal");

        }

        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }
    }

    private void addTab(List<String> tabTexts, int index) {
        final TextView tab = new TextView(getContext());
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);
        tab.setGravity(Gravity.CENTER);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        tab.setLayoutParams(new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        tab.setTextColor(textUnselectedColor);
        tab.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(menuUnselectedIcon), null);
        tab.setText(tabTexts.get(index));
        tab.setPadding(dp2Px(5), dp2Px(12), dp2Px(5), dp2Px(12));
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMenu(tab);
            }
        });
        tabMenuView.addView(tab);

        // dividerLine
        if (index < tabTexts.size() - 1) {
            View view = new View(getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(dp2Px(0.5f),
                    ViewGroup.LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(dividerColor);
            tabMenuView.addView(view);
        }
    }

    private void switchMenu(TextView tab) {

    }
}
