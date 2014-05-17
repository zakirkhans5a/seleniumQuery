package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.PSEUDO_CLASS_VALUE_NOT_USED;
import static io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses.PseudoClassFilter.SELECTOR_NOT_USED;
import io.github.seleniumquery.by.evaluator.DriverSupportMap;
import io.github.seleniumquery.by.selector.CompiledSelector;
import io.github.seleniumquery.by.selector.SqCSSFilter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public class FocusPseudoClass implements PseudoClass {
	
	private static final FocusPseudoClass instance = new FocusPseudoClass();
	public static FocusPseudoClass getInstance() {
		return instance;
	}
	private FocusPseudoClass() { }
	
	private static final String FOCUS_PSEUDO_CLASS_NO_COLON = "focus";
	private static final String FOCUS_PSEUDO_CLASS = ":"+FOCUS_PSEUDO_CLASS_NO_COLON;
	
	@Override
	public boolean isApplicable(String pseudoClassValue) {
		return FOCUS_PSEUDO_CLASS_NO_COLON.equals(pseudoClassValue);
	}
	
	@Override
	public boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		WebElement currentlyActiveElement = driver.switchTo().activeElement();
		return element.equals(currentlyActiveElement);
	}
	
	private static final SqCSSFilter focusPseudoClassFilter = new PseudoClassFilter(getInstance(),
																		SELECTOR_NOT_USED, PSEUDO_CLASS_VALUE_NOT_USED);
	@Override
	public CompiledSelector compilePseudoClass(WebDriver driver, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		// https://developer.mozilla.org/en-US/docs/Web/CSS/:focus
		if (DriverSupportMap.getInstance().supportsNatively(driver, FOCUS_PSEUDO_CLASS)) {
			return CompiledSelector.createNoFilterSelector(FOCUS_PSEUDO_CLASS);
		}
		return CompiledSelector.createFilterOnlySelector(focusPseudoClassFilter);
	}
	
}