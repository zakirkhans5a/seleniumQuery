/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.csstree.condition.pseudoclass.form;

import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.locatorgenerationstrategy.SQCssPseudoNeverNativelySupported;
import io.github.seleniumquery.by.locator.SQLocatorXPath;

import static io.github.seleniumquery.by.css.attributes.AttributeEvaluatorUtils.TYPE_ATTR_LC_VAL;

/**
 * :submit
 * https://api.jquery.com/submit-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssSubmitPseudoClass extends SQCssPseudoClassCondition {

    public static final String PSEUDO = "submit";
    public static final String SUBMIT_XPATH_EXPRESSION = "(" +
            "(self::input and " + TYPE_ATTR_LC_VAL + " = 'submit')" +
            " or " +
            "(self::button and (" + TYPE_ATTR_LC_VAL + " = 'submit' or not(@type)))" +
        ")";

    public SQCssPseudoNeverNativelySupported submitPseudoClassLocatorGenerationStrategy = new SQCssPseudoNeverNativelySupported() {
        @Override
        public SQLocatorXPath toXPath() {
            return SQLocatorXPath.pureXPath(SUBMIT_XPATH_EXPRESSION);
        }
    };

    @Override
    public SQCssPseudoNeverNativelySupported getSQCssLocatorGenerationStrategy() {
        return submitPseudoClassLocatorGenerationStrategy;
    }

}