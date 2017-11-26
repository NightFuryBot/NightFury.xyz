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
    window.onload = {
        console.info(document.documentURI)

        val parts = document.documentURI.split(splitter)

        val htmldoc: String = parts[parts.size - 1].run {
            if(contains("?")) {
                val sIndex = indexOf("?")
                substring(0, sIndex)
            } else this
        }.run {
            if(endsWith("html", ignoreCase = true)) {
                substring(0, length - 5)
            } else this
        }

        val doc = HTMLDoc.values().find { it.name.equals(htmldoc, ignoreCase = true) }

        doc?.generator?.invoke(it)
    }
}

val splitter = Regex("/")

private inline fun <reified T: TagConsumer<HTMLElement>> T.centerDiv() = div(classes = "center-div") {
    h1(classes = "center-div-header") {
        + "NightFury"
    }

    p(classes = "center-div-paragraph") {
        + "A Multipurpose Discord Bot For Your Server."
    }
}

enum class HTMLDoc(val generator: (Event) -> Unit) {
    INDEX({
        document.run { body ?: create.body {} }.append {
            navBar()
            centerDiv()
            copyright()
        }
    })
}