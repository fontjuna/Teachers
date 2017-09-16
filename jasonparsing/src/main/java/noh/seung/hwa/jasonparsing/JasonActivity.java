package noh.seung.hwa.jasonparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import noh.seung.hwa.jasonparsing.models.Location;
import noh.seung.hwa.jasonparsing.models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JasonActivity extends AppCompatActivity {

    @BindView(R.id.result_text)
    TextView mResultText;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private JsonSampleInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jason);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonSampleInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // 스트링을 Location객체로 변환
                .build();

        mApiService = retrofit.create(JsonSampleInterface.class);
    }

    public void requestLocation(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        mApiService.getLocation().enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) { // 성공시 분기
                mResultText.setText(response.body().toString()); //response.body에 데이타가 담김
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) { //에러발생시 분기
                mResultText.setText(t.getLocalizedMessage());         //t객체에 에러 메세지
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    public void requestWeather(View view) {
        mProgressBar.setVisibility(View.VISIBLE);

        mApiService.getWeatherList().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                mResultText.setText(response.body().toString());
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                mResultText.setText(t.getLocalizedMessage());
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
