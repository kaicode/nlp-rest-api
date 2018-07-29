package io.kaicode.standford.nlp.domain;

import edu.stanford.nlp.pipeline.CoreSentence;

import java.util.List;

/**
 * Simplified representation of a sentence for serialisation.
 */
public class SimpleSentence {

	private final String text;
	private final List<String> posTags;
	private final SimpleTree constituencyParse;

	public SimpleSentence(CoreSentence coreSentence) {
		text = coreSentence.text();
		posTags = coreSentence.posTags();
		constituencyParse = new SimpleTree(coreSentence.constituencyParse());
	}

	public String getText() {
		return text;
	}

	public List<String> getPosTags() {
		return posTags;
	}

	public SimpleTree getConstituencyParse() {
		return constituencyParse;
	}
}
