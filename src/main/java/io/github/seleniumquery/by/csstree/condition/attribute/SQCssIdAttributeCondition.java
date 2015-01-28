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

package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by.csstree.condition.SQCssCondition;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

/**
 * #id
 *
 * CASE SENSITIVE!
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssIdAttributeCondition implements SQCssCondition, SQCssConditionImplementedLocators {

    private String id;

    public SQCssIdAttributeCondition(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        String newCssSelector = SQLocatorUtils.cssMerge(leftLocator.getCssSelector(), toCSS());
        String newXPathExpression = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        return new SQLocator(newCssSelector, newXPathExpression, leftLocator);
    }

    private String toCSS() {
        return "#" + this.id;
    }

    private String toXPath() {
        return "@id = '" + id + "'";
    }

}