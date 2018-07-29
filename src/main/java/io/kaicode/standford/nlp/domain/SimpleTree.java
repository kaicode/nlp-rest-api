package io.kaicode.standford.nlp.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
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
		CoreLabel label = (CoreLabel) constituencyParse.label();
		tag = label.tag();
		// Only set category if no POS tag present to prevent duplicate information
		category = tag != null ? null : label.category();
		String text = label.originalText();
		Tree[] children = constituencyParse.children();

		// Only copy children if they are necessary, otherwise just copy the single child's text.
		boolean flattenChild = false;
		if (children != null) {
			if ((text == null || text.isEmpty()) && children.length == 1 && tag != null) {
				Tree child = children[0];
				CoreLabel childLabel = (CoreLabel) child.label();
				flattenChild = child.children().length == 0 && tag.equals(childLabel.tag());
			}
			if (flattenChild) {
				text = ((CoreLabel) children[0].label()).originalText();
			} else {
				List<SimpleTree> childList = Arrays.stream(children).map(SimpleTree::new).collect(Collectors.toList());
				if (!childList.isEmpty()) {
					this.children = childList;
				}
			}
		}
		this.text = text != null && !text.isEmpty() ? text : null;
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
		return children;
	}
}
