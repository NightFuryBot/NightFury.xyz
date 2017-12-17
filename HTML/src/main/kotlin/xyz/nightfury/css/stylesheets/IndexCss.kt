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
@Import("layout.css")
object IndexCss : StylesheetGenerator {
    override val file: String = "index"

    fun Stylesheet.index() {
        backgroundColor = `var`("--main-page-color")
    }

    fun Stylesheet.centerDiv() {
        top = "50%"
        left = "50%"
        position = FIXED
        transform = translate("-50%", "-50%")
    }

    @Keyframes fun Stylesheet.centerDivAnimation() {
        from { opacity = "0.0" }
        to   { opacity = "1.0" }
    }

    fun Stylesheet.centerDivHeader() {
        position = RELATIVE
        textAlign = CENTER
        verticalAlign = MIDDLE
        fontFamily = "Kung Fu Panda"
        color = `var`("--dark-text")

        animation = "center-div-animation 2.5s"
    }

    fun Stylesheet.centerDivParagraph() {
        position = RELATIVE
        verticalAlign = MIDDLE
        fontStyle = NORMAL
        fontFamily = "Whitney"
        fontWeight = BOLD
        color = `var`("--light-text")

        animation = "center-div-animation 4.5s"
    }
}