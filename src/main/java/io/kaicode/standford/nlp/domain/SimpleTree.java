package io.kaicode.standford.nlp.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import edu.stanford.nlp.trees.Tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Simplified representation of a parse tree for serialisation.
 */
@JsonPropertyOrder({"tag", "category", "text", "children"})
public class SimpleTree {

	private List<SimpleTree> children;
	private final String tag;
	private final String category;
	private final String text;

	public SimpleTree(Tree constituencyParse) {
		LabeledScoredTreeNode trees = (LabeledScoredTreeNode) constituencyParse;
		CoreLabel label = (CoreLabel) constituencyParse.label();
		tag = label.tag();
		category = label.category();
		String text = label.originalText();
		this.text = text != null && !text.isEmpty() ? text : null;
		if (constituencyParse.children() != null) {
			this.children = Arrays.stream(constituencyParse.children()).map(SimpleTree::new).collect(Collectors.toList());
		}
	}

	public String getTag() {
		return tag;
	}

	public String getCategory() {
		return category;
	}

	public String getText() {
		return text;
	}

	public List<SimpleTree> getChildren() {
		return children.isEmpty() ? null : children;
	}
}
