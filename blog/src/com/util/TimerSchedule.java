package com.util;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class TimerSchedule {

	public int delTempPic = 1000 * 60 * 60;

	public String delPath = "";

	class TimerDelTempPic extends TimerTask {
		public void run() {
			File temFile = new File(delPath);
			File[] list = temFile.listFiles();
			long cu = System.currentTimeMillis();
			for (File l : list) {
				long time = l.lastModified();
				if ((cu - time) > delTempPic) {
					FileHandle.delPath(l);
				}
			}
		}
	}

	public void timerDel() {
		Timer timer = new Timer();
		System.out.println(TimeHandle.currentTime() + ":TimerSchedule start");
		timer.schedule(new TimerDelTempPic(), 0, delTempPic);
	}

	public int getDelTempPic() {
		return delTempPic;
	}

	public void setDelTempPic(int delTempPic) {
		this.delTempPic = delTempPic;
	}

	public String getDelPath() {
		return delPath;
	}

	public void setDelPath(String delPath) {
		this.delPath = delPath;
	}

}
