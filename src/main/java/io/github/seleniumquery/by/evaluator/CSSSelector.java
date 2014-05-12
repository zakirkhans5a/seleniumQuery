package io.github.seleniumquery.by.evaluator;

import io.github.seleniumquery.by.selector.CompiledSelector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface CSSSelector<T> {

	boolean is(WebDriver driver, WebElement element, T selector);

	CompiledSelector compile(WebDriver driver, T selector);
	
}