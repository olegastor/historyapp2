package by.znaj.rogachev2.historyapptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FillActivity extends AppCompatActivity implements View.OnTouchListener {

    private View selected_item = null;
    private int offset_x = 0;
    private int offset_y = 0;
    Boolean touchFlag = false;
    boolean dropFlag = false;
    LayoutParams imageParams;
    TextView imageDrop, image1, image2;
    int eX, eY;
    int topY, leftX, rightX, bottomY;
    String st;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fill);
        View root = findViewById(android.R.id.content).getRootView();
        imageDrop = (TextView) findViewById(R.id.ImgDrop);
        image1 = (TextView) findViewById(R.id.img);
        image2 = (TextView) findViewById(R.id.img2);
        image1.setOnTouchListener(this);
        image2.setOnTouchListener(this);
        findViewById(R.id.img3).setOnTouchListener(this);
        findViewById(R.id.img4).setOnTouchListener(this);
        findViewById(R.id.img5).setOnTouchListener(this);
        findViewById(R.id.img6).setOnTouchListener(this);
        root.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (touchFlag) {
                    System.err.println("Display If  Part ::->" + touchFlag);
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            topY = imageDrop.getTop();
                            leftX = imageDrop.getLeft();
                            rightX = imageDrop.getRight();
                            bottomY = imageDrop.getBottom();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            eX = (int) event.getX();
                            eY = (int) event.getY();
                            int x = (int) event.getX() - offset_x;
                            int y = (int) event.getY() - offset_y;
                            int w = getWindowManager().getDefaultDisplay().getWidth() - 50;
                            int h = getWindowManager().getDefaultDisplay().getHeight() - 10;
                            if (x > w) x = w;
                            if (y > h) y = h;
                            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(new ViewGroup.MarginLayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                            lp.setMargins(x, y, 0, 0);

                            if (eX > leftX && eX < rightX && eY > topY && eY < bottomY) {
                                st = String.valueOf(imageDrop.getText());
                                TextView t = (TextView) selected_item;
                                imageDrop.setText(st + t.getText());
                                selected_item.bringToFront();
                                selected_item.setVisibility(View.GONE);
                                dropFlag = true;
                            } else {
                                //imageDrop.setBackgroundColor(Color.BLUE);
                            }
                            selected_item.setLayoutParams(lp);
                            break;
                        case MotionEvent.ACTION_UP:
                            touchFlag = false;
                            if (dropFlag) {
                                dropFlag = false;
                            } else {
                                selected_item.setLayoutParams(imageParams);
                            }
                            break;
                        default:
                            break;
                    }
                }
                return true;
            }
        });
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                touchFlag = true;
                offset_x = (int) event.getX();
                offset_y = (int) event.getY();
                selected_item = v;
                imageParams = v.getLayoutParams();
                break;
            case MotionEvent.ACTION_UP:
                selected_item = null;
                touchFlag = false;
                break;
            default:
                break;
        }
        return false;
    }
}
