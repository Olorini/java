package patterns.strategy;

import patterns.strategy.notMyLibrary.LowPass;
import patterns.strategy.notMyLibrary.Waveform;

/**
 * 
 */

public class Strategy {

	public static void process(IProcessor processor, Object s) {
		System.out.println("Используем: " + processor.name());
		System.out.println(processor.process(s));
	}

	public static void main(String[] args) {
		String s = "Съешь еще этих мягких французских булок";
		process(new Upcase(), s);
		process(new Lowcase(), s);
		process(new FilterAdapter(new LowPass(1.0)), new Waveform());
	}
}
