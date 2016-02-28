package com.fengyunwuji.flashword;

import android.graphics.Color;
import android.view.View;

public class MainActivity extends ColorLight {
	public void onClick_ToFlashlight(View view) {
		hideAllUI();
		mUIFlashlight.setVisibility(View.VISIBLE);
		mLastUITye = UIType.UI_TYPE_FLASHLIGHT;
		mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
	}

	public void onClick_ToWarningLight(View view) {
		hideAllUI();
		mUIWarningLight.setVisibility(View.VISIBLE);
		mLastUITye = UIType.UI_TYPE_WARNINGLIGHT;
		mCurrentUIType = mLastUITye;
		screenBrightness(1f);
		new WarningLightThread().start();
	}

	public void onClick_ToMorse(View view) {
		hideAllUI();
		mUIMorse.setVisibility(View.VISIBLE);
		screenBrightness(1f);
		mLastUITye = UIType.UI_TYPE_BLUB;
		mCurrentUIType = mLastUITye;

	}

	public void onClick_ToBulb(View view) {
		hideAllUI();
		mUIBulb.setVisibility(View.VISIBLE);
		mHideTextViewBulb.hide();
		mHideTextViewBulb.setTextColor(Color.BLACK);
		mLastUITye = UIType.UI_TYPE_MORSE;
		mCurrentUIType = mLastUITye;

	}

	public void onClick_ToColor(View view) {
		hideAllUI();
		mUIColorLight.setVisibility(View.VISIBLE);
		screenBrightness(1f);
		mLastUITye = UIType.UI_TYPE_COLOR;
		mCurrentUIType = mLastUITye;
		mHideTextViewColorLight.setTextColor(Color.rgb(
				255 - Color.red(mCurrentColorLight),
				255 - Color.green(mCurrentColorLight),
				255 - Color.blue(mCurrentColorLight)));

	}

	public void onClick_Controller(View view) {
		hideAllUI();
		if (mCurrentUIType != UIType.UI_TYPE_MAIN) {
			mUIMain.setVisibility(View.VISIBLE);
			mCurrentUIType = UIType.UI_TYPE_MAIN;
			mWarningLightFlicker = false;
			screenBrightness(mDefaultScreenBrightness / 255);
			if (mBulbCrossFadeFlag) {
				mDrawable.reverseTransition(0);
				mBulbCrossFadeFlag = false;
			}
		} else {
			switch (mLastUITye) {
			case UI_TYPE_FLASHLIGHT:
				mUIFlashlight.setVisibility(View.VISIBLE);
				screenBrightness(1f);
				mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
				break;
			case UI_TYPE_WARNINGLIGHT:
				mUIWarningLight.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
				new WarningLightThread().start();
				break;
			case UI_TYPE_MORSE:
				mUIMorse.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_MORSE;
				break;
			case UI_TYPE_BLUB:
				mUIBulb.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_BLUB;
				break;
			case UI_TYPE_COLOR:
				mUIColorLight.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_COLOR;
				break;
			default:
				break;
			}
		}
	}

}
