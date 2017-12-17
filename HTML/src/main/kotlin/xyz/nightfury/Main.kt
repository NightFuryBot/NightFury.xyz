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
package xyz.nightfury

import azadev.kotlin.css.Stylesheet
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.write
import me.kgustave.kson.KSONObject
import me.kgustave.kson.KSONTokener
import xyz.nightfury.css.*
import xyz.nightfury.css.stylesheets.HorizontalNavBarCss
import xyz.nightfury.css.stylesheets.IndexCss
import xyz.nightfury.css.stylesheets.LayoutCss
import xyz.nightfury.html.DocumentGenerator
import xyz.nightfury.html.document.Index
import xyz.nightfury.html.document.error.*
import java.io.File
import kotlin.reflect.KVisibility
import kotlin.reflect.full.*

/**
 * @author Kaidan Gustave
 */
object Main {
    private val htmlGenerators: Array<DocumentGenerator> = arrayOf(
        Index, Error400, Error401, Error403, Error404, Error500
    )

    private val cssGenerators: Array<StylesheetGenerator> = arrayOf(
        IndexCss, LayoutCss, HorizontalNavBarCss
    )

    @JvmStatic
    fun main(args: Array<String>) {

        val config = Main::class.java.getResourceAsStream("/config.json").use { KSONObject(KSONTokener(it)) }

        val htmlOutput = File(config["html_output"] as String).also { if(!it.exists()) it.mkdirs() }
        val cssOutput = File(config["css_output"] as String).also { if(!it.exists()) it.mkdirs() }

        for(generator in htmlGenerators) {
            val html = generator.run { createHTMLDocument().create() }

            val outputFile = generator.file(htmlOutput.path).also {
                if(!it.parentFile.exists()) it.parentFile.mkdirs()
                if(it.exists()) it.delete()
                it.createNewFile()
            }

            outputFile.writer(Charsets.UTF_8).use { it.write(html, prettyPrint = true) }
        }

        for(generator in cssGenerators) {
            val css = Stylesheet {
                generator::class.memberExtensionFunctions.filter {
                    it.extensionReceiverParameter?.type == Stylesheet::class.starProjectedType &&
                    it.visibility == KVisibility.PUBLIC
                }.sortedWith(Comparator { f1, f2 ->
                    (f1.findAnnotation<CSSPriority>()?.value ?: Int.MAX_VALUE) -
                    (f2.findAnnotation<CSSPriority>()?.value ?: Int.MAX_VALUE)
                }).forEach {
                    if(it.findAnnotation<Keyframes>() != null) {
                        val cssName = it.findAnnotation<CSSName>()?.value
                                      ?: formatCSSSelectorName(it.name, false)

                        keyframes(cssName) { it.call(generator, this) }
                    } else {
                        val cssName = it.findAnnotation<CSSName>()?.value
                                      ?: formatCSSSelectorName(it.name, true)

                        cssName { it.call(generator, this) }
                    }
                }
            }

            val outputFile = generator.file(cssOutput.path).also {
                if(!it.parentFile.exists()) it.parentFile.mkdirs()
                if(it.exists()) it.delete()
                it.createNewFile()
            }



            outputFile.writer(Charsets.UTF_8).use {
                it.write(buildString {
                    generator::class.annotations.filter { it is Import }.forEach {
                        append("@import \"${(it as Import).value}\";\n")
                    }

                    css.renderTo(this)
                })
            }
        }
    }

    private fun formatCSSSelectorName(name: String, period: Boolean): String {
        return buildString {
            if(period)
                append('.')

            for(char in name) {
                if(char.isUpperCase())
                    append('-').append(char.toLowerCase())
                else
                    append(char)
            }
        }
    }
}