package com.remuladgryta.modjam;

import java.util.logging.Level;

import net.minecraftforge.common.MinecraftForge;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;

public class RecognitionThread extends Thread {
	Recognizer r;
	private boolean shouldRun = true;
	public RecognitionThread(Recognizer r){
		this.r = r;
	}
	
	public void cancel(){
		shouldRun = false;
	}
	@Override
	public void run(){
		LogHelper.log(Level.FINE, "Starting recognition");
		while (shouldRun) {
            Result result = r.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                if(!resultText.equals(""))
                	MinecraftForge.EVENT_BUS.post(new SpeechDetectionEvent(resultText));
            }
        }
		LogHelper.log(Level.FINE, "Stopping recognition");
	}
}
