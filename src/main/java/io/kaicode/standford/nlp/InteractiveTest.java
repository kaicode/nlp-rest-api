package io.kaicode.standford.nlp;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

public class InteractiveTest {

	private ParseService parseService;

	public static void main(String[] args) {
		new InteractiveTest().run();
	}

	private void run() {
		parseService = new ParseService();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = reader.readLine()) != null) {
				annotateLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void annotateLine(String line) {
		if (line.isEmpty()) {
			return;
		}
		System.out.println();
		System.out.println("--- New Document ---");
		CoreDocument document = new CoreDocument(line);
		parseService.annotate(document);

		for (CoreSentence coreSentence : document.sentences()) {
			System.out.println();
			System.out.println("Sentence:\n" + coreSentence.text());
			System.out.println();
			System.out.println("POS Tags:\n" + coreSentence.posTags());
			System.out.println();
			System.out.println("NER Tags:\n" + coreSentence.nerTags());
			System.out.println();
			Tree constituencyParse = coreSentence.constituencyParse();

			System.out.println("Constituency parse:\n" + constituencyParse);
			System.out.println();

			SemanticGraph dependencyParse = coreSentence.dependencyParse();
			System.out.println("Dependency parse:\n" + dependencyParse);
			System.out.println();
		}
		System.out.println("--- End Document ---");
		System.out.println();
	}

}
