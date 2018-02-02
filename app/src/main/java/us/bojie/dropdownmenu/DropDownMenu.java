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
import android.view.animation.AnimationUtils;
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
    private FrameLayout containerView;
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

    // Position that selected, -1 means unselected
    private int currentTabPosition = -1;

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
        containerView = new FrameLayout(context);
        containerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(containerView, 2);
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

        // Setup top tabMenuView
        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }

        // Add content view
        containerView.addView(contentView, 0);

        // MaskView
        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        maskView.setVisibility(GONE);
        containerView.addView(maskView, 1);

        // Popup menu views
        popupMenuViews = new FrameLayout(getContext());
        popupMenuViews.setVisibility(GONE);
        for (int i = 0; i < popupViews.size(); i++) {
            popupViews.get(i).setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            popupMenuViews.addView(popupViews.get(i), i);
        }
        containerView.addView(popupMenuViews, 2);
    }

    private void closeMenu() {

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

    private void switchMenu(View targetView) {
        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {
            if (targetView == tabMenuView.getChildAt(i)) {
                if (currentTabPosition == i) {
                    closeMenu();
                } else { // popup menu
                    if (currentTabPosition == -1) {
                        popupMenuViews.setVisibility(VISIBLE);
                        popupMenuViews.setAnimation(
                                AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
                        maskView.setVisibility(VISIBLE);
                        maskView.setAnimation(AnimationUtils
                                .loadAnimation(getContext(), R.anim.dd_mask_in));
                        popupMenuViews.getChildAt(i / 2).setVisibility(VISIBLE);
                    } else {
                        popupMenuViews.getChildAt(i / 2).setVisibility(VISIBLE);
                    }

                    currentTabPosition = i;
                    ((TextView) tabMenuView.getChildAt(i)).setTextColor(textSelectedColor);
                    ((TextView) tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(
                            null, null,
                            getResources().getDrawable(menuSelectedIcon),
                            null);
                }
            } else {
                ((TextView) tabMenuView.getChildAt(i)).setTextColor(textUnselectedColor);
                ((TextView) tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(
                        null, null,
                        getResources().getDrawable(menuUnselectedIcon),
                        null);
                popupMenuViews.getChildAt(i / 2).setVisibility(GONE);
            }
        }
    }
}
