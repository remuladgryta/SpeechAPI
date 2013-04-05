package com.remuladgryta.speech;

import java.util.logging.Level;

import javax.speech.recognition.GrammarException;
import javax.speech.recognition.RuleGrammar;
import javax.speech.recognition.RuleParse;

import com.sun.speech.engine.recognition.BaseRuleGrammar;

import net.minecraftforge.common.MinecraftForge;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;

public class RecognitionThread extends Thread {
	Recognizer r;
	RuleGrammar grammar;
	private boolean shouldRun = true;
	public RecognitionThread(Recognizer r,RuleGrammar rule){
		this.r = r;
		grammar = rule;
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
                String fullName = null;
                try {
					RuleParse ruleParse = grammar.parse(resultText, null);
					if(ruleParse !=null)fullName = ruleParse.getRuleName().getRuleName();
				} catch (GrammarException e) {
					e.printStackTrace();
				}
                if(!resultText.equals(""))
                	MinecraftForge.EVENT_BUS.post(new SpeechDetectionEvent(resultText,fullName));
            }
        }
		LogHelper.log(Level.FINE, "Stopping recognition");
	}
}
