package nl.vogelbescherming.wadvogels.expandView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class ExpandableEditText extends EditText {

    private ViewGroup.LayoutParams mParams;
    private int mWidth = 0;
    private int currentWidth = 50;
    private float scaleX = 0.1f;
    private float speed = 1f;
    private float animTime = 250;
    private long lastResize = 0;

    public ExpandableEditText(Context context) {
        super(context);
    }

    public ExpandableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mParams = getLayoutParams();
        if(mWidth == 0) {
            mParams.width = currentWidth;
            mWidth = right - left;

            scaleX = ((float) currentWidth) / ((float) mWidth);

            if(mWidth != 0) {
                resize(0);
            }
        }
    }

    private void resize(final int time) {
        postDelayed(new Runnable() {
            boolean finished = false;

            @Override
            public void run() {
                if(((RelativeLayout)ExpandableEditText.this.getParent()).getVisibility() == View.INVISIBLE){
                    ((RelativeLayout)ExpandableEditText.this.getParent()).setVisibility(View.VISIBLE);
                }

                if(time == 0) {
                    speed = (float) mWidth / animTime;
                }
                long curr = System.currentTimeMillis();
                float delay = 0;
                if(lastResize != 0) {
                    delay = ((float) (curr - lastResize)) / 500;   //1000
                }
                lastResize = curr;
                if(scaleX < 1) {
                    scaleX += speed * delay;
                    currentWidth = (int) (mWidth * scaleX);
                    mParams.width = currentWidth;
                    ExpandableEditText.this.setLayoutParams(mParams);
                } else {
                    finished = true;
                }
                if(!finished) {
                    resize(10);
                }
            }
        }, time);
    }

}
