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

import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.js.body
import kotlinx.html.js.h1
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.browser.window

/**
 * @author Kaidan Gustave
 */
enum class HTMLDoc(val suffix: String, val generator: (Event) -> Unit) {
    LANDING("/", generator = {
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