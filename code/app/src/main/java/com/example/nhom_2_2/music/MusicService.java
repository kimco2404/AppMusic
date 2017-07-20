package com.example.nhom_2_2.music;
/**
 * Created by kimcodev on 3/7/2017.
 */
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MusicService extends Service {
	private MyBinder myBinder = new MyBinder();

	public class MyBinder extends Binder {
		public MusicService getService() {
			return MusicService.this;
		}
	}

	public void playMusic(MediaPlayer mPlayer) {

		if (mPlayer != null) {
			mPlayer.start();
		}

	}

	public void pauseMusic(MediaPlayer mPlayer) {
		if (mPlayer != null) {
			mPlayer.pause();
		}

	}

	public void stopMusic(MediaPlayer mPlayer) {
		if (mPlayer != null) {
			mPlayer.stop();
		}
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return myBinder;
	}
}
