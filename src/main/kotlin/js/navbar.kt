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
import kotlinx.html.js.ul
import org.w3c.dom.HTMLElement

inline fun <reified T: TagConsumer<HTMLElement>> T.navBar() =
    ul(classes = "nav-bar-ul") {
        navLi { navA  /* TODO href */   { + "Home"          } }
        navLi { navA  /* TODO href */   { + "Documentation" } }
        navLi { navA(href = BOT_INVITE) { + "Invite"        } }
        navLi { navA(href = SUPPORT)    { + "Support"       } }
    }

inline fun <reified U: UL> U.navLi(crossinline block: LI.() -> Unit) =
    li("nav-bar-li") { block() }

inline fun <reified L: LI> L.navA(href: String? = null, target: String? = null, crossinline block: A.() -> Unit) =
    a(href, target, "nav-bar-a") { block() }

const val BOT_INVITE: String =
    "https://discordapp.com/oauth2/authorize?client_id=263895505145298944&permissions=671211734&scope=bot"

const val SUPPORT: String = "https://discord.gg/XCmwxy8"