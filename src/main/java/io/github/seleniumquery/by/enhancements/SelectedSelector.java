package io.github.seleniumquery.by.enhancements;

import static io.github.seleniumquery.by.evaluator.SelectorUtils.ESCAPED_SLASHES;
import io.github.seleniumquery.by.SeleniumQueryBy;
import io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.SelectedPseudoClass;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * <p>Selects all <code>&lt;option&gt;<code>s that are selected.</p>
 * 
 * <p>
 * Just like jQuery, the <code>:selected<code> selector works for <code>&lt;option&gt;</code> elements.
 * It does not work for checkboxes or radio inputs; use <code>:checked</code> for them.
 * </p>
 * 
 * @author acdcjunior
 * @since 0.4.0
 */
public class SelectedSelector implements SeleniumQueryEnhancement {
	
	private static final String SELECTED_PATTERN = "(.*)"+ESCAPED_SLASHES+":selected";
	
	private final SelectedPseudoClass selectedPseudoClass = SelectedPseudoClass.getInstance();

	@Override
	public boolean isApplicable(String selector, SearchContext context) {
		return selector.matches(SELECTED_PATTERN);
	}

	@Override
	public List<WebElement> apply(String selector, SearchContext context) {
		String effectiveSelector = selector;
		
		Pattern p = Pattern.compile(SELECTED_PATTERN);
		Matcher m = p.matcher(selector);
		
		m.find(); // trigger regex matching so .group() is available
		effectiveSelector = m.group(1);
		
		List<WebElement> elementsFound = null;
		if (effectiveSelector.isEmpty()) {
			elementsFound = new By.ByCssSelector("*").findElements(context);
		} else {
			elementsFound = SeleniumQueryBy.byEnhancedSelector(effectiveSelector).findElements(context);
		}
		
		for (Iterator<WebElement> iterator = elementsFound.iterator(); iterator.hasNext();) {
			WebElement webElement = iterator.next();
			if (!selectedPseudoClass.isSelected(webElement)) {
				iterator.remove();
			}
		}

		return elementsFound;
	}

}