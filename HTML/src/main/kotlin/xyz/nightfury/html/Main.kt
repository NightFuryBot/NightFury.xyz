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
package xyz.nightfury.html

import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.write
import me.kgustave.kson.KSONObject
import me.kgustave.kson.KSONTokener
import xyz.nightfury.html.document.Index
import java.io.File

/**
 * @author Kaidan Gustave
 */
object Main {
    private val registry: Array<DocumentGenerator> = arrayOf(Index)

    @JvmStatic
    fun main(args: Array<String>) {

        val config = Main::class.java.getResourceAsStream("/config.json").use { KSONObject(KSONTokener(it)) }

        val outputDir = File(config["output"] as String).also { if(!it.exists()) it.mkdirs() }

        for(generator in registry) {
            val html = generator.run { createHTMLDocument().create() }

            val outputFile = generator.file(outputDir.path).also {
                if(it.exists()) it.delete()
                it.createNewFile()
            }

            outputFile.writer(Charsets.UTF_8).use { it.write(html, prettyPrint = true) }
        }
    }
}