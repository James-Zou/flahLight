package com.fengyunwuji.flashword;

import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class FlashLight extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mImageViewFlashlight.setTag(false);
		Point point=new Point();
		getWindowManager().getDefaultDisplay().getSize(point);
		LayoutParams laParams=(LayoutParams) mImageViewFlashlightController.getLayoutParams();
		laParams.height=point.y*3/4;
		laParams.width=point.x/3;
		mImageViewFlashlightController.setLayoutParams(laParams);
		
	}
	public void onClick_flahlight(View view){
		if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
			Toast.makeText(this, "当前设备没有闪光灯", Toast.LENGTH_LONG).show();
			return;
		}
		if((Boolean)mImageViewFlashlight.getTag()==false){
			openFlashlight();
		}else{
			closeFlashlight();
		}
	}
	//打开闪光灯
	protected void openFlashlight(){
		TransitionDrawable drawable=(TransitionDrawable) mImageViewFlashlight.getDrawable();
		drawable.startTransition(200);
		mImageViewFlashlight.setTag(true);
		try {
			mCamera=Camera.open();
			int texturtIf=0;
			mCamera.setPreviewTexture(new SurfaceTexture(texturtIf));
			mCamera.startPreview();
			mpaParameters=mCamera.getParameters();
			mpaParameters.setFlashMode(mpaParameters.FLASH_MODE_TORCH);
			mCamera.setParameters(mpaParameters);
		} catch (Exception e) {
		}
	}
	//关闭闪关灯
	protected void closeFlashlight(){
		TransitionDrawable drawable=(TransitionDrawable) mImageViewFlashlight.getDrawable();
		if((boolean) mImageViewFlashlight.getTag()){
			drawable.reverseTransition(200);
			mImageViewFlashlight.setTag(false);
			if(mCamera!=null){
				mpaParameters=mCamera.getParameters();
				mpaParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(mpaParameters);
				mCamera.stopPreview();
				mCamera.release();
				mCamera=null;
			}
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		closeFlashlight();
	}
}
