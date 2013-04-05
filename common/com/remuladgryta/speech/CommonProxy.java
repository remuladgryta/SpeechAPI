package com.remuladgryta.speech;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		LogHelper.init();
	}

	public void init(FMLInitializationEvent e) {
		
	}

	public void postInit(FMLPostInitializationEvent e) {
		
	}

}
