package com.remuladgryta.modjam;

import net.minecraftforge.event.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SpeechDetectionEvent extends Event{
	public final String detectedSpeech;
	
	public SpeechDetectionEvent(String detectedSpeech){
		this.detectedSpeech = detectedSpeech;
	}
}
