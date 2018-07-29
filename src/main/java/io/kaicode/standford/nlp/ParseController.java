package io.kaicode.standford.nlp;

import io.kaicode.standford.nlp.domain.SimpleSentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParseController {

	@Autowired
	private ParseService parseService;

	@RequestMapping(path = "/parse", method = RequestMethod.POST)
	public List<SimpleSentence> parseText(@RequestParam String text) {
		return parseService.annotateText(text);
	}

}
