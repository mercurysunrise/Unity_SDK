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
	String SDKListening = "Main Camera";// SDK�ű����󶨵�GameObject
	Activity myActivity;

	// --------------------------------------------------��unity���õĲ���---------------------------------------------------------------//
	public void Login(String customParams) {
		showToast("�˴�ִ��login����,����Ϊ��" + customParams);
		onLoginSuccess();
	}

	public void Logout(String customParams) {
		showToast("�˴�ִ��logout����,����Ϊ" + customParams);
	}

	public void charge(String itemName, int unitPrice, int defaultCount,
			String callBackInfo, String callBackUrl) {
		showToast("�˴�ִ��charge����,����Ϊ��" + itemName + ",," + unitPrice + ",,"
				+ defaultCount + ",," + callBackInfo + ",," + callBackUrl);
	}

	public void pay(int total, String unitName, int count, String callBackInfo,
			String callBackUrl) {
		showToast("�˴�ִ��pay����");
	}

	public void setExtData(String id, String roleId, String roleName,
			int roleLevel, int zoneId, String zoneName, int balance, int vip,
			String partyName) {
		showToast("�˴�ִ��setExtData����,����������" + id + ",," + roleId + ",,"
				+ roleName + ",," + roleLevel + ",," + zoneId + ",," + zoneName
				+ ",," + balance + vip + ",," + partyName + "..");
	}

	public void exit() {
		runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						myActivity);
				builder.setTitle("��ʾ").setMessage("���ȷ���Ƴ�ô");
				builder.setNegativeButton("ȷ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								onExit();
							}
						});
				builder.setPositiveButton("ȡ��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ɶҲ����..O(��_��)O~~
							}
						});
				builder.show();
			}
		});
	}

	// --------------------------------------------------��unity�ص��Ĳ���---------------------------------------------------------------//
	public void onLoginSuccess() {
		JSONObject job = new JSONObject();
		try {
			job.put("username", "����");
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

	// --------------------------------------------------���ڸ���UI---------------------------------------------------------------//

	public void showToast(final String info) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(MainActivity.this, info, Toast.LENGTH_LONG)
						.show();
			}
		});
	}

	// --------------------------------------------------���ܵ��õ���������---------------------------------------------------------------//
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