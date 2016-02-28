package com.fengyunwuji.flashword;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BaseActivity extends Activity{
	protected enum UIType{
		UI_TYPE_MAIN,UI_TYPE_FLASHLIGHT,UI_TYPE_WARNINGLIGHT,UI_TYPE_MORSE,UI_TYPE_BLUB,UI_TYPE_COLOR,UI_TYPE_POLICE,UI_TYPE_SETTINGS
	}
	protected ImageView mImageViewFlashlight;
	protected ImageView mImageViewWarninglight1;
	protected ImageView mImageViewWarninglight2;
	protected ImageView mImageViewFlashlightController;
	protected Camera mCamera;
	protected Parameters mpaParameters;
	protected FrameLayout mUIFlashlight;
	protected LinearLayout mUIMain;
	protected LinearLayout mUIWarningLight;
	protected UIType mCurrentUIType=UIType.UI_TYPE_FLASHLIGHT;
	protected UIType mLastUITye=UIType.UI_TYPE_FLASHLIGHT;
	protected float mDefaultScreenBrightness;
	protected EditText mEditTextMorseCode;
	protected LinearLayout mUIMorse;
	protected ImageView mImageViewBulb;
	protected FrameLayout mUIBulb;
	protected FrameLayout mUIColorLight;
	protected int mFinishCount = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageViewFlashlight=(ImageView) findViewById(R.id.imageview_flashlight);
		mImageViewFlashlightController=(ImageView) findViewById(R.id.imageview_flashlight_controller);
		mUIFlashlight=(FrameLayout) findViewById(R.id.framelayout_flashlight);
		mUIMain=(LinearLayout) findViewById(R.id.linearlayout_main);
		mImageViewWarninglight1=(ImageView) findViewById(R.id.imageview_warning_light1);
		mImageViewWarninglight2=(ImageView) findViewById(R.id.imageview_warning_light2);
		mUIWarningLight=(LinearLayout) findViewById(R.id.linearlayout_warning_light);
		mDefaultScreenBrightness=getScreenBrightness();
		mUIMorse=(LinearLayout) findViewById(R.id.linearlayout_morse);
		mEditTextMorseCode=(EditText) findViewById(R.id.editext_morse_code);
		mUIBulb=(FrameLayout) findViewById(R.id.framelayout_bulb);
		mImageViewBulb=(ImageView) findViewById(R.id.imageview_bulb);
		mUIColorLight=(FrameLayout) findViewById(R.id.framelayout_color_light);
	}
	protected void hideAllUI(){
		mUIFlashlight.setVisibility(View.GONE);
		mUIMain.setVisibility(View.GONE);
		mUIWarningLight.setVisibility(View.GONE);
		mUIMorse.setVisibility(View.GONE);
		mUIBulb.setVisibility(View.GONE);
		mUIColorLight.setVisibility(View.GONE);
	}
	protected void screenBrightness(Float value){
		try {
			WindowManager.LayoutParams layout=getWindow().getAttributes();
			layout.screenBrightness=value;
			getWindow().setAttributes(layout);
		} catch (Exception e) {
		}
	}
	protected int getScreenBrightness(){
		int value=0;
		try {
			value=android.provider.Settings.System.getInt(getContentResolver(),Settings.System.SCREEN_BRIGHTNESS );
			
		} catch (Exception e) {
		}
		return value;
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mFinishCount = 0;
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void finish() {
		mFinishCount++;
		if (mFinishCount == 1) {
			Toast.makeText(this, "再按一次退出！", Toast.LENGTH_LONG).show();
		} else if (mFinishCount == 2) {
			super.finish();
		}
	}
}
