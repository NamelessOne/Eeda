package ru.sigil.eeda;

import org.acra.*;
import org.acra.annotation.*;

import android.app.Application;

@ReportsCrashes(formKey = "dEV5TXVMekRXR1hxRHRCd21aeGZULVE6MQ", logcatArguments = {
		"-t", "50" })
public class MyApp extends Application {
	@Override
	public void onCreate() {
		ACRA.init(this);
		ErrorReporter.getInstance().checkReportsOnApplicationStart();
		super.onCreate();
	}
}
