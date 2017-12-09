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
package xyz.nightfury.html.extensions

import kotlinx.html.HEAD
import kotlinx.html.meta

class DiscordEmbed {
    companion object {
        const val propPrefix = "og:"
    }

    var title: String? = null
    var description: String? = null
    var image: String? = null

    fun generate(head: HEAD) = with(head) {
        title?.let { meta { name = "${propPrefix}title"; content = it } }

        description?.let { meta { name = "${propPrefix}description"; content = it } }

        image?.let { meta { name = "${propPrefix}image"; content = it } }
    }
}

fun HEAD.discord(block: DiscordEmbed.() -> Unit) {
    DiscordEmbed().also(block).generate(this)
}