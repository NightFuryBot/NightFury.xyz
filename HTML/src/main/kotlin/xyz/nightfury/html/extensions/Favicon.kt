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
import kotlinx.html.link
import kotlinx.html.meta

fun HEAD.favicon(base: String = "../") {
    link { rel = "manifest"; href = "${base}favicon/manifest.json" }

    meta { name = "msapplication-TileColor"; content = "#ffffff" }
    meta { name = "msapplication-TileImage"; content = "ms-icon-144x144.png" }
    meta { name = "theme-color"; content = "#ffffff" }
}