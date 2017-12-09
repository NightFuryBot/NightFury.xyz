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

fun TagConsumer<HTMLElement>.navBar() =
    ul(classes = "nav-bar-ul") {
        navLi { navA(HTMLDoc.LANDING.url) { + "Home"          } }
        navLi { navA  /* TODO href */     { + "Documentation" } }
        navLi { navA(HTMLDoc.INVITE.url)  { + "Invite"        } }
        navLi { navA(HTMLDoc.SUPPORT.url) { + "Support"       } }
        navLi { navA(HTMLDoc.GITHUB.url)  { + "GitHub"        } }
    }

fun UL.navLi(block: LI.() -> Unit) = li("nav-bar-li") { block() }

fun LI.navA(href: String? = null, target: String? = null, block: A.() -> Unit) =
    a(href, target, "nav-bar-a") { block() }