package com.remuladgryta.speech;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {
	private static Logger log;
	public static void init(){
		log = Logger.getLogger("remuladgryta.speech");
		log.setParent(FMLLog.getLogger());
	}
	public static void log(Level l, String message){
		log.log(l, message);
	}
}
