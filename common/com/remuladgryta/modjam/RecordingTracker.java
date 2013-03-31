package com.remuladgryta.modjam;

import java.util.HashMap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RecordingTracker {
	private HashMap<String, Integer> refs;
	private static RecordingTracker instance;

	private RecordingTracker() {
		refs = new HashMap<String, Integer>();
	}

	public static RecordingTracker instance() {
		if (instance == null)
			instance = new RecordingTracker();
		return instance;
	}

	//Someone wants recording to be on, start recording if there's no other requests already.
	public int requestRecording(String modname) {
		if(refs.isEmpty())RecordingHandler.instance().startRecording();
		Integer cur = refs.get(modname);
		if (cur == null)
			cur = 0;
		cur++;
		refs.put(modname, cur);
		return cur.intValue();
	}

	//Someone wants to release one recording handler. If this is the last handler, stop recording.
	public int releaseRecordingHandler(String modname) {
		Integer cur = refs.get(modname);
		if (cur != null) {
			cur--;
			if (cur > 0) {
				refs.put(modname, cur);
			} else {
				refs.remove(modname);
				if(refs.isEmpty())RecordingHandler.instance().stopRecording();
			}
			return cur.intValue();
		} else
			throw new IllegalArgumentException("The mod " + modname + " tried to release a recording handler, but had no handlers.");
	}
	
	//Someone wants to release all their handlers. If nobody else is requesting a recording, stop recording.
	public boolean releaseAllHandlers(String modname){
		Integer num = refs.get(modname);
		refs.remove(modname);
		if(refs.isEmpty()) RecordingHandler.instance().stopRecording();
		return num != null && num.intValue() >0;
	}
}
