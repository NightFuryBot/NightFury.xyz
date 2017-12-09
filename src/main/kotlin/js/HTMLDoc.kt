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

import js.error.generate404Page
import kotlinx.html.dom.append
import kotlinx.html.dom.create
import kotlinx.html.js.*
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.browser.window

/**
 * @author Kaidan Gustave
 */
enum class HTMLDoc(private val title: String, val suffix: String, val generator: (Event) -> Unit) {
    LANDING("NightFury", "/", generator = {
        document.run { body ?: create.body {} }.append {
            navBar()
            div(classes = "center-div") {
                h1(classes = "center-div-header") {
                    + "NightFury"
                }

                p(classes = "center-div-paragraph") {
                    + "A Multipurpose Discord Bot For Your Server."
                }
            }
            copyright()
        }
    }),

    INVITE("Invite", "/invite", generator = {
        window.location.assign(URLs.BOT_INVITE)
    }),

    SUPPORT("Support", "/support", generator = {
        document.title = "Support"
        window.location.assign(URLs.SUPPORT_SERVER)
    }),

    GITHUB("GitHub", "/github", generator = {
        window.location.assign(URLs.GITHUB_REPO)
    }),

    ERROR404("404 Not Found","/error/404", generator = { generate404Page() });

    val url: String = "${URLs.BASE_URL}$suffix"

    fun redirectTo() = window.location.assign(url)

    fun run(event: Event) {
        document.title = title
        generator(event)

    }
}