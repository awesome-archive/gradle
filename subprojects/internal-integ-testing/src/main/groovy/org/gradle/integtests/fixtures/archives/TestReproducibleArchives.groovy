/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.fixtures.archives

import org.spockframework.runtime.extension.ExtensionAnnotation

import java.lang.annotation.ElementType
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * A Spock extension to run tests with reproducible archives enabled.
 *
 * We currently do not have reproducible archives enabled by default.
 * We still want to test if switching them on supports all the use cases we have with the regular archive tasks.
 * Placing this annotation on a Spock spec or getMajorVersionNumber will run the features twice, with and without reproducible archives enabled.
 * A test it self can check via the property {@code reproducibleArchives} if reproducible archives are enabled or not.
 * This property is added at runtime via meta programming.
 *
 * Note that the extension adds {@code [reproducible archives]} to the method name - so tests using {@link org.gradle.integtests.fixtures.TestResources} can run into problems.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target([ElementType.METHOD, ElementType.TYPE])
@ExtensionAnnotation(ReproducibleArchivesTestExtension)
@Inherited
@interface TestReproducibleArchives {
}
