package com.sdk.unitysdk;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {
	String SDKListening = "Main Camera";// SDK脚本所绑定的GameObject
	Activity myActivity;

	// --------------------------------------------------被unity调用的部分---------------------------------------------------------------//
	public void Login(String customParams) {
		showToast("此处执行login流程,参数为：" + customParams);
		onLoginSuccess();
	}

	public void Logout(String customParams) {
		showToast("此处执行logout流程,参数为" + customParams);
	}

	public void charge(String itemName, int unitPrice, int defaultCount,
			String callBackInfo, String callBackUrl) {
		showToast("此处执行charge流程,参数为：" + itemName + ",," + unitPrice + ",,"
				+ defaultCount + ",," + callBackInfo + ",," + callBackUrl);
	}

	public void pay(int total, String unitName, int count, String callBackInfo,
			String callBackUrl) {
		showToast("此处执行pay流程");
	}

	public void setExtData(String id, String roleId, String roleName,
			int roleLevel, int zoneId, String zoneName, int balance, int vip,
			String partyName) {
		showToast("此处执行setExtData流程,参数包括：" + id + ",," + roleId + ",,"
				+ roleName + ",," + roleLevel + ",," + zoneId + ",," + zoneName
				+ ",," + balance + vip + ",," + partyName + "..");
	}

	public void exit() {
		runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						myActivity);
				builder.setTitle("提示").setMessage("真的确定推出么");
				builder.setNegativeButton("确定",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								onExit();
							}
						});
				builder.setPositiveButton("取消",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 啥也不做..O(∩_∩)O~~
							}
						});
				builder.show();
			}
		});
	}

	// --------------------------------------------------给unity回调的部分---------------------------------------------------------------//
	public void onLoginSuccess() {
		JSONObject job = new JSONObject();
		try {
			job.put("username", "张三");
			job.put("uid", "12345678");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		UnityPlayer.UnitySendMessage(this.SDKListening, "onLoginSuccess",
				job.toString());
	}

	public void onLoginFailed(String reason) {
		UnityPlayer
				.UnitySendMessage(this.SDKListening, "onLoginFailed", reason);
	}

	public void onLogout(Object customParams) {
		UnityPlayer.UnitySendMessage(this.SDKListening, "onLogout",
				(String) customParams);
	}

	 public void onExit() {
	 UnityPlayer.UnitySendMessage(this.SDKListening, "onSdkExit", "msg");
	 }

	// paySuccess payFailed ....

	// --------------------------------------------------用于更新UI---------------------------------------------------------------//

	public void showToast(final String info) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(MainActivity.this, info, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	// --------------------------------------------------可能调用的生命周期---------------------------------------------------------------//
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.myActivity = this;
	}

	public void onStop() {
		super.onStop();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	public void onRestart() {
		super.onRestart();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}