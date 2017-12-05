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
package js

import kotlinx.html.TagConsumer
import kotlinx.html.js.div
import kotlinx.html.js.h1
import kotlinx.html.js.p
import org.w3c.dom.HTMLElement
import kotlin.browser.window

fun main(args: Array<String>) {
    val currentLocation = window.location.pathname
    window.onload = onload@ {
        // Check to see if the path requested is hidden
        URLs.HIDDEN_PATHS.find { it.equals(currentLocation, ignoreCase = true) }?.let {
            return@onload window.location.assign(HTMLDoc.ERROR404.url)
        }

        val doc = HTMLDoc.values().find { it.suffix == currentLocation }
                  ?: return@onload window.location.assign(HTMLDoc.ERROR404.url)

        doc.generator.invoke(it)
    }
}

inline fun <reified T: TagConsumer<HTMLElement>> T.centerDiv() = div(classes = "center-div") {
    h1(classes = "center-div-header") {
        + "NightFury"
    }

    p(classes = "center-div-paragraph") {
        + "A Multipurpose Discord Bot For Your Server."
    }
}