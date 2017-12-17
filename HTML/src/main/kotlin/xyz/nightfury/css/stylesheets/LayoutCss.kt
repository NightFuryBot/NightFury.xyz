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
@file:Suppress("Unused")
package xyz.nightfury.css.stylesheets

import azadev.kotlin.css.*
import xyz.nightfury.css.*

/**
 * @author Kaidan Gustave
 */
@Import("horizontal-nav-bar.css")
object LayoutCss : StylesheetGenerator {
    override val file: String = "layout"

    @CSSName(":root")
    @CSSPriority(1)
    fun Stylesheet.root() {
        setProperty("--dark-text", "darkred")
        setProperty("--light-text", "orangered")
        setProperty("--selection-highlight", "#B3E25E")
        setProperty("--main-page-color", "#313332")
        setProperty("--nav-bar-block-color", "#414442")
    }

    fun Stylesheet.copyright() {
        top = "90%"
        left = "50%"
        position = FIXED
        verticalAlign = MIDDLE
        fontFamily = SANS_SERIF
        fontWeight = BOLD
        transform = translate("-50%", "-10%")
        color = `var`("--light-text")
    }
}