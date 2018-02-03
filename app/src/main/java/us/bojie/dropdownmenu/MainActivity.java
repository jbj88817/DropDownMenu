package us.bojie.dropdownmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DropDownMenu mDropDownMenu;
    private String headers[] = {"city", "age", "sex", "sign"};
    private List<View> popViews = new ArrayList<>();

    private String citys[] = {"Beijing", "SFO", "shanghai", "LA"};
    private String ages[] = {"18", "28", "38"};
    private String sexs[] = {"male", "female"};
    private String signs[] = {"LEO", "haha", "bet", "foo", "bar"};
    private int[] imgIds = {R.drawable.city, R.drawable.age, R.drawable.sex, R.drawable.sign};

    private int signPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDropDownMenu = findViewById(R.id.drop_down_menu);
        initViews();
    }

    private void initViews() {
        ListView lvCity = new ListView(this);
        lvCity.setDividerHeight(0);
    }
}
