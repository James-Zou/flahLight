package com.fengyunwuji.flashword;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WarningLight extends FlashLight{
	protected boolean mWarningLightFlicker;
	protected boolean mwarningLightState;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWarningLightFlicker=true;
		
	}
	class WarningLightThread extends Thread{
		public void run(){
			mWarningLightFlicker=true;
			while(mWarningLightFlicker){
				try {
					Thread.sleep(300);
					mWarningHandler.sendEmptyMessage(0);
				} catch (Exception e) {
				}
			}
		}
	}
	private Handler mWarningHandler=new Handler(){
		public void handleMessage(Message msg) {
			if(mwarningLightState){
				mImageViewWarninglight1.setImageResource(R.drawable.warning_light_on);
				mImageViewWarninglight2.setImageResource(R.drawable.warning_light_off);
				mwarningLightState=false;
			}else{
				mImageViewWarninglight2.setImageResource(R.drawable.warning_light_on);
				mImageViewWarninglight1.setImageResource(R.drawable.warning_light_off);
				mwarningLightState=true;
			}
		};
	};
}
