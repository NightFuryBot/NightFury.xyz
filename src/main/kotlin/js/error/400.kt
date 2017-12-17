/*
 * Copyright 2017 Kaidan Gustave
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package js.error

import js.URLs
import kotlinx.html.dom.append
import kotlinx.html.js.a
import kotlinx.html.js.h1
import kotlin.browser.document

fun generate400Page() {
    document.body?.append {
        h1 { + "400 - Bad Request!" }

        a {
            + "Go to main site."
            href = URLs.BASE_URL
        }
    }
}