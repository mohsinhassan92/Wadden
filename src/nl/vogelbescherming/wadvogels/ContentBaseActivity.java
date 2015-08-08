package nl.vogelbescherming.wadvogels;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import nl.vogelbescherming.wadvogels.R;

public class ContentBaseActivity extends BaseActivity{

	private ImageView button;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContent(R.layout.content_base);
    }

	public void setButton(int drawable){
		setContentPart(R.id.ButtonContainer, R.layout.apply_button);
		setBackgroundToButton(R.id.apply_button, drawable);
	}

	public void showButton(boolean show){
		View button = findViewById(R.id.ButtonContainer);
		button.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public ImageView getButton(){
		return button;
	}

	@SuppressLint("NewApi")
	private void setBackgroundToButton(int applyButton, int drawable) {
		button = (ImageView) findViewById(applyButton);
		button.setImageResource(drawable);
	}
	
	public void setTitle(String str){
		//setHeader(R.layout.title, str);
		setContentPart(R.id.TitleContainer, R.layout.title);
        setText(R.layout.title, R.id.title_text, Typeface.NORMAL, str, false);
	}

	public void setContent(int contentLayoutId) {
		setContentPart(R.id.ContentContainer, contentLayoutId);
	}
}
