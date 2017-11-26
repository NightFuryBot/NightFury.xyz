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

import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.js.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    val currentLocation = window.location.pathname

    // Redirect if the url is nightfuxy.xyz
    if(currentLocation == "/" || currentLocation.isEmpty()) {
        return window.location.assign("http://nightfury.xyz/index.html")
    }

    window.onload = {
        val doc = HTMLDoc.values().find { it.suffix == currentLocation }

        doc?.generator?.invoke(it)
    }
}

private inline fun <reified T: TagConsumer<HTMLElement>> T.centerDiv() = div(classes = "center-div") {
    h1(classes = "center-div-header") {
        + "NightFury"
    }

    p(classes = "center-div-paragraph") {
        + "A Multipurpose Discord Bot For Your Server."
    }
}

enum class HTMLDoc(val suffix: String, val generator: (Event) -> Unit) {
    INDEX("/index.html", generator = {
        document.run { body ?: create.body {} }.append {
            navBar()
            centerDiv()
            copyright()
        }
    });

    val url: String = "http://nightfury.xyz$suffix"
}