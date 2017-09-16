package noh.seung.hwa.weather.fragments;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import noh.seung.hwa.weather.R;
import noh.seung.hwa.weather.models.current.CurrentWeather;
import noh.seung.hwa.weather.retrofits.WeatherUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {


    @BindView(R.id.city_edit_text)
    EditText mCityEditText;
    @BindView(R.id.temp_text_view)
    TextView mTempTextView;
    @BindView(R.id.pressure_text_view)
    TextView mPressureTextView;
    @BindView(R.id.humidity_text_view)
    TextView mHumidityTextView;
    @BindView(R.id.min_temp_text_view)
    TextView mMinTempTextView;
    @BindView(R.id.max_temp_text_view)
    TextView mMaxTempTextView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.city_text_view)
    TextView mCityTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    Unbinder unbinder;

    WeatherUtil mWeatherUtil;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        unbinder = ButterKnife.bind(this, view);

        mCityEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && //요것만 하면 두번 먹는다(누를때, 뗄때)
                        keyEvent.getAction() == KeyEvent.ACTION_UP) { //뗄때
                    //검색 버튼을 뗄때 처리
                    search(mCityEditText.getText().toString());

                    //입력이 끝나면 키보드 숨기기
                    dismissKeyboard();

                    // 이벤트 소비
                    return true;
                }
                return false;
            }
        });
        // 색
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,
                Color.YELLOW,
                Color.GREEN);

        // 아래로 땡기면 콜 백
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                search(mCityEditText.getText().toString());
            }
        });
        return view;
    }

    private void dismissKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mCityEditText.getWindowToken(), 0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mWeatherUtil = new WeatherUtil();
    }

    private void search(String cityName) {
        mSwipeRefreshLayout.setRefreshing(true);
        mWeatherUtil.getApiService().getCurrentWeather(cityName).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();

                mTempTextView.setText("기온 : " + currentWeather.getMain().getTemp());
                mPressureTextView.setText("기압 : " + currentWeather.getMain().getPressure());
                mHumidityTextView.setText("습도 : " + currentWeather.getMain().getHumidity());
                mMinTempTextView.setText("최저기온 : " + currentWeather.getMain().getTempMin());
                mMaxTempTextView.setText("최고기온 : " + currentWeather.getMain().getTempMax());
                mCityTextView.setText("지역 : " + currentWeather.getName());
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
