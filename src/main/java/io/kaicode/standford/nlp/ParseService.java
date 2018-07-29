package io.kaicode.standford.nlp;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import io.kaicode.standford.nlp.domain.SimpleSentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class ParseService {

	static final String ANNOTATORS = "tokenize,ssplit,pos,lemma,ner,parse";
	static final String NEURAL = "neural";

	private final StanfordCoreNLP pipeline;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ParseService() {
		Date date = new Date();
		Properties props = new Properties();
		props.setProperty("annotators", ANNOTATORS);
		props.setProperty("coref.algorithm", NEURAL);
		pipeline = new StanfordCoreNLP(props);
		logger.info("Pipeline online. Setup took {} seconds", (new Date().getTime() - date.getTime()) / 1000);
	}

	public List<SimpleSentence> annotateText(String documentText) {
		logger.info("Parsing {}", documentText);
		CoreDocument document = new CoreDocument(documentText);
		annotate(document);
		return document.sentences().stream().map(SimpleSentence::new).collect(Collectors.toList());
	}

	void annotate(CoreDocument document) {
		pipeline.annotate(document);
	}

}
