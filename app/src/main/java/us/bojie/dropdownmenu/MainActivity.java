package us.bojie.dropdownmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
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
    private GirdDropDownAdapter mCityAdapter;
    private ListDropDownAdapter mAgeAdapter;
    private ListDropDownAdapter mSexAdapter;
    private ConstellationAdapter mConstellationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDropDownMenu = findViewById(R.id.drop_down_menu);
        initViews();
    }

    private void initViews() {
        ListView lvCity = new ListView(this);
        mCityAdapter = new GirdDropDownAdapter(this, Arrays.asList(citys));
        lvCity.setDividerHeight(0);
        lvCity.setAdapter(mCityAdapter);

        ListView lvAge = new ListView(this);
        mAgeAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        lvAge.setDividerHeight(0);
        lvAge.setAdapter(mAgeAdapter);

        ListView lvSex = new ListView(this);
        mSexAdapter = new ListDropDownAdapter(this, Arrays.asList(sexs));
        lvSex.setDividerHeight(0);
        lvSex.setAdapter(mSexAdapter);

        View constellationView = getLayoutInflater().inflate(R.layout.layout_constellation, null);
        GridView gridView = constellationView.findViewById(R.id.constellation);
        TextView okTextView = constellationView.findViewById(R.id.ok);
        mConstellationAdapter = new ConstellationAdapter(this, Arrays.asList(signs));
        gridView.setAdapter(mConstellationAdapter);
    }
}
