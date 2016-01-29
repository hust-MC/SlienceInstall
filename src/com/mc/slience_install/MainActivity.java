package com.mc.slience_install;

import java.io.File;
import java.lang.reflect.Method;

import com.example.slienceinstall.R;

import android.app.Activity;
import android.content.pm.IPackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;

/**
 * Android实现静默安装
 * @author 超低空
 *
 */
public class MainActivity extends Activity
{
	private File sdcardFile = Environment.getExternalStorageDirectory();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick_install(View view)
	{
		File apkFile = new File(sdcardFile, "target.apk");

		try
		{
			Class<?> clazz = Class.forName("android.os.ServiceManager");
			Method method_getService = clazz.getMethod("getService",
					String.class);
			IBinder bind = (IBinder) method_getService.invoke(null, "package");

			IPackageManager iPm = IPackageManager.Stub.asInterface(bind);
			iPm.installPackage(Uri.fromFile(apkFile), null, 2,
					apkFile.getName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
