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
        return window.location.assign(HTMLDoc.INDEX.url)
    }

    window.onload = {
        val doc = HTMLDoc.values().find {
            it.suffix == currentLocation
        }

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

object URLs {
    val BASE_URL: String = window.location.origin
    const val BOT_INVITE: String =
        "https://discordapp.com/oauth2/authorize?client_id=263895505145298944&permissions=671211734&scope=bot"

    const val SUPPORT_SERVER: String = "https://discord.gg/XCmwxy8"
}

enum class HTMLDoc(val suffix: String, val generator: (Event) -> Unit) {
    INDEX("/index", generator = {
        document.run { body ?: create.body {} }.append {
            navBar()
            centerDiv()
            copyright()
        }
    }),

    INVITE("/invite", generator = { window.location.assign(URLs.BOT_INVITE) }),
    SUPPORT("/support", generator = { window.location.assign(URLs.SUPPORT_SERVER) }),

    ERROR404("/error/404", generator = {
        document.run { body ?: create.body {} }.append {
            h1 { + "404 - Not Found!" } // TODO Make this better
        }
    });

    val url: String = "${URLs.BASE_URL}$suffix"
}