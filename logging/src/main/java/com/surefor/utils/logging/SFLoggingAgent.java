package com.surefor.utils.logging;

import java.lang.instrument.Instrumentation;

public class SFLoggingAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("Starting SureFor Logging Agent..") ;
		inst.addTransformer(new LogClassTransformer()) ;
	}
}
