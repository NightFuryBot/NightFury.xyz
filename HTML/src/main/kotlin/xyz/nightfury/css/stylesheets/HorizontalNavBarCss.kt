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
import xyz.nightfury.css.CSSName
import xyz.nightfury.css.StylesheetGenerator
import xyz.nightfury.css.`var`

/**
 * @author Kaidan Gustave
 */
object HorizontalNavBarCss : StylesheetGenerator {
    override val file: String = "horizontal-nav-bar"

    fun Stylesheet.navBarUl() {
        listStyleType = NONE
        margin = 0
        padding = 0
    }

    fun Stylesheet.navBarLi() {
        display = INLINE
        float = LEFT
    }

    fun Stylesheet.navBarA() {
        backgroundColor = `var`("--nav-bar-block-color")
    }

    @CSSName(".nav-bar-li .nav-bar-a")
    fun Stylesheet.navBarLiAndA() {
        display = BLOCK
        color = `var`("--selection-highlight")
        textAlign = CENTER
        padding = "14px 16px"
        textDecoration = NONE
        fontFamily = "\"Kingthings Clarity\", sans-serif"
    }

    @CSSName(".nav-bar-li .nav-bar-a:hover")
    fun Stylesheet.navBarLiAndAOnHover() {
        color = "${`var`("--nav-bar-block-color")} darken"
        backgroundColor = `var`("--selection - highlight")
        transitionDuration = ".5s"
    }
}