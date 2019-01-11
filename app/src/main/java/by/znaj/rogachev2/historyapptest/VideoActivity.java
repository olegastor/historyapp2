package by.znaj.rogachev2.historyapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyA_SsxErQ96lpa6SM61llFb0ITHWhdyBbY";

    long taskId = 0;
    int id;
    String[] videoId = {"uXUOUuC5yUc",
            "uXUOUuC5yUc",
            "9IQVvkccz-c",
            "I76QX-f8_JY",
            "n85_6QYQyFI",
            "55U0sSsF30I",
            "TZyCB6RQseo",
            "oHGyyQOJzHE",
            "WDKvu7Rr_wk",
            "3HZMl1eVteo",
            "h_Htv4VxXvI"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);

        /*getActionBar().setTitle(R.string.app_name_short);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setIcon(R.mipmap.ic_launcher_new);*/

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskId = extras.getLong("id");
        }
        id = Integer.valueOf(Long.toString(taskId));

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        /** Start buffering **/
        if (!b) {
            youTubePlayer.cueVideo(videoId[id]);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }
}
