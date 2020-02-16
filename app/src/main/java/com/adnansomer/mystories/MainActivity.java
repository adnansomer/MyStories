package com.adnansomer.mystories;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.adnansomer.mystories.StoryView.StoriesProgressView;
import com.adnansomer.mystories.WebServiceModel.Story;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivity extends AppCompatActivity implements StoriesProgressView.StoriesListener {

    RestInterface restInterface;

    private static final int PROGRESS_COUNT = 6;
    private StoriesProgressView storiesProgressView;
    private ImageView image;
    private int counter = 0;


    private int[] resources = new int[] {
            R.drawable.sample1,
            R.drawable.sample2,
            R.drawable.sample3,
            R.drawable.sample4,
            R.drawable.sample5,
            R.drawable.sample6,
    };


    private final long[] durations = new long[]{
            500L, 1000L, 1500L, 4000L, 5000L, 1000,
    };

    long pressTime = 0L;
    long limit = 500L;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    storiesProgressView.resume();
                    return limit < now - pressTime;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //RETROFIT ILE VERİLERİ ÇEKME
        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<List<Story>> call = restInterface.getStory();
/*
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {

                //resources = response.body();
                //picasso
               /* for (int i=0;i<resources.size();i++){
                    String imageLink = resources.get(i).getSrc();

                }
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.println("Error");
            }
        }); */

        storiesProgressView = findViewById(R.id.stories);
        storiesProgressView.setStoriesCount(PROGRESS_COUNT);
        storiesProgressView.setStoryDuration(3000L);

        storiesProgressView.setStoriesListener(this);

        counter = 0;
        storiesProgressView.startStories(counter);

        image = findViewById(R.id.image);
        image.setImageResource(resources[counter]); //

        // reverse view
        View reverse = findViewById(R.id.reverse);
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storiesProgressView.reverse();
            }
        });
        reverse.setOnTouchListener(onTouchListener);

        // skip view
        View skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storiesProgressView.skip();
            }
        });
        skip.setOnTouchListener(onTouchListener);
    }

    @Override
    public void onNext() {
        image.setImageResource(resources[++counter]);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < 0) return;
        image.setImageResource(resources[--counter]);
    }

    @Override
    public void onComplete() {
    }

    @Override
    protected void onDestroy() {
        storiesProgressView.destroy();
        super.onDestroy();
    }
}
