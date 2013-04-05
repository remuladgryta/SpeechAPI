package com.remuladgryta.speech;

import net.minecraftforge.event.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SpeechDetectionEvent extends Event{
	public final String detectedSpeech,ruleName;
	
	public SpeechDetectionEvent(String detectedSpeech, String matchedRule){
		this.detectedSpeech = detectedSpeech;
		ruleName = matchedRule;
	}
}
